srcm-env
====
#### 进入项目所在目录
* cd ~/workspace/omnisocials
#### 关闭全部容器
* docker rm -f $(docker ps -aq)
#### 重置项目
##### 删除
* sudo rm -rf omnisocials-backend　omnisocials-frontend data log
##### 获取最新代码
* git pull
##### 初始化项目
* ./build.sh init
##### 获取 develop 分支代码, 更新子模块, 初始化 local
* ./build.sh up

#### 新建用户
* docker exec -it omnisocials-backend bash
* cd src/
* ./yii management/account/create dev@qq.com
* exit
#### 移除backend中的nikehbl文件夹
* cd omnisocials-backend/src/modules
* rm -rf nikehbl
#### 添加软链
* ln -s /srv/omnisocials-frontend/src/modules/nikehbl nikehbl
----
#### 进入开发模式(进入 docker 容器中, 类似进入一台 linux 系统)
* ./build.sh ssh
#### 查看后台module的关联情况
* 路径: /srv/omnisocials-backend/src/backend/modules#
* 命令: ll
#### link module
* 命令: grunt linkmodule:nelnmomclubwpc   grunt linkmodule:danoneaptamil
* 路径: /srv/omnisocials-backend/src#
----