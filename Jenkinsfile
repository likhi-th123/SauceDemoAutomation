pipeline {
    agent any

    tools {
        jdk 'Java-17'       // must match Jenkins tool name
        maven 'Maven-3.9.11'
    }

    environment {
        GIT_CREDENTIALS_ID = 'SauceDemo-Automation'
        BRANCH_NAME = 'main'
        COMMIT_MESSAGE = 'Jenkins: Auto-commit after build'
        GIT_WORKSPACE = 'C:\\Users\\HP\\git\\repository2\\SauceDemoAutomationProject'
    }

    triggers {
        pollSCM('H/5 * * * *')
    }

    stages {
        stage('Check Git Installation') {
            steps {
                bat 'git --version'
            }
        }

        stage('Checkout from Git') {
            steps {
                git branch: "${env.BRANCH_NAME}",
                    url: 'https://github.com/likhi-th123/SauceDemoAutomation.git'
            }
        }

        stage('Copy Files from Eclipse Workspace') {
            steps {
                bat """
                echo Copying files from Eclipse workspace...
                xcopy /E /Y /I "${GIT_WORKSPACE}\\*" "."
                """
            }
        }

        stage('Build & Test') {
            steps {
                bat 'mvn clean test -Dheadless=true -DsuiteXmlFile=src/test/resources/testng.xml'
            }
        }

        stage('Commit & Push Changes') {
            steps {
                script {
                    echo 'Checking for changes to commit and push...'
                    dir("${GIT_WORKSPACE}") {
                        withCredentials([usernamePassword(
                            credentialsId: "${env.GIT_CREDENTIALS_ID}",
                            usernameVariable: 'GIT_USER',
                            passwordVariable: 'GIT_TOKEN')]) {

                            bat """
                                REM Mark this directory as safe for SYSTEM user
                                git config --global --add safe.directory ${GIT_WORKSPACE}

                                git config user.email "likhithkumarv788@gmail.com"
                                git config user.name "likhi-th123"

                                REM Stage all changes
                                git add --all
                                git add --renormalize .

                                REM Commit & Push only if there are changes
                                git diff --cached --quiet
                                IF %ERRORLEVEL% NEQ 0 (
                                    echo "Changes detected, committing..."
                                    git commit -m "${COMMIT_MESSAGE}"
                                    git push https://%GIT_USER%:%GIT_TOKEN%@github.com/likhi-th123/SauceDemoAutomation.git HEAD:${BRANCH_NAME}
                                ) ELSE (
                                    echo "No changes to commit, skipping push"
                                )
                            """
                        }
                    }
                }
            }
        }
    }

    post {
        always {
            publishHTML([
                allowMissing: true,
                alwaysLinkToLastBuild: true,
                keepAll: true,
                reportDir: 'test-output',
                reportFiles: 'dashboard.html',
                reportName: 'TestNG Dashboard'
            ])
        }

        success {
            echo 'Build, test, and Git push completed successfully.'
        }
    }
}
