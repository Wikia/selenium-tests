git checkout ptaq-demo;
git pull;
mvn clean test -Dbrowser=FF -Dbase-address=http://mediawiki119.wikia.com -Denv=prod -Dgroups=PalantirTests
