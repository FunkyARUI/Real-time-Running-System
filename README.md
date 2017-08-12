
##Service Start Sequence
1. docker-compose up
3. sh ./start-location-simulator.sh
4. sh ./start-location-distribution.sh
5. sh ./start-location-updater.sh
6. sh ./start-running-location-service.sh
7. go to running location service folder and run sh ./upload-running.sh

##UI
1. Open Simulator UI on http://localhost:9005
2. Click run simulation
3. Click cancel if you want to cancel simulation

##Hystrix stream is open at
http://localhost:9005/hystrix.stream

https://github.com/FasterXML/jackson-databind/issues/1239
