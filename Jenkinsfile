pipeline {
   agent any

   tools {
      // Install the Maven version configured as "M3" and add it to the path.
      maven "M3"
   }

   stages {
      stage('Build') {
         steps {
            // Get some code from a GitHub repository
            git 'https://github.com/geisadedica/tw-itcase-tw.git'

            // Run Maven on a Unix agent.
            sh "mvn -Dmaven.test.failure.ignore=true "
            
            sh 'mvn -Dmaven.test.failure.ignore=true install'
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