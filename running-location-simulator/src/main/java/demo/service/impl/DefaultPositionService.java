/*
 * Copyright 2016 the original author or authors.
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

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import demo.model.CurrentPosition;
import demo.service.PositionService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
@Slf4j
public class DefaultPositionService implements PositionService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultPositionService.class);

    @Autowired
    private RestTemplate restTemplate;

//    @Value("${com.ross.running.location.distribution}")
//    private String runningLocationDistribution;

    public DefaultPositionService() {
        super();
    }

    @HystrixCommand(fallbackMethod = "processPositionInfoFallback")
    @Override
    public void processPositionInfo(long id, CurrentPosition currentPosition,
                                    boolean sendPositionsToDistributionService) {

        String runningLocationDistribution = "http://running-location-distribution";
        if (sendPositionsToDistributionService) {
            log.info("Simulator is callling distribution REST API");
            this.restTemplate.postForLocation(runningLocationDistribution + "/api/locations", currentPosition);

        }

    }

    public void processPositionInfoFallback(long id, CurrentPosition currentPosition,
                                            boolean sendPositionsToDistributionService) {
        LOGGER.error("Hystrix Fallback Method. Unable to send message for distribution.");
    }

}
