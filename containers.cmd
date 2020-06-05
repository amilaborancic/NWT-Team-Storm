call docker system prune -a
cd ./eureka
call mvn clean install
cd ../user-service
call mvn clean install -DskipTests
cd ../api-gateway
call mvn clean install -DskipTests
cd ../comicbook-service
call mvn clean install -DskipTests
cd ../rating-service
call mvn clean install -DskipTests
cd ../catalogue-service
call mvn clean install -DskipTests
cd ../system-events
call mvn clean install -DskipTests
cd ../stripomanija-frontend
call docker build -t stripomanija-frontend .
cd ../
call docker-compose up --build
pause 9999999999999999