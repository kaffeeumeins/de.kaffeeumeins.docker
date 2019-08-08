#!/usr/bin/env bash
sudo docker run -d --name cron \
    -e SSHPASS=geheim \
    de.kaffeeumeins/cron
sudo docker container ls --all
