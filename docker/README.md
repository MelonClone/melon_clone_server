
```shell
docker network create --gateway 172.19.0.1 --subnet 172.19.0.0/21 mynet

# [위치] Dockerfile 빌드
docker build -t [image name]:[image version] [위치]

# 컨테이너 실행
docker run -it --ip [ip] -p [외부port]:[컨테이너 내부port] --name [컨테이너 이름] [image id] /bin/bash
## OR
docker run -d --ip [ip] -p [외부port]:[컨테이너 내부port] --name [컨테이너 이름] [image id]
docker logs -f [컨테이너 이름]

docker inspect -f "{{ .NetworkSettings.IPAddress }}" eureka-server

# 컨테이너 중지
docker container stop [컨테이너 이름]

# 컨테이너 삭제
docker container rm [컨테이너 이름]
```