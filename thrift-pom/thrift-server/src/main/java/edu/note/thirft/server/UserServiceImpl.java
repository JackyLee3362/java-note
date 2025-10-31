package edu.note.thirft.server;

import edu.note.thrift.BaseResponse;
import edu.note.thrift.UserService;

public class UserServiceImpl implements UserService {

    @Override
    public BaseResponse hello(String name) {
        return BaseResponse.success(name);
    }

}
