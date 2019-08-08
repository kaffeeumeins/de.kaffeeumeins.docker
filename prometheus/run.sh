#!/usr/bin/env bash
docker run -d --name prometheus \
    -v $(pwd)/prometheus.yml:/prometheus.yml \
    -v $(pwd)/data:/prometheus \
    -p 9090:9090 \
    prom/prometheus --web.enable-lifecycle --config.file="/prometheus.yml"
