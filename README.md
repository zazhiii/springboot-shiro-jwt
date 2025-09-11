# SpringBoot-Shrio-JWT
## 简介
本项目基于 Spring Boot 框架，结合 Shiro 权限管理和 JWT 无状态认证机制，实现了灵注解式认证与授权系统。

## 依赖环境
- JDK 8 或更高（推荐使用 JDK 17）
- Maven（用于构建项目）
- Git：用于克隆项目（可选）
  
## 部署
```shell
git clone https://github.com/zazhiii/springboot-shiro-jwt.git // 拉取代码

cd springboot-shiro-jwt // 进入项目目录

mvn clean package // 打包

java -jar target/springboot-shiro-jwt-1.0.jar // 启动项目
```
访问 http://localhost:8080/doc.html 进行测试
## TODO
1. 缓存角色权限信息，真实场景不会是每一次请求都去查询用户的权限和角色
2. 完成配套前端页面，包括页面、按钮级别的权限控制
