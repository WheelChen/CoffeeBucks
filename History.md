# Coffee Bucks 更新日志

## 更新日期 - 20200319
- 创建Coffee/CoffeeOrder实体类，搭建基本框架
- 引入CacheRepository,进行Redis缓存操作
- 针对特定Money类型进行自定义Converter方便存入缓存

- 引入travis 进行持续集成控制
- 引入checkstyle加强代码规范

## 更新日期 - 20200321 - 01
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