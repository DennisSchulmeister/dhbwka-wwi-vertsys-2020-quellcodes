#! /bin/sh

docker stop gcplus-admin
docker stop gcplus-nameserver
docker stop gcplus-gateway
docker stop gcplus-golfcourse
docker stop gcplus-scorecard
docker stop gcplus-frontend

docker rm gcplus-admin
docker rm gcplus-nameserver
docker rm gcplus-gateway
docker rm gcplus-golfcourse
docker rm gcplus-scorecard
docker rm gcplus-frontend

docker container prune --force

docker network rm gcplus
docker network prune --force
