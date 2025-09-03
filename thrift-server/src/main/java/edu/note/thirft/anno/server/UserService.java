package edu.note.thirft.anno.server;

import com.facebook.swift.service.ThriftMethod;
import com.facebook.swift.service.ThriftService;

import edu.note.thrift.BaseResponse;

/**
 * @author jackylee
 * @date 2025/9/3 10:23
 */
@ThriftService
public interface UserService {

    @ThriftMethod
    BaseResponse hello(String name);

}
