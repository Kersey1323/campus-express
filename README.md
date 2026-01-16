# 校园快递管理系统 (School Express)

一个基于 Spring Boot + Vue.js + MySQL + Redis 的全栈校园快递管理平台，提供完整的快递下单、支付、配送管理功能。

## 📋 项目简介

校园快递管理系统是一个现代化的校园快递服务平台，旨在为校园内的学生和配送员提供便捷的快递服务。系统支持用户注册、快递下单、在线支付、订单管理、配送员管理等功能，实现了快递服务的全流程数字化管理。

## 🚀 技术栈

### 后端技术
- **框架**: Spring Boot 2.7.6
- **ORM**: MyBatis 3.0.0
- **数据库**: MySQL 8.0+
- **缓存**: Redis + Jedis
- **连接池**: Druid 1.1.16
- **JSON处理**: Fastjson 1.2.62
- **日志**: Log4j 1.2.17
- **图表**: JFreeChart 1.5.0
- **支付**: 支付宝 SDK 3.1.0 (沙箱环境)
- **构建工具**: Maven
- **Java版本**: JDK 1.8

### 前端技术
- **框架**: Vue.js
- **UI组件库**: Element UI 2.15.13
- **Cookie管理**: js-cookie 3.0.1

## 📁 项目结构

```
shcool-express/
├── src/main/java/com/kersey/
│   ├── config/           # 配置类（拦截器、Web配置）
│   ├── controller/       # 控制器（REST API）
│   │   ├── UserController.java      # 用户管理
│   │   ├── OrderController.java     # 订单管理
│   │   ├── AliPayController.java    # 支付宝支付
│   │   ├── DelOrderController.java  # 配送订单管理
│   │   ├── ChartController.java     # 图表统计
│   │   ├── UtilController.java      # 工具类
│   │   ├── Code.java                # 状态码定义
│   │   └── Result.java              # 统一返回结果
│   ├── mapper/          # MyBatis Mapper接口
│   ├── pojo/            # 实体类
│   └── service/         # 业务逻辑层
│       └── impl/        # 服务实现类
├── src/main/resources/
│   ├── static/          # 静态资源
│   │   ├── css/         # 样式文件
│   │   ├── imgs/        # 图片资源
│   │   ├── js/          # JavaScript文件
│   │   ├── pages/       # HTML页面
│   │   └── plugins/     # 第三方库（Element UI等）
│   ├── com/kersey/mapper/ # MyBatis XML映射文件
│   └── application.yml  # 配置文件
├── pom.xml              # Maven依赖配置
└── package.json         # 前端依赖配置
```

## 🎯 功能特性

### 1. 用户管理
- ✅ 用户注册与登录
- ✅ 验证码验证（Redis存储）
- ✅ 用户角色管理（普通用户、配送员）
- ✅ 用户信息查询与修改
- ✅ 用户积分系统

### 2. 订单管理
- ✅ 创建快递订单
- ✅ 订单号自动生成（Redis缓存）
- ✅ 订单查询、修改、删除
- ✅ 订单分页和条件查询
- ✅ 订单状态管理（待支付、已支付、配送中、已完成等）

### 3. 支付系统
- ✅ 集成支付宝支付（沙箱环境）
- ✅ 订单支付功能
- ✅ 批量支付功能
- ✅ 支付状态回调处理

### 4. 配送管理
- ✅ 配送员注册和管理
- ✅ 订单配送状态更新
- ✅ 订单拒绝功能
- ✅ 配送员订单分配

### 5. 数据统计与可视化
- ✅ 使用JFreeChart生成统计图表
- ✅ 订单数据可视化
- ✅ 用户角色分布统计
- ✅ 系统运行数据监控

### 6. 系统特性
- ✅ 前后端分离架构
- ✅ Redis缓存优化（验证码、订单号、用户会话）
- ✅ 登录拦截器安全机制
- ✅ 统一异常处理
- ✅ 响应式前端设计

## 🔧 环境要求

### 开发环境
- JDK 1.8+
- Maven 3.6+
- MySQL 8.0+
- Redis 5.0+
- Node.js 14+ (前端依赖)

### 数据库配置
```yaml
数据库名: shcool_express
用户名: root
密码: kerseytay13
端口: 3306
```

## 🚀 快速开始

### 1. 克隆项目
```bash
git clone <项目地址>
cd shcool-express
```

### 2. 数据库初始化
1. 创建MySQL数据库：
```sql
CREATE DATABASE shcool_express CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```

2. 执行SQL脚本（项目中的 `sql/` 目录下）

### 3. 配置修改
修改 `src/main/resources/application.yml` 中的数据库连接信息：
```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/shcool_express?serverTimezone=UTC
    username: your_username
    password: your_password
```

### 4. 启动Redis服务
```bash
# Windows
redis-server.exe

# Linux/Mac
redis-server
```

### 5. 构建和运行
```bash
# 使用Maven构建
mvn clean package

# 运行项目
java -jar target/shcool-express-0.0.1-SNAPSHOT.jar
```

### 6. 访问应用
- 后端API: `http://localhost:8080`
- 前端页面: `http://localhost:8080/static/pages/`

## 📊 API接口文档

### 用户相关接口
- `GET /users/{username}` - 根据用户名查询用户
- `POST /users` - 用户注册
- `POST /users/login` - 用户登录
- `PUT /users` - 更新用户信息
- `DELETE /users/{id}` - 删除用户

### 订单相关接口
- `GET /orders` - 生成订单号
- `GET /orders/{orderId}` - 根据订单号查询
- `POST /orders` - 添加订单
- `PUT /orders` - 更新订单
- `DELETE /orders/{orderId}` - 删除订单

### 支付相关接口
- `POST /alipay/pay` - 支付宝支付
- `POST /alipay/batchPay` - 批量支付
- `GET /alipay/return` - 支付回调

### 配送相关接口
- `GET /delorders` - 查询配送订单
- `PUT /delorders` - 更新配送状态
- `DELETE /delorders/{orderId}` - 拒绝配送订单

## 🛠️ 开发指南

### 项目导入
1. 使用IntelliJ IDEA或Eclipse导入Maven项目
2. 等待Maven依赖下载完成
3. 配置运行环境

### 代码规范
- 控制器类使用 `@RestController` 注解
- 服务层接口和实现分离
- 实体类使用Lombok简化代码
- 统一使用 `Result` 类返回结果
- 状态码定义在 `Code` 类中

### 数据库设计
主要数据表包括：
- `user` - 用户表
- `order` - 订单表
- `user_order` - 用户订单关联表
- `del_order` - 配送订单表

## 📈 性能优化

### 缓存策略
1. **Redis缓存**：
   - 验证码存储（5分钟过期）
   - 订单号生成缓存
   - 用户会话信息
   - 热点数据缓存

2. **数据库优化**：
   - Druid连接池配置
   - 合理的索引设计
   - 查询优化

### 安全措施
1. **输入验证**：所有用户输入进行验证
2. **SQL注入防护**：使用MyBatis参数绑定
3. **会话管理**：Redis存储用户会话
4. **验证码机制**：防止暴力破解

## 🤝 贡献指南

1. Fork 本仓库
2. 创建功能分支 (`git checkout -b feature/AmazingFeature`)
3. 提交更改 (`git commit -m 'Add some AmazingFeature'`)
4. 推送到分支 (`git push origin feature/AmazingFeature`)
5. 开启 Pull Request

## 📄 许可证

本项目采用 MIT 许可证 - 查看 [LICENSE](LICENSE) 文件了解详情

## 📞 联系方式

如有问题或建议，请通过以下方式联系：
- 项目维护者：[Kersey]
- 邮箱：[492700414@qq.com]
- 问题反馈：[GitHub Issues]

## 🙏 致谢

感谢以下开源项目：
- Spring Boot - 优秀的Java后端框架
- Vue.js & Element UI - 现代化的前端技术栈
- MyBatis - 简洁的ORM框架
- Redis - 高性能缓存数据库
- 支付宝开放平台 - 支付接口支持

---

**最后更新**: 2023年6月30日

**版本**: 0.0.1-SNAPSHOT

**状态**: 🚧 已完成