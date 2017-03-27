node('linux') {

  checkout scm

  stage 'Build'
    sh 'bash ./build.sh'

  stage 'Unit Tests'
    sh 'bash ./test.sh'

  stage 'Functional Tests'
    sh 'bash ./test.sh'
}
