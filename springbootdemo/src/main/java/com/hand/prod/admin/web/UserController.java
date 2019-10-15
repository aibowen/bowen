package com.hand.prod.admin.web;

import com.hand.prod.admin.domain.User;
import com.hand.prod.admin.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


/**
 * @description:
 * @author: bowen.wei@hand-china.com
 * @version: 1.0
 * @date: 2019-10-14 14:36
 */

@RestController
@Slf4j
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/login")
    public boolean login(@RequestParam("username") String username,
                         @RequestParam("password") String password) {
        return userService.login(username, password);
    }

    @PostMapping(value = "/createUser")
    public boolean createUser(@RequestBody User user) {
        return userService.insert(user);
    }
}
