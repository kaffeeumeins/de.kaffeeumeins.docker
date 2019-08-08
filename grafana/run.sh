#!/usr/bin/env bash
##   -e "GF_INSTALL_PLUGINS=grafana-clock-panel,vonage-status-panel,grafana-polystat-panel" \
docker run -d --name grafana \
    -p 3000:3000 \
    -v $(pwd)/data:/var/lib/grafana \
    -e "GF_metrics_enabled=true" \
    grafana/grafana
