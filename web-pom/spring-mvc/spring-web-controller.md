---
type: basic-note
title: spring-web-controller
author: JackyLee
create_date: 2025-10-14
update_date:
tags:
description:
---

## 执行顺序

1. 

```ascii
┌─────────────────────────────────────────────┐
│ web container                               │
│    ┌──────────────────────────────┐         │
│    │ servlet-context              │         │
│    │ ┌─────────────────────────┐  │         │
│    │ │ web-application-context │  │         │
│    │ │                         │  │         │
│    │ │ ┌───────────────────┐   │  │         │
│    │ │ │                   │   │  │         │
│    │ │ │  user-controller  │   │  │         │
│    │ │ │                   │   │  │         │
│    │ │ └───────────────────┘   │  │         │
│    │ └─────────────────────────┘  │         │
│    └──────────────────────────────┘         │
└─────────────────────────────────────────────┘
```

- [Spring Boot Test 的详细使用教程 - gongchengship - 博客园](https://www.cnblogs.com/gongchengship/p/18540901)

## 参考资料
