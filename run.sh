#!/bin/bash
export JAVA_HOME=/usr/lib/jvm/jre-21-openjdk
export PATH=$JAVA_HOME/bin:$PATH

#java -version

#mvn spring-boot:run
java -jar target/sampleproject-1.0-SNAPSHOT.jar
