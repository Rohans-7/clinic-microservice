pipeline {
    agent any
    triggers {
        githubPush()
    }

    environment {
        GITHUB_REPO_URL = 'https://github.com/Rohans-7/clinic-microservice.git'
        DOCTOR_DOCKER_IMAGE = 'imrds7/doctorms'
        PATIENT_DOCKER_IMAGE = 'imrds7/patientms'
        APPOINTMENT_DOCKER_IMAGE = 'imrds7/appointmentms'
    }

    stages {
        stage('Checkout') {
            steps {
                script {
                    git branch: 'main', url: "${GITHUB_REPO_URL}"
                }
            }
        }

        stage('Build Docker Images') {
            steps {
                script {
                    def doctorImage = docker.build("${DOCTOR_DOCKER_IMAGE}", './doctor-service/')
                    def patientImage = docker.build("${PATIENT_DOCKER_IMAGE}", './patient-service/')
                    def appointmentImage = docker.build("${APPOINTMENT_DOCKER_IMAGE}", './appointment-service/')

                    // Save them to env for next stage
                    env.DOCTOR_IMAGE_ID = doctorImage.id
                    env.PATIENT_IMAGE_ID = patientImage.id
                    env.APPOINTMENT_IMAGE_ID = appointmentImage.id
                }
            }
        }

        stage('Push Docker Images') {
            steps {
                script {
                    docker.withRegistry('', 'DockerHubCred') {
                        docker.image("${DOCTOR_DOCKER_IMAGE}").push()
                        docker.image("${PATIENT_DOCKER_IMAGE}").push()
                        docker.image("${APPOINTMENT_DOCKER_IMAGE}").push()
                    }
                }
            }
        }

        stage('Run Ansible Playbook') {
            steps {
                script {
                    withEnv(["ANSIBLE_HOST_KEY_CHECKING=False"]) {
                        ansiblePlaybook(
                            playbook: 'ansible-playbook.yaml',
                            inventory: 'inventory'
                        )
                    }
                }
            }
        }
    }

    // post {
    //     success {
    //         mail to: 'rohandinkar.sonawane@iiitb.ac.in',
    //              subject: "Application Deployment SUCCESS: Build ${env.JOB_NAME} #${env.BUILD_NUMBER}",
    //              body: "The build was successful!"
    //     }
    //     failure {
    //         mail to: 'rohandinkar.sonawane@iiitb.ac.in',
    //              subject: "Application Deployment FAILURE: Build ${env.JOB_NAME} #${env.BUILD_NUMBER}",
    //              body: "The build failed."
    //     }
    //     always {
    //         cleanWs()
    //     }
    // }
}
