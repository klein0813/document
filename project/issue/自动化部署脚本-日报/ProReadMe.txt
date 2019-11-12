生产环境自动化部署步骤

1. (在生产机中)关闭监控脚本，由于监控脚本是ROOT权限执行的，而自动化脚本是user执行的，所以需要先关闭监控脚本。
   监控脚本脚本用于监控：Mysql, Nginx, Redis, RabbitMQ 以及运行项目的那个Jar包的进程，监控这些进程是否存在，如果不存在会自动去启动他们。

   1.1 禁止监控脚本执行，执行如下命令。
       sudo crontab -e

       例如：显示如下：
       #
       # For more information see the manual pages of crontab(5) and cron(8)
       #
       # m h  dom mon dow   command
       */15 * * * * bash /home/user/launch-script/monitor.sh

   1.2 将monitor.sh所在行注释掉。
       例如：显示如下：
       #
       # For more information see the manual pages of crontab(5) and cron(8)
       #
       # m h  dom mon dow   command
       # */15 * * * * bash /home/user/launch-script/monitor.sh

2. (在生产机中)杀死项目监控脚本对应的Java进程。
   2.1 得到运行SpringBoot运行项目Jar包的对应的进程ID。
       sudo lsof -i:9999 
       注意：这里的9999是指端口号，并且这个端口号是确定不会变化的，就是9999

       例如，显示如下，得到的进程ID是1590
       sudo lsof -i:9999 
       COMMAND  PID USER   FD   TYPE DEVICE SIZE/OFF NODE NAME
       java    1590 root   29u  IPv6  18303      0t0  TCP *:9999 (LISTEN)

   2.2 根据得到PID为1590，杀死运行项目Jar包的进程
       sudo kill -9 1590
	
3. (在生产机中)生产环境所需软件安装，目前生产环境已经安装了所有所需的软件，所以请跳过此步骤。
   如果特殊情况更换的服务器并且没有安装这些软件，才需要执行此步骤。
	
   3.1 安装dtach，用来后台执行部署Jar包的的软件，执行如下命令。
       sudo apt-get install dtach
		
   3.2 安装ssh，用来允许远程链接服务器的软件，执行如下命令。
       sudo apt-get install openssh-server	

4. (可能在本地或者测试环境中执行的)执行自动化部署脚本文件所在机器的软件，如果你在本地执行，就要安装在本地。如果你在生产环境执行，就要安装在生成环境。
   但是由于生产环境链接不到GIT所以不能下载代码，所以目前生产环境没有安装此软件，所以无法在生产环境运行自动化部署脚本。
   只能在本地运行生产环境的自动化部署脚本，来自动化部署到生产环境。

   4.1 安装ssh，用来允许远程链接服务器的软件，执行如下命令。
       sudo apt-get install openssh-server

   4.2 给自动化部署脚本设置免密登陆服务器，默认是不免密的，所以我们需要手动设置为免密登陆。

       4.2.1 获取密钥，命令如下：
             注意：如果SSH已经安装，并且此文件(/home/user/.ssh/id_rsa.pub)不存在，就需要执行如下命令。如果已经存在了的话就不用执行这条命令了。
             ssh-keygen -t rsa

       4.2.2 传送公钥，如果你的机器曾经执行过此条命令，则表示已经允许免密登陆了，所以以后都不用执行了。当然你每次执行也是没有问题的。命令如下：
             ssh-copy-id -i ~/.ssh/id_rsa.pub user@172.26.20.233

   4.3 安装编译SpringBoot的后台代码所需要的maven软件，命令如下。
       sudo apt-get install maven
	  
   4.4 安装运行执行自动化部署脚本的软件，自动化部署脚本是用python写的，所以需要安装Python。
   
       一般情况下Linux都已经默认安装过python的，所以需要查看一下系统是否已经安装，执行如下命令：
       python --version
     
       如果有输出结果，则表示已经安装，要安装两个组件，执行如下命令。
       安装pip组件命令如下：
       sudo apt-get install python-pip

       安装fabric组件命令如下：
       sudo pip install fabric3

       如果没有输出结果，则表示没有安装，要按照Python以及两个组件，执行如下命令。

       安装python命令如下：
       sudo apt-get install python2.7

       安装pip组件命令如下：
       sudo apt-get install python-pip
		
       安装fabric组件命令如下：
       sudo pip install fabric3          
	
5. (可能在本地或者测试环境中执行的)进入部署脚本所在目录，具体目录看你机器的项目所在目录。
   例：cd ~/project/DailyReport/deploy-config/

	  
6. (可能在本地或者测试环境中执行的)执行自动化部署脚本，命令如下：
   fab -f dailyreport-pro.py deploy:pro

   注意：生产环境自动化部署脚本里面，拉的是master分支的代码。如果需要修改分支，请更改dailyreport-pro.py 52行并注释掉 51 行。
   例如: local('git reset --hard')       改成 =》# local('git reset --hard')
        local('git pull origin master') 改成 =》local('git pull origin develop') 

7. (在生产机中)172.26.20.233中验证部署是否成功
   
   脚本执行完成，不意味着部署成功，jar包的部署是后台执行，需等会儿，大概2-3分钟，可通过如下命令来验证是否启动成功了。
   
   查看运行SpringBoot运行项目Jar包的对应的进程ID，如果有进程则表示启动成功了，否则表示没有启动成功，可能自动化部署有问题。
   lsof -i:9999 
   
   如果执行自动化部署脚本输出的日志都没有问题，但是启动失败了没有找到对应的进程ID，则查看运行项目Jar包的日志文件
   /home/user/dailyreport/log/dailyreport.log

8. 自动化部署脚本完成、项目跑起来后，允许监控脚本执行，操作步骤类似步骤1，将monitor.sh所在行前的‘#’去掉。
   则这个脚本会继续监控Mysql, Nginx, Redis, RabbitMQ 以及运行项目的那个Jar包的进程，监控这些进程是否存在，如果不存在会自动去启动他们。