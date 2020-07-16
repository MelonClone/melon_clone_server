#!/usr/bin/env bash
./gradlew clean build
docker build -t melon-api-server:latest .
docker container stop api-server
docker container rm api-server
docker run --ip 172.19.0.90 -p 8099:8099 --name api-server melon-api-server:latest