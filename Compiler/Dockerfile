# syntax=docker/dockerfile:1

FROM openjdk:8-jdk-slim-buster

RUN apt-get update && \
    apt-get install -y --no-install-recommends \
        ca-certificates \
        curl \
        python3.7 \
        python3-pip \
        python3.7-dev \
        python3-setuptools \
        python3-wheel


RUN apt-get install android-sdk -y --no-install-recommends
ENV ANDROID_HOME /usr/lib/android-sdk
WORKDIR /python-docker

COPY requirements.txt requirements.txt
RUN pip3 install -r requirements.txt



COPY . .


CMD [ "python3","-u","main.py"]
