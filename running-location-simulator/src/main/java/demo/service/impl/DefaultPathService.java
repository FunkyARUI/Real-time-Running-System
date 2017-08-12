/*
 * Copyright 2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package demo.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.maps.DirectionsApi;
import com.google.maps.DirectionsApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.model.DirectionsRoute;
import com.google.maps.model.LatLng;
import demo.model.DirectionInput;
import demo.model.Point;
import demo.model.SimulatorInitLocations;
import demo.service.PathService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class DefaultPathService implements PathService {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private Environment environment;

    @Value("${simulation.service.googleApiKey}")
    private String googleApiKey;

    public DefaultPathService() {
        super();
    }

    @Override
    public List<DirectionInput> loadDirectionInput() {
        final InputStream is = this.getClass().getResourceAsStream("/directions.json");

        try {
            return objectMapper.readValue(is, new TypeReference<List<DirectionInput>>() {
            });
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public SimulatorInitLocations loadSimulatorFixture() {
        final InputStream is = this.getClass().getResourceAsStream("/init-locations.json");

        try {
            return objectMapper.readValue(is, SimulatorInitLocations.class);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public String getCoordinatesFromGoogleAsPolyline(DirectionInput directionInput) {
        final GeoApiContext context = new GeoApiContext()
                .setApiKey(googleApiKey);
        final DirectionsApiRequest request = DirectionsApi.getDirections(
                context,
                directionInput.getFrom(),
                directionInput.getTo());

        try {
            DirectionsRoute[] routes = request.await();
            return routes[0].overviewPolyline.getEncodedPath();
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }


    @Override
    public List<Point> getCoordinatesFromGoogle(DirectionInput directionInput) {

        final GeoApiContext context = new GeoApiContext()
                .setApiKey(googleApiKey);
        final DirectionsApiRequest request = DirectionsApi.getDirections(
                context,
                directionInput.getFrom(),
                directionInput.getTo());
        List<LatLng> latlongList = null;

        try {
            DirectionsRoute[] routes = request.await();

            for (DirectionsRoute route : routes) {
                latlongList = route.overviewPolyline.decodePath();
            }
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }

        final List<Point> points = new ArrayList<>(latlongList.size());

        for (LatLng latLng : latlongList) {
            points.add(new Point(latLng.lat, latLng.lng));
        }

        return points;
    }

}
