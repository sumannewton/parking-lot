#!/usr/bin/env bash
# Add script to run program here.
# Complete bin/setup.sh so that after it is
# run, bin/parking_lot.sh can be used to launch
# it.

# This variable contains absolute path of this `parking_lot.sh` script
DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" >/dev/null && pwd )/target"
JAR_NAME="parking-lot-1.0-SNAPSHOT.jar"

if [ -z "$1" ] ; then
        java -jar $DIR/$JAR_NAME
        exit 1
else
	java -jar $DIR/$JAR_NAME $arg1
fi