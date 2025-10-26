package edu.note.spring.interceptor;

import javax.servlet.http.HttpServletRequest;

import org.omg.CORBA.SystemException;
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
// 同上 @ControllerAdvice(annotations = { RestController.class })
public class GlobalExceptionHandler {

    /**
     * 系统异常处理，比如：404,500
     *
     * @param req 请求
     * @param ex  错误
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public String doException(HttpServletRequest req, Throwable ex) {
        return "unknown fail...";
    }

    @ExceptionHandler(SystemException.class)
    public String doSystemException(SystemException ex) {
        return "System fail...";
    }

    // @ExceptionHandler(BusinessException.class)
    // public String doBusinessException(BusinessException ex) {
    // return "Business fail...";
    // }
}
