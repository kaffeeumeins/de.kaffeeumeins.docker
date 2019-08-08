#!/usr/bin/env bash
docker stop grafana
docker rm grafana
docker container ls --all
