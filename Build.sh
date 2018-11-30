#!/bin/bash
MAVEN_HOME=/usr/local/maven
export MAVEN_HOME
export PATH=${PATH}:${MAVEN_HOME}/bin
export JAVA_HOME=/usr/lib/jvm/jdk1.8.0_77
export JRE_HOME=${JAVA_HOME}/jre  
export CLASSPATH=.:${JAVA_HOME}/lib:${JRE_HOME}/lib  
export PATH=${JAVA_HOME}/bin:$PATH
echo  “Job目录：”$BUILD_REPOSITORY_LOCALPATH
cd $BUILD_REPOSITORY_LOCALPATH && mvn clean install

