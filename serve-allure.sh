#!/usr/bin/env bash
set -e

mvn clean test
mvn allure:generate

log=serve.log
rm -f "$log"
nohup mvn allure:serve > "$log" 2>&1 &
pid=$!
echo "Started Allure serve (PID $pid). Waiting for URL..."

for i in $(seq 1 30); do
  sleep 1
  if grep -Eo 'http://[^ ]+' "$log" | head -n1 >/dev/null 2>&1; then
    url=$(grep -Eo 'http://[^ ]+' "$log" | head -n1)
    echo "Allure URL: $url"
    if command -v xdg-open >/dev/null 2>&1; then
      xdg-open "$url" || true
    elif command -v open >/dev/null 2>&1; then
      open "$url" || true
    fi
    exit 0
  fi
done

echo "Could not find Allure URL in $log; check the log file for details."
echo "To stop the server: kill $pid"