job("[Engine] Build") {
    container("openjdk:14") {
        shellScript {
            content = "./gradlew clean build --info"
        }
    }
}

job("[Engine] Publish artifact") {
    container("openjdk:14") {
        env["jb_packages_username"] = Secrets("jb_packages_username")
        env["jb_packages_password"] = Secrets("jb_packages_password")

        shellScript {
            content = "./gradlew publish"
        }
    }
}

job("[Engine] Run tests") {
    container("openjdk:14") {
        shellScript {
            content = "./gradlew test --info"
        }
    }
}