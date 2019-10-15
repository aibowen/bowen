package com.hand.prod.admin.domain;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.hand.prod.common.domain.Domain;
import com.hand.prod.common.utils.TableUtil;
import lombok.Data;

/**
 *
 *
 * @author: bowen.wei@hand-china.com
 * @date: 2019-10-14 14:27
 * @version： 1.0
 */
@TableName(value = TableUtil.TABLE_PREFIX + "user")
@Data
public class User extends Domain {

	@TableField(value = "username")
	private String username;

	@TableField(value = "password")
	private String password;

	@TableField(value = "fullname")
	private String fullname;

	@TableField(value = "role_id")
	private String roleId;

	@TableField(value = "is_disabled")
	private Boolean isDisabled;

}
