#!/usr/bin/env bash

echo "Projects Starting..."
nohup java -jar fish-register.jar > /dev/null 2>&1 &
sleep 5;
echo "fish-register Start Success."
nohup java -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=36902 -jar fish-gateway.jar > /dev/null 2>&1 &
sleep 8;
echo "fish-gateway Start Success."
nohup java -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=36904 -jar fish-job-admin.jar > /dev/null 2>&1 &
sleep 8;
echo "fish-job-admin Start Success."
nohup java -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=36906 -jar fish-executor.jar > /dev/null 2>&1 &
sleep 8;
echo "fish-executor Start Success."
nohup java -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=36908 -jar fish-business.jar > /dev/null 2>&1 &
sleep 5;
echo "fish-business Start Success."
