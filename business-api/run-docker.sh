#!/bin/bash

docker rm peculium
docker run -p 8808:8808 -t peculium