package edu.note.spring.interceptor;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import edu.note.spring.Response;
import lombok.extern.slf4j.Slf4j;

/**
 * @author jackylee
 * @date 2025-10-21 12:43
 */

@Slf4j
@ControllerAdvice(annotations = { RestController.class })
public class GlobalExceptionHandler {

    /**
     * 系统异常处理，比如：404,500
     *
     * @param req 请求
     * @param ex  错误
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Response defaultErrorHandler(HttpServletRequest req, Throwable ex) {
        return Response.fail(null, "Global Error");
    }
}
