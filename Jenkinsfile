node('ubuntu') {
  stage 'Build'
    sh 'echo "BUILDING"'

  stage 'Unit Tests'
    sh 'bash ./test.sh'

  stage 'Functional Tests'
    sh 'bash ./test.sh'
}
