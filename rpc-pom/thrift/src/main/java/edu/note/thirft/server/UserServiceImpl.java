package edu.note.thirft.server;

import edu.note.thirft.sdk.anno.BaseResponse;
import edu.note.thirft.sdk.anno.UserService;

public class UserServiceImpl implements UserService {

    @Override
    public BaseResponse hello(String name) {
        return BaseResponse.success(name);
    }

}
