#!/bin/bash
echo  “Job目录：”$BUILD_REPOSITORY_LOCALPATH
cd $BUILD_REPOSITORY_LOCALPATH && mvn clean install