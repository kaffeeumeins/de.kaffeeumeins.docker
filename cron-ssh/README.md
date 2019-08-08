# Execute cron jobs in docker container

This is a example of a docker image which executes every second a given script.
We assume the script will copy a file from a remote server. Therefore we have to install the following additional package:

* ``openssh-client``
* ``rsync``
* ``sshpass``

The password will be passted via the environment variable ``SSHPASS``.

* Keep docker image running, to inspect the content: ``CMD tail -f /dev/null``

| File      | Description |
| --------- | ----------- |
| image/*   | all files to build the image |
| test.sh   | execute ``rm.sh``, ``build.sh``, ``run.sh`` and ``status.sh`` |
| rm.sh     | remove the container   |
| build.sh  | build the image        |
| ssh.sh    | ssh into the container |
| start.sh  | start the image        |
| status.sh | display the logs       |
| stop      | stop the container     |

## References

* [Alpine Linux](https://alpinelinux.org/downloads/)
* [Docker Alpine](https://hub.docker.com/_/alpine)
* [Docker Forum: Cron](https://forums.docker.com/t/how-to-run-a-cron-job-inside-a-container-alpine/7759)
* [BusyBox cron container example](https://gist.github.com/andyshinn/3ae01fa13cb64c9d36e7)
* [Auf die Minute genau: Cronjobs im Docker-Container](https://ueberdosis.io/artikel/auf-die-minute-genau-cronjobs-im-docker-container/)