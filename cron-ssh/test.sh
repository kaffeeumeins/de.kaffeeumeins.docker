#!/usr/bin/env bash
echo ===== rm cron container
./rm.sh

echo ===== build cron container
./build.sh

echo ===== run cron container
./run.sh

echo ===== status cron container
./status.sh
