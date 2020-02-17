pipeline {
   agent any

   stages {
      stage('Build') {
         steps {
            // Get some code from a GitHub repository
            git 'https://github.com/geisadedica/tw-itcase-tw.git'

            // Run Maven on a Unix agent.
            sh "mvn -Dmaven.test.failure.ignore=true "
            
            sh 'mvn -Dmaven.test.failure.ignore=true clean package'
         }

         post {
            success {
               junit '**/target/surefire-reports/TEST-*.xml'
               archiveArtifacts 'target/*.jar'
            }
         }
      }
   }
}