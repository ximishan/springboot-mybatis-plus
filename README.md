# springboot-mybatis-plus

spring boot 集成mybatis-plus, swagger

### mybatis-plus 
1.使用了mybatis-plus集成的分页插件和代码生成功能。

2.mybatis-plus 详细使用功能参考：https://baomidou.gitee.io/mybatis-plus-doc/#/quick-start
+ 2.1 mybatis-plus 代码生成功能在MpGenerator类中。    
+ 2.2 作者名称替换。     
+ 2.3 数据库连接地址替换。  
+ 2.4 生成的表名替换。    
+ 2.5 生成的xml文件在resource/mapper下。    
+ 2.6 需要注意的是，mybatis-plus生成的实体默认主键是id，如果自定义表的主键不是id，需要使用注解@TableId()明确指定。

3.集成valid参数校验。

4.统一处理异常信息。查看 ExceptionControllerAdvice 类。

5.格式化接口调用的返回参数。查看 ResponseControllerAdvice 类。

6.aop 查询接口的调用性能。根据需要自定义保存到数据库中。

7.待补充