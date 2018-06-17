#!/bin/sh
nohup mvn spring-boot:run -Dmaven.test.skip=true 2>&1 /dev/null &
