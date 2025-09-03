package edu.note.thirft.anno.server;

import edu.note.thrift.BaseResponse;

public class UserServiceImpl implements UserService {
    @Override
    public BaseResponse hello(String name) {
        return BaseResponse.success(name);
    }

}
