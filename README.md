# 校园互助信息平台（CIP）

[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-2.7.9-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![MyBatis-Plus](https://img.shields.io/badge/MyBatis--Plus-3.5.2-blue.svg)](https://baomidou.com/)
[![ChatGPT](https://img.shields.io/badge/ChatGPT-Integrated-orange.svg)](https://openai.com/)
[![Elasticsearch](https://img.shields.io/badge/Elasticsearch-Latest-yellow.svg)](https://www.elastic.co/)
[![Redis](https://img.shields.io/badge/Redis-Latest-red.svg)](https://redis.io/)

## 项目简介

校园互助信息平台（Campus Information Platform，简称CIP）是一个基于文本挖掘与分析的智能校园互助平台。本系统利用先进的AI技术和搜索引擎技术，为校园用户提供智能化的信息服务和互助支持。

## 技术栈

### 核心框架
- **Spring Boot 2.7.9**: 主框架
- **MyBatis-Plus 3.5.2**: ORM框架
- **MySQL + Druid**: 数据库及连接池
- **Redis**: 缓存服务
- **Elasticsearch**: 搜索引擎

### AI 与智能化
- **ChatGPT SDK**: 
  - chatgpt-java 1.0.5
  - chatgpt 4.0.1
- **文本挖掘分析**

### 开发工具
- **Swagger**: API文档（多UI支持）
- **Lombok**: 代码简化
- **Spring Boot DevTools**: 开发热重载

## 主要功能

### 1. 智能服务
- AI驱动的智能问答
- 文本分析与挖掘
- 智能信息推荐

### 2. 信息管理
- 信息发布与管理
- 全文检索
- 分类浏览

### 3. 用户服务
- 用户认证
- 邮件通知
- 个性化推荐

### 4. 系统功能
- 高性能搜索
- 缓存优化
- 实时通知

## 项目结构
```
src/main/java/com/uchain/cip/
├── config/         # 配置类
├── controller/     # 控制器层
├── enums/          # 枚举定义
├── mapper/         # MyBatis-Plus映射
├── pojo/           # 实体类
├── service/        # 服务层
├── tools/          # 工具类
└── vo/             # 视图对象
```

## 快速开始

### 环境要求
- JDK 1.8+
- Maven 3.6+
- MySQL 8.0+
- Redis
- Elasticsearch
- OpenAI API Key（用于ChatGPT功能）

### 配置步骤

1. 克隆项目
```bash
git clone https://github.com/Kickflip73/CIP.git
```

2. 配置数据库
```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/your_database
    username: your_username
    password: your_password
```

3. 配置Redis
```yaml
spring:
  redis:
    host: localhost
    port: 6379
```

4. 配置Elasticsearch
```yaml
spring:
  elasticsearch:
    uris: http://localhost:9200
```

5. 配置ChatGPT API（可选）
```yaml
chatgpt:
  api-key: your_api_key
```

6. 运行项目
```bash
mvn spring-boot:run
```

## API文档

启动项目后，可以通过以下地址访问API文档：
- Swagger UI: `http://localhost:8080/swagger-ui.html`
- Swagger Bootstrap UI: `http://localhost:8080/doc.html`

## 开发指南

### 代码规范
- 遵循阿里巴巴Java开发规范
- 使用统一的代码格式化工具
- 保持良好的代码注释

### 分支管理
- master: 主分支，用于发布
- develop: 开发分支
- feature/*: 功能分支
- hotfix/*: 紧急修复分支

## 部署说明

### 开发环境
- 配置 application-dev.yml
- 启用开发工具和调试功能

### 生产环境
- 配置 application-prod.yml
- 关闭开发者工具
- 优化JVM参数
- 配置日志级别

## 注意事项

1. 敏感配置信息请勿提交到版本控制
2. 本地开发请使用开发环境配置
3. API Key等敏感信息建议使用环境变量
4. 定期备份数据库

## 更新日志

### v1.0.0 (2023.03)
- 项目初始化
- 基础功能实现
- AI集成完成

## 贡献指南

1. Fork 本仓库
2. 创建功能分支
3. 提交变更
4. 发起 Pull Request

## 许可证

[MIT License](LICENSE)

## 联系我们

- 项目维护：刘煜燃|3065242502
- 技术支持：[3065242502@qq.com]
- 问题反馈：请提交 Issue

---
© 2023 校园互助信息平台. All Rights Reserved.
