#!/bin/bash

docker rm crestaurant
docker run -p 8808:8808 -t crestaurant