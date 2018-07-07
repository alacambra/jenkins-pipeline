def checkoutToDir(String url, String targetDir, String branch = 'master') {
    checkout([
            $class                           : 'GitSCM',
            branches                         : [[name: "refs/heads/${branch}"]],
            doGenerateSubmoduleConfigurations: false,
            extensions                       : [
                    [$class: 'RelativeTargetDirectory', relativeTargetDir: targetDir],
            ],
            submoduleCfg                     : [],
            userRemoteConfigs                : [[credentialsId: 'git-credentials', url: url]]
    ])

    dir(targetDir) {
        gitCommit = sh(returnStdout: true, script: 'git rev-parse HEAD').trim()
        sh "git checkout ${branch}"
    }
    gitCommit
}

def commitMergePush(String message, String directory = '.') {
    sshagent(['git-credentials']) {
        dir(directory) {
            def branch = sh returnStdout: true, script: 'git rev-parse --abbrev-ref HEAD'

            sh "git add --all"
            sh "git commit --allow-empty -m '${message}'"
            sh "git pull --rebase origin ${branch}"
            sh "git push origin ${branch}"
        }
    }
}
