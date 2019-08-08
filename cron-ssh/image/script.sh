#!/bin/sh

user="user"             # the ssh user
host="host"             # the ssh host
host_dir="source"       # the folder on the ssh host
host_pattern="^.*\.zip" # the pattern to filter the files
target_dir="target"     # the folder to copy the newest file

# copy newest file from host to target folder
echo $(date -Iseconds) Get the newest file name
#lastest=$(sshpass -e ssh ${user}@${host} "ls -t ${host_dir} | grep -E ${host_pattern} | head -1")
echo $(date -Iseconds) Copy newest file ${latest}
#sshpass -e rsync -vzh ${user}@${host}:${host_dir}/${lastest} ${target_dir} 
echo $(date -Iseconds) Copy successfully done.
