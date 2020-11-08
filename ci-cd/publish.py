import os


def isSnapshot():
    projectVersion = os.system('./mnt/space/work/gradlew properties -q | grep "version:" | awk "{print $2"}') # Get the project version
    if ("SNAPSHOT" in projectVersion):
        return True
    else:
        return False


if (isSnapshot()):
    os.system("./mnt/space/work/gradlew publishEvcalcPublicationToMaven2Repository") # Publish to snapshot repository
else:
    os.system("./mnt/space/work/gradlew publishEvcalcPublicationToMavenRepository") # Publish to normal repository