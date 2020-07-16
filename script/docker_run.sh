#!/usr/bin/env bash
docker run --ip 172.19.0.90 -p 8099:8099 --name api-server melon-api-server:latest