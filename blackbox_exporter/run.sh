#!/usr/bin/env bash
# https://github.com/prometheus/blackbox_exporter
sudo docker run -d --name blackbox_exporter \
    -p 9115:9115 \
    -v /mnt/docker/blackbox_exporter/blackbox.yml:/etc/blackbox_exporter/config.yml \
    prom/blackbox-exporter 
