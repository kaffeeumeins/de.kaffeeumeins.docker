FROM alpine:3.8

# Ensure needed software is installed
RUN apk --update add openssh-client
RUN apk --update add rsync
RUN apk --update add sshpass

# Install scripts
COPY script.sh /script.sh
RUN chmod +x /script.sh

# Init cron
COPY crontab /etc/cron/crontab
RUN crontab /etc/cron/crontab

ENV SSHPASS="given by docker"

CMD crond -l 2 -f
