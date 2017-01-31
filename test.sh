#!/bin/bash
RND=$RANDOM

ERR=$(( $RND % 10 ));

echo "TESTING ..."
echo "<test></test>" >> test_results.xml

if [ $ERR -lt 1 ]
then
	echo "ERROR DURING TESTS"
	exit 1
else
	echo "ALL THE TESTS PASSED"
	exit 0
fi