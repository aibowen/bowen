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
 * @date: 2019-10-14 14:29
 * @version： 1.0
 */
@TableName(value = TableUtil.TABLE_PREFIX + "role_operation")
@Data
public class RoleOperation extends Domain {

    @TableField(value = "role_id")
    private Long roleId;

    @TableField(value = "operation_id")
    private Long operationId;
}
