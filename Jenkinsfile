pipeline {
    agent any
    triggers {
      githubPush()
   }
    environment {
        GITHUB_REPO_URL = 'https://github.com/Rohans-7/clinic-microservice.git'
    }

    stages {
        stage('Checkout') {
            steps {
                script {
                    // Checkout the code from the GitHub repository
                    git branch: 'main', url: "${GITHUB_REPO_URL}"
                }
            }
        }

        // stage('Run Ansible Playbook') {
        //     steps {
        //         script {
        //         withEnv(["ANSIBLE_HOST_KEY_CHECKING=False"]) {
        //             ansiblePlaybook(
        //                 playbook: 'ansible-playbook.yaml',
        //                 inventory: 'inventory'
        //             )
        //         }
        //     }
        // }
        // }
}
//  post {
//         success {
//             mail to: 'rohandinkar.sonawane@iiitb.ac.in',
//                  subject: "Application Deployment SUCCESS: Build ${env.JOB_NAME} #${env.BUILD_NUMBER}",
//                  body: "The build was successful!"
//         }
//         failure {
//             mail to: 'rohandinkar.sonawane@iiitb.ac.in',
//                  subject: "Application Deployment FAILURE: Build ${env.JOB_NAME} #${env.BUILD_NUMBER}",
//                  body: "The build failed."
//         }
//         always {
//             cleanWs()
//         }
//       }
    }