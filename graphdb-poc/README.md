
Steps done to install NEO4J in a docker instance

Step1 

docker run -it --name=neo4j-db ubuntu-upstart /bin/bash

Step2
install oracle 8

sudo add-apt-repository ppa:webupd8team/java -y

sudo apt-get update

sudo apt-get install oracle-java8-installer

Step 3
wget -O - http://debian.neo4j.org/neotechnology.gpg.key >> key.pgp

sudo apt-key add key.pgp

echo 'deb http://debian.neo4j.org/repo stable/' | sudo tee -a /etc/apt/sources.list.d/neo4j.list > /dev/null

sudo apt-get update && sudo apt-get install neo4j

sudo service neo4j (stop|start|restart)
