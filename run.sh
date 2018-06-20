#!/bin/sh

export ENVIRONMENT=prd
echo "当前环境为$ENVIRONMENT"

logfile=/var/log/starrynight.log
touch $logfile

start(){
    nohup java -jar target/starrynight-0.0.1.jar -Dspring.profiles.active=prd > $logfile  2>&1 &
    tailf $logfile
}

stop(){
    if [ `whoami` != 'root' ] ; then
        echo "需要以root权限执行"
        return 1
    fi
    pid=`ps -ef | grep 'java -jar target/starrynight' | grep -v 'grep' | awk '{print $2}'`
    count=`echo $pid | grep -v '^$' | wc -l`
    if [ $count  -ne 0 ] ; then
        echo "已有$count个进程"
        for id in $pid 
        do
            echo "准备杀死$id号进程"
            kill -9 $id
            echo "杀死了$id号进程"
        done
    else
        echo '没有已经启动的进程'
    fi
}
help(){
    echo "Usage: start|stop|restart"
}

case $1 in
    start)
        start
        ;;
    stop)
        stop
        ;;
    restart)
        restart
        ;;
    *)
        help
        ;;
esac


