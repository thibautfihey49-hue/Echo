#!/bin/sh
set -e
DIR="$(cd "$(dirname "$0")" && pwd)"
JAR="$DIR/gradle/wrapper/gradle-wrapper.jar"
if [ ! -f "$JAR" ] || ! unzip -t "$JAR" >/dev/null 2>&1; then
  echo "gradle-wrapper.jar manquant/corrompu, tentative generation..."
  if command -v gradle >/dev/null 2>&1; then
    gradle wrapper --gradle-version 8.6
  else
    mkdir -p "$DIR/gradle/wrapper"
    curl -L --retry 3 -o "$JAR" https://raw.githubusercontent.com/gradle/gradle/v8.6.0/gradle/wrapper/gradle-wrapper.jar
  fi
fi
exec java -jar "$JAR" "$@"
