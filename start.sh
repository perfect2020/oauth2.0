#!/bin/bash

echo "start deploy application..."

home_dir=$(pwd)

echo "home_dir:" $home_dir

if [ -a "$home_dir"/server.war ];

	then 
		( java -jar ./server.war --server.port=8989 >oauthserver.log 2>&1 &)

		echo "[OK] done..."

	else
		echo '[ERROR] server.war package not found'

fi
