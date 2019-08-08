# Collection of Docker stuff

## Docker

* Enable docker advanced stuff
  ```json
  {
    "debug" : true,
    "experimental" : true,
    "metrics-addr" : "0.0.0.0:9323"
  }
  ```
* Access the metrics with: ``http://localhost:9323/metrics`` from Mac
* Access the metrics with: ``http://host.docker.internal:9323/metrics/``from a docker image

## Prometheus

| Property       | Value                         |
| -------------- | ----------------------------- |
| Access:        | http://localhost:9090         |
| Metrics:       | http://localhost:9090/metrics |
| Container name | ``prometheus``                |
| Configuration  | ``./prometheus.yml``          |
| Data           | ``./data``                    |

### Grafana

| Property       | Value                       |
| -------------- | --------------------------- |
| Access:        | http://localhost:3000       |
| Metrics:       | http://localhost:3000/metrics |
| Container name | ``grafana``                 |
| Data           | ``./data``    |

* [Configuration](https://grafana.com/docs/installation/configuration/#enabled-6)
* User ``admin`` with password ``admin``
* !!! The data is stored in the container and will be lost if we remove the container.
  For now this is ok.
  ==> On the long run we should fix this, like Prometheus.
* [Grafana Dashboards](https://grafana.com/grafana/dashboards)
  * Grafana Internals: 3590
  * Prometheus Stats: 2
  * Docker and Host Monitoring w/ Prometheus: 179