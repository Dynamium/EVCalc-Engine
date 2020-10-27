job("Build Engine") {
    gradlew("openjdk:14", "clean build")
}

job("Publish artifact") {
    gradlew("openjdk:14", "publish")
}