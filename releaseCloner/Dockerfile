# syntax=docker/dockerfile:1

FROM python:3.8-slim-buster

WORKDIR /python-docker

COPY requirements.txt requirements.txt
RUN apt-get update && \
    apt-get upgrade -y && \
    apt-get install -y git
RUN pip3 install -r requirements.txt

COPY . .

CMD [ "python3","-u", "mainListner.py"]
