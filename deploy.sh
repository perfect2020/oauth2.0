#!/bin/bash

echo "start deploy application..."

home_dir=$(pwd)

echo "home_dir:" $home_dir

mvn clean && mvn package && echo "package complated"


if [ -a "$home_dir"/oauthserver.war ];

	then 
		scp ./oauthserver.war aliyun:/opt/ezt/servers.war

		echo "war package upload aliyun server "
		
		echo "[OK] done..."

	else
		echo '[ERROR] server.war package not found'

fi
