#!/bin/sh

if [ -n "$2" ] ;
then
    echo "参数中提供了环境信息"
    PROFILE="$2"
else
    PROFILE=dev
fi

echo "当前环境配置为$PROFILE"

pid=$(ps -ef | grep java | grep spring-boot | grep -v grep | awk '{print $2}')
count=`echo $pid | grep -v '^$' | wc -l`

basepath=$(cd `dirname $0`; pwd)
echo "basepath=$basepath"
logfile=/var/log/starrynight.log

echo "logfile=$logfile"

jarname=spring-boot:run

start(){
    # nohup java -jar $basepath/target/starrynight-0.0.1.jar -Dspring.profiles.active=$PROFILE > $logfile  2>&1 &
    # 检查是否已有进程在运行
    if [ $count -ne 0 ] ; then
        echo "已有$count个进程在运行,请停止后再尝试启动新进程"
        # 返回异常退出
        return 1
    fi
    # cd $basepath
    # nohup mvn spring-boot:run -Dmaven.test.skip=true -Dspring.profiles.active=$PROFILE > $logfile  2>&1 &
    cd $basepath
    mvn package -Dmaven.test.skip=true
    nohup java -jar $basepath/target/starrynight-0.0.1.jar -Dspring.profiles.active=$PROFILE > $logfile  2>&1 &
    if [ "$PROFILE"x = "dev"x ] ; then
        touch $logfile
        tailf $logfile
    fi
}

stop(){
    if [ `whoami` != 'root' ] ; then
        echo "需要以root权限执行"
        return 1
    fi
    # pid=`ps -ef | grep 'target/starrynight' | grep -v 'grep' | awk '{print $2}'`
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
    echo "Usage: (start|stop|restart) [dev|prd]"
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


