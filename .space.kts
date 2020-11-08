job("[Engine] Build") {
    container("openjdk:14") {
        shellScript {
            content = "./gradlew clean build --info -x test"
        }
    }
}

job("[Engine] Publish artifact") {
    container("openjdk:14") {
        env["jb_packages_username"] = Secrets("jb_packages_username")
        env["jb_packages_password"] = Secrets("jb_packages_password")

        shellScript {
            interpreter = "/bin/bash"
            location = "ci-cd-publish.sh"
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