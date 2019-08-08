#!/usr/bin/env bash
sudo docker build -t "de.kaffeeumeins/cron" ./image/
sudo docker image ls -a | grep cron
