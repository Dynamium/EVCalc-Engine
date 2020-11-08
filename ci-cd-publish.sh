# This script publishes the artifacts in needed repositories.

PROJECT_VERSION=./gradlew properties -q | grep "version:" | awk "{print $2}"

if grep -q "SNAPSHOT" <<< "$PROJECT_VERSION"; then # If project version contains SNAPSHOT
    ./gradlew publishEvcalcPublicationToMaven2Repository # Publish to a snapshot repository
else # Else
  ./gradlew publishEvcalcPublicationToMavenRepository # Publish to a main repository
fi