# 配置文件

项目一共分为4个配置文件,分别是

1. application.yml 基础系统配置文件，一般不用更改
2. application-dev.yml 开发配置文件，开发会自动使用该文件，打包会舍弃该文件。用于开发环境，
  可以配置数据库连接、log、端口等其他可能修改的配置
3. application-release.yml 正式环境配置文件，开发时该文件无效！打包成jar才会启动该配置文件。
  用于正式环境配置。
4. application-business.yml 业务配置文件。注意该文件不要写系统级别的配置。同时必须需要写映射bean。


# 打包

web 下的db目录用于存放数据库的脚本文件，打包后会自动放在压缩包的根目录

web 下的build\readme.txt 用于编写打包后的使用说明文件，会放在压缩包的根目录

打包命令 clean install 或者 clean package

打包后的jar需要使用提供的start.bat文件启动。（linux的启动文件还没写）

