#!/bin/bash
source /etc/profile
APP_NAME=knowledge
PWDPATH=`dirname $0`
CW_INSTALL_DIR=`cd $PWDPATH && cd ../.. && pwd`
INSTALL_DIR=${CW_INSTALL_DIR}
LOG_DIR=${CW_LOG_DIR}
APP_HOME="${INSTALL_DIR}/${APP_NAME}"
APP_LOG_DIR="${LOG_DIR}/${APP_NAME}"


cd $APP_HOME
JVM_OPTS="
-server
 -d64
 -Xmx1024m
 -Xms512m 
 -Xss256K 
 -XX:+UseG1GC
 -XX:MaxGCPauseMillis=400
 -XX:InitiatingHeapOccupancyPercent=85
 -XX:G1ReservePercent=14
 -XX:G1HeapRegionSize=32M
 -XX:+PrintGCDetails
 -XX:-UseGCOverheadLimit
 -XX:+PrintGCDateStamps
 -Xloggc:${APP_LOG_DIR}/gc.log
"


. ./conf/dbchangelogs/liquibasedb.properties

#name=$(java -cp lib/cwop-commons-1.0.8.jar com.cloudwise.cwop.security.RSAUtils decrypt ${username})
name=${username}
#pwd=$(java -cp lib/cwop-commons-1.0.8.jar com.cloudwise.cwop.security.RSAUtils decrypt ${password})
pwd=${password}

update() {
    java $JVM_OPTS -jar lib/liquibase-core-3.5.3.jar  --driver=${driver}  --classpath=${classpath}  --changeLogFile=${changeLogFile}  --url=${url}   --username=${name##*:}  --password=${pwd##*:} update 2>&1 | tee -a $LOG_FILE_PATH
    echo -e '\r'
}



# See how we were called.
case "$1" in
  init)
    if update "$1" ; then
      RETVAL=0
      echo "Init has been successful." | tee -a "$LOG_FILE_PATH"
    else
      RETVAL=1
      echo "Init has been failed" | tee -a "$LOG_FILE_PATH"
    fi
    ;;
  update)
    if update "$1" ; then
      RETVAL=0
      echo "Update has been successful." | tee -a "$LOG_FILE_PATH"
    else
      RETVAL=1
      echo "Update has been failed" | tee -a "$LOG_FILE_PATH"
    fi
    ;;
  *)
    echo $"Usage: $prog {init|update}" | tee -a "$LOG_FILE_PATH"
    RETVAL=2
esac

echo "Script exit status: $RETVAL" | tee -a "$LOG_FILE_PATH"
exit $RETVAL

