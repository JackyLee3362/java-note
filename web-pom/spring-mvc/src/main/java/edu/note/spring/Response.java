package edu.note.spring;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author jackylee
 * @date 2025-10-14 16:22
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Response {

    private Integer code;
    private String message;
    private Object data;

    // final 修饰方法，方法不可重写
    public final static Response success(Object data) {
        return new Response(200, null, data);
    }

    public final static Response fail(Object data, String message) {
        return new Response(200, message, data);
    }

}
