# 低代码平台 (Low Code Platform)

## 项目介绍

  这是一个从0到1的一个练手项目。 虽然只是练手，但是我会尽可能的尝试最有的实现吧（比如说容器化部署），并且之后不断更新最新的软件版本，不断的优化功能细节和优化代码实现。

  ### 技术栈
  - Spring Boot 2.7
  - Elasticsearch(Spring-Boot-Data-Elasticsearch)
  - MySQL(Mybatis) 
  - Vue 3.x 
  - Element UI Plus

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

所有的流程都走通

登录后台管理 -> 创建问卷 -> 问卷提交 -> 问卷后台 -> 后台管理查看结果

未实现
- 完善接口权限的前后端，各种参数校验 测试
- 定时器的执行
- 数据权限页面和接口 数据定义
- 将Mapper全部变成Dao
- 添加日志功能
- 单元测试
- 工作流的前端页面和后端页面
- 希望将PostgreSQL替换掉MySQL，Elasticsearch，降低代码复杂度和资源

其他页面基本功能实现，待完善和测试。

## 项目部署

### 准备

RSA加密
```
keytool -genkey -alias jwtsigning -keyalg RSA -keystore keystore.jks  -keysize 2048
```
加密密码是：123456

### 启动
在script文件夹中有对应的bash脚本可以参考

```bash
mvn verify # 构建Java项目
docker-compose -f docker-compose-test.yml build --no-cache # 构建镜像
docker-compose -f docker-compose-test.yml up -d # 启动镜像
```

