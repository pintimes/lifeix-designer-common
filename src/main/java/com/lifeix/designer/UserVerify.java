package com.lifeix.designer;

import com.lifeix.designer.model.user.Device;
import com.lifeix.designer.model.user.User;
import com.lifeix.designer.utils.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.core.Response;

/**
 * Created by neoyin on 2017/1/13.
 */

@Component("userVerify")
public class UserVerify {

    public static final Logger LOGGER = LoggerFactory.getLogger(UserVerify.class);


    @Autowired
    private RedisUtil redisUtil;

    public User verify(String userId,String deviceId)throws NotAuthorizedException {
        if (StringUtils.isEmpty(userId)||StringUtils.isEmpty(deviceId)) throw new NotAuthorizedException("user id or device id is null", Response.status(Response.Status.UNAUTHORIZED));
        User user = (User) redisUtil.getObject(User.class,userId);
        if (user==null)throw new NotAuthorizedException("not found this user", Response.status(Response.Status.UNAUTHORIZED));
        Device online = user.getOnline();
        if (online==null||online.getDeviceId()==null||!deviceId.equals(online.getDeviceId())){
            LOGGER.warn(" this account login other deviceid "+deviceId +"==>"+user);
            throw new NotAuthorizedException("this account login other deviceid", Response.status(Response.Status.UNAUTHORIZED));
        }
        return user;
    }

}
