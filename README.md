# 低代码平台 (Low Code Platform)

## 项目介绍

### 项目功能

- 管理系统
  - 登录页面
  - 主页面
  - 用户页面
  - 接口页面
  - 数据权限页面
  - 问卷页面
    - 问题编辑页面
    - 预览页面
    - 结果页面
  - 客户页面
  - 标签页面
  - docker容器化部署
  - 多环境配置
  - 单元测试

- 问卷 
  - 问卷的页面
  - docker容器化部署
  - 多环境配置
  - 单元测试

### 目前进度

主要功能未实现
- 各种参数校验 测试
- 问卷页面优化 （活动未开始，活动结束提醒， 参数校验功能， 报错提醒）
- elastic docker
- 定时器的执行
- 数据权限页面和接口 数据定义


其他页面基本功能实现，待完善和测试。

## 项目部署准备

RSA加密
```
keytool -genkey -alias jwtsigning -keyalg RSA -keystore keystore.jks  -keysize 2048
```
加密密码是：123456

