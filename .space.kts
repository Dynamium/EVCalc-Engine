job("Build Engine") {
    container("openjdk:14") {
        shellScript {
            content = "./gradlew clean build"
        }
    }
}

job("Publish artifact") {
    container("openjdk:14") {
        env["jb_packages_username"] = Secrets("jb_packages_username")
        env["jb_packages_password"] = Secrets("jb_packages_password")

        shellScript {
            content = "./gradlew publish"
        }
    }
}