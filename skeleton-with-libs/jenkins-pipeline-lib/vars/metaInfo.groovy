import com.sebastian_daschner.jenkins.pipeline.MetaInfo

MetaInfo create(Map params) {
    MetaInfo info = new MetaInfo()
    info.buildVersion = env.BUILD_ID
    info.projectName = params.projectName
    info.commit = params.commit
    info.dockerImage = "docker.example.com/${params.dockerImage}:${info.buildVersion}"
    info.databaseMigrationImage = "docker.example.com/${params.dbMigrationImage}:${info.buildVersion}"
    info.databaseVersion = calculateDatabaseVersion(params.databaseRolloutDir)

    getBuildDirectory(info.projectName, info.buildVersion).mkdirs()
    commit(info, 'created build info')

    info
}

private String calculateDatabaseVersion(String databaseRolloutDir) {
    dir(databaseRolloutDir) {
        def output = sh(returnStdout: true, script: "ls *.sql | sort -r | head -1").trim()
        def version = Integer.parseInt(output.substring(0, output.indexOf('_'))).toString()
        version
    }
}

private File getBuildDirectory(String projectName, String buildVersion) {
    new File("${env.WORKSPACE}/versions/${projectName}/builds/${buildVersion}/")
}

private File getBuildInfoFile(String projectName, String buildVersion) {
    new File(getBuildDirectory(projectName, buildVersion).path + '/build-info.json')
}

def recordTestReports(MetaInfo info, String directory, String alternativePath = '') {
    String buildDirectory = getBuildDirectory(info.projectName, info.buildVersion).path + '/'
    String target = alternativePath ? buildDirectory + alternativePath : buildDirectory
    sh "cp -R ${directory} ${target}"
}

def commit(MetaInfo info, String message) {
    getBuildInfoFile(info.projectName, info.buildVersion).write(info.toJson())
    git.commitMergePush("[jenkins] ${message} for build ${info.buildVersion}", 'versions')
}

MetaInfo load(String projectName, String buildVersion) {
    def text = getBuildInfoFile(projectName, buildVersion).text
    echo "text is ${text}"
    def info = MetaInfo.fromJson(text)
    echo "info is ${text}"
    info
}
