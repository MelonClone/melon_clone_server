FROM adoptopenjdk/openjdk11:slim

# 기본 툴 설치
RUN apt-get update
RUN apt-get upgrade -y

RUN apt-get install git -y && \
	apt-get install vim -y 

# SSH Key 설정
RUN mkdir /root/.ssh/
COPY ./id_rsa /root/.ssh/id_rsa
COPY ./id_rsa.pub /root/.ssh/id_rsa.pub

RUN chmod 700 ~/.ssh && \
	chmod 600 ~/.ssh/id_rsa && \
	chmod 644 ~/.ssh/id_rsa.pub && \
	touch /root/.ssh/known_hosts && \
	ssh-keyscan github.com >> /root/.ssh/known_hosts

# Local time, timezone 지정
# RUN /bin/cp -f /etc/localtime /etc/localtime.org && \
	# /bin/cp -f /usr/share/zoneinfo/Asia/Seoul /etc/localtime

# 소스 Pull
WORKDIR /
RUN mkdir /src
WORKDIR /src
RUN git clone git@github.com:gdmail91/melon_clone_server
WORKDIR ./melon_clone_server

RUN chmod +x ./gradlew
RUN ./gradlew build

CMD ["/bin/bash"]