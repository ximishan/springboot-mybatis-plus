# springboot-mybatis-plus

spring boot 集成mybatis-plus, swagger

### mybatis-plus 
1.使用了mybatis-plus集成的分页插件和代码生成功能。

2.mybatis-plus 详细使用功能参考：https://baomidou.gitee.io/mybatis-plus-doc/#/quick-start
+ mybatis-plus 代码生成功能在MpGenerator类中。    
+ 作者名称替换。     
+ 数据库连接地址替换。  
+ 生成的表名替换。    
+ 生成的xml文件在resource/mapper下。    
+ 需要注意的是，mybatis-plus生成的实体默认主键是id，如果自定义表的主键不是id，需要使用注解@TableId()明确指定。

3.集成valid参数校验。

4.统一处理异常信息。查看 ExceptionControllerAdvice 类。

5.格式化接口调用的返回参数。查看 ResponseControllerAdvice 类。

6.aop 查询接口的调用性能。根据需要自定义保存到数据库中。
    添加打印接口的请求参数。
    
7.使用 logback 打印日志。

8.master 分支无权限校验，无redis，无多个配置文件。