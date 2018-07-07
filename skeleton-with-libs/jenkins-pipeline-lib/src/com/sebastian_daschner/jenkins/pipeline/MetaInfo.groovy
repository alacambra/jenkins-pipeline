package com.sebastian_daschner.jenkins.pipeline

import groovy.json.JsonOutput
import groovy.json.JsonSlurper

class MetaInfo implements Serializable {
    String projectName, buildVersion
    String commit, dockerImage
    String databaseVersion, databaseMigrationImage

    boolean unitTests, integrationTests, systemTests
    double testCoverage
    int cyclomaticComplexity

    String toJson() {
        return JsonOutput.prettyPrint(JsonOutput.toJson(this))
    }

    static MetaInfo fromJson(String json) {
        new JsonSlurper().parseText(json) as MetaInfo
    }

    @Override
    String toString() {
        "MetaInfo{" +
                "projectName='" + projectName + '\'' +
                ", buildVersion='" + buildVersion + '\'' +
                ", commit='" + commit + '\'' +
                ", dockerImage='" + dockerImage + '\'' +
                ", databaseVersion='" + databaseVersion + '\'' +
                ", databaseMigrationImage='" + databaseMigrationImage + '\'' +
                ", unitTests=" + unitTests +
                ", integrationTests=" + integrationTests +
                ", systemTests=" + systemTests +
                ", testCoverage=" + testCoverage +
                ", cyclomaticComplexity=" + cyclomaticComplexity +
                '}'
    }
}
