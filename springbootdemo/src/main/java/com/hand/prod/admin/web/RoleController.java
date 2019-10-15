package com.hand.prod.admin.web;

import com.hand.prod.admin.domain.Role;
import com.hand.prod.admin.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: bowen.wei@hand-china.com
 * @version: 1.0
 * @date: 2019-10-14 16:36
 */
@RestController
@RequestMapping("/api/role")
@Slf4j
public class RoleController {
    @Autowired
    private RoleService roleService;

    @PostMapping("/createRole")
    public boolean createRole(@RequestBody Role role) {
        return roleService.insert(role);
    }
}
