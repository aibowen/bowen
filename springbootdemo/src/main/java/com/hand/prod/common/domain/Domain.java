package com.hand.prod.common.domain;

import com.baomidou.mybatisplus.annotations.TableField;
import lombok.Data;

import java.time.ZonedDateTime;

/**
 * @description:
 * @author: bowen.wei@hand-china.com
 * @version: 1.0
 * @date: 2019-10-14 14:20
 */
@Data
public class Domain extends BaseObject {

    @TableField("create_by")
    private String createBy;
    @TableField("create_date")
    private ZonedDateTime createDate;
    @TableField("update_by")
    private String updateBy;
    @TableField("update_date")
    private ZonedDateTime udateDate;
}
