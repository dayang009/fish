#!/usr/bin/env bash

#nohup java -Xmx2g -Xms2g -Xmn1g -jar fish-register.jar --spring.profiles.active=prod > /dev/null 2>&1 &
#sleep 1;
nohup java -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=36902 -Xmx2g -Xms2g -Xmn1g -jar fish-gateway.jar --spring.profiles.active=prod > /dev/null 2>&1 &
sleep 1;
nohup java -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=36904 -Xmx2g -Xms2g -Xmn1g -jar fish-job-admin.jar --spring.profiles.active=prod > /dev/null 2>&1 &
sleep 1;
nohup java -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=36906 -Xmx2g -Xms2g -Xmn1g -jar fish-executor.jar --spring.profiles.active=prod > /dev/null 2>&1 &
sleep 1;
nohup java -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=36908 -Xmx2g -Xms2g -Xmn1g -jar fish-business.jar --spring.profiles.active=prod > /dev/null 2>&1 &
sleep 1;
echo "Projects Start Success."
