job("[Engine] Build") {
    container("openjdk:14") {
        shellScript {
            content = "./gradlew clean build --info -x test"
        }
    }
}

job("[Engine] Publish artifact") {
    container("ubuntu") {
        env["jb_packages_username"] = Secrets("jb_packages_username")
        env["jb_packages_password"] = Secrets("jb_packages_password")

        shellScript {
            interpreter = "/bin/bash"
            content = "python3 ci-cd/publish.py"
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