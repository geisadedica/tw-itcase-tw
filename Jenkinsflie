@Library('cvc-jenkins-lib')
import br.com.cvccorp.jenkins.Commons
final commons = new Commons()

final _projectName = "caseit-tw"
final _namespace = "caseit-tw"
final _gitUrl = "https://github.com/geisadedica/tw-itcase-tw.git"


node {
  env.BU="tw"
  deployTI(_projectName, _namespace, _gitUrl, _appUrlTi) {
      stage('Pulling do codigo') {
          commons.cloneOrCheckoutTagFromGit(_gitUrl)
      }
      stage('Build') {
          commons.runMavenGoalsWithJava8('-P nexus clean package -Dmaven.test.skip=true')
      }
  }
}