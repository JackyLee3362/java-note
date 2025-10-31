package edu.note.thirft.sdk.anno;

import com.facebook.swift.codec.ThriftField;
import com.facebook.swift.codec.ThriftStruct;

/**
 * 1. 为什么 ThriftField 需要定义数字?
 * 字段 ID 的唯一性要求，用于序列化和反序列化时的识别字段
 * 2. 为什么 Getter 上定义数字，但是在 Setter 上不定义?
 * 原因: 框架会自动关联到同名字段，不需要重复定义
 * <p>
 * 按照这样约定使用即可，不用多想
 *
 * @author lijie206
 * @date 2025/9/3 10:54
 */
@ThriftStruct
public class BaseResponse {

    private Integer code;
    private String message;
    private String data;
    
    @ThriftField(1)
    public Integer getCode() {
        return code;
    }

    @ThriftField
    public void setCode(Integer code) {
        this.code = code;
    }

    @ThriftField(2)
    public String getMessage() {
        return message;
    }

    @ThriftField
    public void setMessage(String message) {
        this.message = message;
    }

    @ThriftField(3)
    public String getData() {
        return data;
    }

    @ThriftField
    public void setData(String data) {
        this.data = data;
    }

    public static BaseResponse success(String data) {
        BaseResponse response = new BaseResponse();
        response.setCode(200);
        response.setMessage("success");
        response.setData(data);
        return response;
    }

    public static BaseResponse fail(String message) {
        BaseResponse response = new BaseResponse();
        response.setCode(500);
        response.setMessage(message);
        response.setData(null);
        return response;
    }

}
