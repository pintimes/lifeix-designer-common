package com.lifeix.designer;

import com.lifeix.designer.model.user.Device;
import com.lifeix.designer.model.user.User;
import com.lifeix.designer.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.ws.rs.NotAuthorizedException;

/**
 * Created by neoyin on 2017/1/13.
 */

@Component("userVerify")
public class UserVerify {

    @Autowired
    private RedisUtil redisUtil;

    public User verify(String userId,String deviceId)throws NotAuthorizedException {
        if (StringUtils.isEmpty(userId)||StringUtils.isEmpty(deviceId)) throw new NotAuthorizedException("user id or device id is null");
        User user = (User) redisUtil.getObject(User.class,userId);
        if (user==null)throw new NotAuthorizedException("not found this user");
        Device online = user.getOnline();
        if (online==null||!deviceId.equals(online.getDeviceId()))throw new NotAuthorizedException("this account login other deviceid");
        return user;
    }

}
