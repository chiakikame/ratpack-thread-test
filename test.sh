#!/usr/bin/env bash
./requestLauncher.sh & ./requestLauncher.sh & ./requestLauncher.sh & ./requestLauncher.sh
wait
diff -sq /var/tmp/sws /var/tmp/ws
diff -sq /var/tmp/sws /var/tmp/aws
diff -sq /var/tmp/ws /var/tmp/aws
