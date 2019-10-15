package com.hand.prod.admin.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.hand.prod.admin.domain.User;
import com.hand.prod.admin.persistence.UserMapper;
import com.hand.prod.common.service.BaseService;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: bowen.wei@hand-china.com
 * @version: 1.0
 * @date: 2019-10-14 14:34
 */
@Service
public class UserService extends BaseService<UserMapper, User> {

    public boolean login(String username, String password) {
        int result = this.selectCount(new EntityWrapper<User>()
        .eq("username", username)
        .eq("password", password));

        if (result > 0) {
            return true;
        } else {
            return false;
        }
    }

}
