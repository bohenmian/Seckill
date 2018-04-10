 [![Build Status](https://travis-ci.org/bohenmian/Seckill.svg?branch=master)](https://travis-ci.org/bohenmian/Seckill)
 慕课网高并发秒杀课程 
================================================================================

## How to play
1. git clone `https://github.com/bohenmian/Seckill`
2. open IDEA -->  File  -->  New  --> Open 
3. choose seckill's pom.xml，open it
4. update the `jdbc.properties` files about your mysql's username and password
5. deploy the tomcat，and start up
6. enter in the browser: `http://localhost:8080/seckill/list`

## Develop environment
利用`spring` + `Mybaits`框架实现
* `Mybatis`实现`DAO`层访问
* `spring`实现`DAO`层、`Service`层的注入
* `Spring-mvc`实现`Controller`的注入

## 视频地址
[Java高并发秒杀API之业务分析与DAO层](http://www.imooc.com/learn/587)
[Java高并发秒杀API之Service层](http://www.imooc.com/learn/631)
[Java高并发秒杀API之web层](http://www.imooc.com/learn/630)
[Java高并发秒杀API之高并发优化](http://www.imooc.com/learn/632)
