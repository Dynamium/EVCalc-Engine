job("Build Engine") {
    container("openjdk:14") {
        shellScript {
            content = "./gradlew clean build"
        }
    }
}

job("Publish artifact") {
    container("openjdk:14") {
        shellScript {
            content = "./gradlew publish"
        }
    }
}