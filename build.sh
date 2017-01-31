#!/bin/bash
STR="BUILDING ..."

RND=$RANDOM

ERR=$(( $RND % 10 ));

echo $STR

if [ $ERR -lt 1 ]
then
	echo "ERROR DURING BUILD"
	exit 1
else
	echo "SUCCESS"
	exit 0
fi
