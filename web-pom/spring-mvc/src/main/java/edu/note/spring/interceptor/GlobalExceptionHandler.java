package edu.note.spring.interceptor;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

/**
 * @author jackylee
 * @date 2025-10-21 12:43
 */

@Slf4j
@RestControllerAdvice
// @ControllerAdvice(annotations = { RestController.class })
public class GlobalExceptionHandler {

    // 业务异常
    // @ExceptionHandler(BusinessException.class)
    // public String doBusinessException(BusinessException ex) {
    // return "Business fail...";
    // }

    // 系统异常
    @ExceptionHandler(RuntimeException.class)
    public String doRuntimeException(RuntimeException ex) {
        log.error("运行时异常: {}", ex);
        return "Runtime fail...";
    }

    // 其他非预期异常
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public String doException(HttpServletRequest req, Throwable ex) {
        log.error("异常: {}", ex);
        return "Unknown Exception...";
    }

}
