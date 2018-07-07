#!/usr/bin/env groovy
import com.sebastian_daschner.jenkins.pipeline.MetaInfo

node {
    prepare()

    stage('build-ut') {
        buildUnitTest()
    }

    parallel failFast: false,
            'integration-test': {
                stage('integration-test') {
                    integrationTest()
                }
            },
            'analysis': {
                stage('analysis') {
                    analysis()
                }
            }

    stage('system-test') {
        systemTest()
    }

    stage('deploy-staging') {
        deployStaging()
    }
}

stage('deploy') {
    input 'Deploy to production?'
    node {
        deployProduction()
    }
}

def prepare() {
    info = metaInfo.create([commit            : "commit",
                            projectName       : 'project-name',
                            dockerImage       : 'project-name',
                            dbMigrationImage  : 'project-name-db-migration',
                            databaseRolloutDir: 'dir/database/rollouts/'])

    deleteDir()
    checkoutGitRepos()
    
}

def checkoutGitRepos() {
    echo "checking out git repos"
}

def buildUnitTest() {
    echo "Building and testing"

def unitTestReports() {
    echo "generating reports"
}

def addBuildMetaInfo() {
    echo "adding MetaInfo"
}

def integrationTest() {
    echo "runing IT"
}

def analysis() {
    runAnalysis()
    addAnalysisMetaInfo()
}

def runAnalysis() {
    echo 'running SonarQube analysis'
    sh 'sleep 3'
    // insert mvn sonar:sonar or similar here
    echo 'finished SonarQube analysis'
}

def addAnalysisMetaInfo() {
    // collect the corresponding information
   echo "addAnalysisMetaInfo"
}

def systemTest() {
    deploy("systemtest", info)
    systemTestReports()
    addSystemTestMetaInfo()
}

def systemTestReports() {
    echo "systemTestReports"
}

def addSystemTestMetaInfo() {
   echo "addSystemTestMetaInfo"
}

def deployStaging() {
    deploy("staging", info)
}

def deployProduction() {
    echo "deployProduction"
}

//<<<<<<<<<<<<<<<<<<<<<<

def buildPushDocker(String imageTag, String directory) {
    echo "buildPushDocker"
}

def deploy(String namespace, MetaInfo info) {
    echo "deploying ${info.dockerImage} to Kubernetes ${namespace}"
}

def updateDeploymentImages(String dockerImage, String namespace, String databaseVersion) {
    echo "updateDeploymentImages"
}
