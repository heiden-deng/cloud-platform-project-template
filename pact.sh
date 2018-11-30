#!/bin/bash
NUM=1
Port_For_Listend="8090"
while true
do
  sleep 5
  if [ $NUM -gt 24 ];then
          exit 1
  fi
  Port_For_Check=$(netstat -tunpl |grep $Port_For_Listend)
  if [[ $Port_For_Check =~ $Port_For_Listend ]]
    then
  echo "Port $Port_For_Listend is already listend...."
  break
    else
  echo "Port $Port_For_Listend is not listend......"
  let NUM+=1
fi
done