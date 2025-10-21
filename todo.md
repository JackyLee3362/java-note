---
type: basic-note
title: todo
author: JackyLee
create_date: 2025-10-20
update_date:
tags:
description:
---
        
对于 RestController 的异常处理，非常巧妙

```java
@Slf4j
@ControllerAdvice(annotations = {RestController.class})
public class GlobalExceptionHandler {

    /**
     * 系统异常处理，比如：404,500
     *
     * @param req
     * @param ex
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Map<String, Object> defaultErrorHandler(HttpServletRequest req, Throwable ex) {
        Throwable e = ExceptionUtil.getRealThrowable(ex);
        String uri = req != null ? req.getRequestURI() : null;
        ExceptionMsg exceptionMsg = new ExceptionMsg(uri, e);
        Map<String, String> map = Maps.newHashMap();
        map.put("errorCode", exceptionMsg.getErrorCode());
        map.put("errorMsg", exceptionMsg.getErrorMessage());
        CatUtil.business("前端接口报错码", map);
        return AjaxResult.fail(exceptionMsg.getErrorCode(), exceptionMsg.getErrorMessage());
    }
}
```

## 问题2: handlerInterceptorAdapter 和 Filter 的顺序

```java
class HandlerInterceptorAdapter
```

## 问题: 区分 java servlet 和 apache tomcat 的区别

- 最好还有 spring-mvc


## 参考资料
