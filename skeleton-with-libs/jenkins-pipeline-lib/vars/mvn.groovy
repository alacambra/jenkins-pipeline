def cleanTest(String buildNumber, String directory = '.') {
    exec("clean test", buildNumber, directory)
}

def cleanPackage(String buildNumber, String directory = '.') {
    exec("clean package", buildNumber, directory)
}

def deploy(String buildNumber, String directory = '.') {
    exec("deploy", buildNumber, directory)
}

def integrationTest(String buildNumber, String directory = '.') {
    exec("failsafe:integration-test failsafe:verify", buildNumber, directory)
}

private def exec(String subCommand, String buildNumber, String directory) {
    dir(directory) {
        def mvnHome = tool name: 'Maven3', type: 'maven'
        sh "${mvnHome}/bin/mvn -B ${subCommand} -DbuildNumber=b${buildNumber}"
    }
}
