#!/usr/bin/env bash
# Add script to run program here.
# Complete bin/setup so that after it is
# run, bin/parking_lot.sh can be used to launch
# it.

# This variable contains absolute path of this `parking_lot.sh` script
DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" >/dev/null && pwd )"
printf $DIR
java -jar $DIR/target/parking-lot-1.0-SNAPSHOT.jar