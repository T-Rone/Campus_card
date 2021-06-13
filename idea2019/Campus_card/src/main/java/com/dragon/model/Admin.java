package com.dragon.model;

import com.baomidou.mybatisplus.annotation.TableField;
import jdk.nashorn.internal.objects.annotations.Constructor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.Date;

/**
 * @Author tdragon.
 * @Date 2021/5/30.
 * @Time 17:28
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Admin {
    private Long id;
    @TableField(value = "username")
    private String name;
    private String password;
    /**目前发现与数据库中字段create_time 直接映射成功了*/
    private Date createTime;
}
