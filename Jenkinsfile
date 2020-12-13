podTemplate(containers: [
  containerTemplate(name: 'maven', image: 'maven:3-jdk-8-alpine', ttyEnabled: true, command: 'cat')
  ], volumes: [
  persistentVolumeClaim(mountPath: '/root/.m2/repository', claimName: 'jenkins-maven-pv-claim', readOnly: false)
  ]) {

  node(POD_LABEL) {
    stage('build') {
      container('maven') {
          ls -al
      }
    }
  }
}