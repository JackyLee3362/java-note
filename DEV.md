---
type: basic-note
title: DEV
author: JackyLee
create_date: 2025-10-11
update_date:
tags:
description:
---

## VSCode 开发

`Java: Configure Java Runtime`

### 设置编译路径

.vscode/setting.json 中添加

```json
{
  "java.home": "<YOUR_JAVA_HOME>",
  "java.configuration.runtimes": [
    {
      "name": "JavaSE-1.8",
      "path": "<YOUR_JAVA_HOME>/bin/java",
      "default": true
    }
  ]
}
```

### 配置编译器

java.configuration.updateBuildConfiguration 设置为 always 编译配置将在每次保存文件时自动更新。

## 参考资料

- [VSCode 配置 Java 开发 - CyrusHuang - 博客园](https://www.cnblogs.com/cyrushuang/p/18731652)
