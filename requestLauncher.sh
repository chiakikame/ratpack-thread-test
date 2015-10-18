#!/usr/bin/env bash
for i in {1..200}
do
   curl -s --data '' http://localhost:8080/ > /dev/null
done
