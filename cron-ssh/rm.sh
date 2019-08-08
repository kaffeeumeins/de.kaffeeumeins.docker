#!/usr/bin/env bash
sudo docker stop cron
sudo docker rm cron
sudo docker container ls --all
