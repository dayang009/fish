#!/usr/bin/env bash

APP_FILE_NAME=/opt/chfs/chfs
LOG_FILE_FILE=/opt/chfs/logs/ops.log
CONFIG_FILE=/opt/chfs/chfs.ini
FILE_PID=$(pgrep -f $APP_FILE_NAME)
kill -9 "$FILE_PID"
echo "close $APP_FILE_NAME"
sleep 3
if test -e $APP_FILE_NAME; then
  echo "start $APP_FILE_NAME"
  nohup $APP_FILE_NAME --file=$CONFIG_FILE >$LOG_FILE_FILE 2>&1 &
  sleep 3
  echo "start success"
else
  echo "start fail"
fi
sleep 2
tail -30f $LOG_FILE_FILE
