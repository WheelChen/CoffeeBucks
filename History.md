# Coffee Bucks 更新日志

## 更新日期 - 20200319
- 创建Coffee/CoffeeOrder实体类，搭建基本框架
- 引入CacheRepository,进行Redis缓存操作
- 针对特定Money类型进行自定义Converter方便存入缓存

- 引入travis 进行持续集成控制
- 引入checkstyle加强代码规范

## 更新日期 - 20200321
- 使用AOP实现DAO层的性能监控 - PerformanceAspect
- 使用p6spy进行SQL日志的输出
- 规范maven依赖版本管理
- 分离h2 SQL文件

## 更新日期 - 20200330
- 实现WebMvcConfigurer
- 定义校验Validator

## 更新日期 - 20200331
- 使用Jackson进行序列化与反序列化

## 更新日期 - 20200402
- 添加异常处理

## 更新日期 - 20200403
- 添加web层拦截器 - PerformanceInterceptor

## 更新日期 - 20200415
- restructure 项目结构,改为多module结构

## 更新日期 - 20200416
- 使用RestTemplate进行Http简单请求
- 增加对Money的序列化/反序列化处理、使用ParameterizedTypeReference处理泛型对象的接收
- 增加http连接相关配置

## 更新日期 - 20200419
- 使用spring-data-rest实践HATEOAS,可以实现带分页的请求。例如：
```text
http://127.0.0.1:8080/coffee?page=0&size=3&sort=id,desc

http://localhost:8080/coffee/search
```

## 更新日期 - 20200425
- 实践超媒体服务

## 更新日期 - 20200506
- 引入Actuator，为监控咖啡数量添加health indicator.