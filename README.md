# CoffeeBucks
[![Build Status](https://travis-ci.com/WheelChen/CoffeeBucks.svg?branch=master)](https://travis-ci.com/WheelChen/CoffeeBucks)

> 线上咖啡厅项目

## 项目设计

### 项目时序图
![项目时序图](https://tva1.sinaimg.cn/large/00831rSTly1gcza1htaw9j30fm0iygn8.jpg)

![流程图](https://tva1.sinaimg.cn/large/00831rSTly1gczax4ap1jj30880kfdgs.jpg)

### 项目架构
![项目架构图](https://tva1.sinaimg.cn/large/00831rSTly1gczaqzh1cgj30ho0e6myx.jpg)

### 实体关系
![实体关系图](https://tva1.sinaimg.cn/large/00831rSTly1gczaw3fr5fj30me0bu75t.jpg)


## 技术组件
- 引入Redis缓存咖啡信息

- 使用Docker运行Redis
```docker
docker run -itd -p 6300:6379 redis:5.0.8
```