#!/usr/bin/env bash
docker stop prometheus
docker rm prometheus
docker container ls --all
