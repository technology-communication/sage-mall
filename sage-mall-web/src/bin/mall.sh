#!/bin/bash
PWDPATH=`dirname $0`
APP_NAME=knowledge
CW_INSTALL_DIR=`cd $PWDPATH && cd ../.. && pwd`
INSTALL_DIR=${CW_INSTALL_DIR}
LOG_DIR=${CW_LOG_DIR}
APP_HOME="${INSTALL_DIR}/${APP_NAME}"
APP_LOG_DIR="${LOG_DIR}/${APP_NAME}"
COMM_HOME=${APP_HOME}
cd $COMM_HOME
MAINCLASS="com.cloudwise.knowledge.KnowledgeApplication"
start () {
    JVM_OPTS="
     -server
     -Xms2g
     -Xmx2g
     -XX:+AlwaysPreTouch
     -XX:+UseG1GC
     -XX:MaxGCPauseMillis=2000
     -XX:GCTimeRatio=4
     -XX:InitiatingHeapOccupancyPercent=30
     -XX:G1HeapRegionSize=8M
     -XX:ConcGCThreads=2
     -XX:G1HeapWastePercent=10
     -XX:+UseTLAB
     -XX:+ScavengeBeforeFullGC
     -XX:+DisableExplicitGC
     -XX:+PrintGCDetails
     -Xloggc:${CW_LOG_DIR}/gc.log
     -XX:-UseGCOverheadLimit
     -XX:+PrintGCDateStamps
     -XX:+HeapDumpOnOutOfMemoryError
     -XX:HeapDumpPath=${CW_LOG_DIR}/dump.dump
     -Dlog4j2.configurationFile=conf/log4j2.xml
     -Dspring.config.location=conf/
    "
    export CLASSPATH=$JAVA_HOME/jre/lib/*:$JAVA_HOME/lib/*:$COMM_HOME/lib/*:$COMM_HOME/conf/*
    export MAINCLASS="com.cloudwise.knowledge.KnowledgeApplication"
    case $1 in
    -b )
        nohup java $JVM_OPTS -cp $CLASSPATH $MAINCLASS  --logging.config=conf/log4j2.xml --spring.cloud.nacos.discovery.enabled=true 1>/dev/null 2>&1 &
    ;;
    -d )
        java $JVM_OPTS -classpath $CLASSPATH $MAINCLASS --logging.config=conf/log4j2.xml --spring.cloud.nacos.discovery.enabled=true
    ;;
    esac
    echo -e '\r'
}

case $1 in
restart )
    echo stop
    PID=`ps avx|grep $COMM_HOME |grep $MAINCLASS|grep -v 'grep'|awk '{print $1}'`
    if  [ ! -n "$PID" ] ;then
       echo "The current process does not exist."
    else
       kill $PID
       echo "The process has been successfully stopped."
    fi

    echo start
    if [ ! -n "$2" ] ;then
	echo "After start, you must add parameters -d or -b. See help for details."
    else
        start $2 -b
    fi
;;
start )
    echo start
    if [ ! -n "$2" ] ;then
	echo "After start, you must add parameters -d or -b. See help for details."
    else
        start $2
    fi
;;
stop )
    echo stop
    PID=`ps avx|grep $COMM_HOME |grep $MAINCLASS|grep -v 'grep'|awk '{print $1}'`
    if  [ ! -n "$PID" ] ;then
       echo "The current process does not exist."
    else
       kill $PID
       echo "The process has been successfully stopped."
    fi
;;
pid )
    PID=`ps avx|grep $COMM_HOME |grep $MAINCLASS|grep -v 'grep'|awk '{print $1}'`
    if  [ ! -n "$PID" ] ;then
       echo "The current process does not exist."
    else
       echo "pid : "${PID}
    fi
;;
status )
    PID=`ps avx|grep $COMM_HOME |grep $MAINCLASS|grep -v 'grep'|awk '{print $1}'`
    if  [ ! -n "$PID" ] ;then
       echo "dead"
    else
       echo "running"
    fi
;;
help )
    echo 'start    -d or -b     Start the service DEBUG mode or background mode.'
    echo 'stop                  Stop the service running in the background.'
    echo 'pid                   Gets the current process id information.'
    echo 'status                Gets the current service status information.'
;;
* )
    echo Command error, please enter help
;;
esac
