#!/usr/bin/env bash
sudo docker stop blackbox_exporter
sudo docker rm blackbox_exporter
sudo docker container ls --all
