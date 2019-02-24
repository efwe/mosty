#!/usr/bin/env bash
./gradlew clean build
ssh root@map.123k.org systemctl stop mosty
scp build/libs/mosty-1.0.0.jar fw@map.123k.org:/home/fw/devel/mosty
ssh root@map.123k.org systemctl start mosty
