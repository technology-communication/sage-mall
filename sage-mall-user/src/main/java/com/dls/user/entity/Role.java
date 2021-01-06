package com.dls.user.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author 张金行
 * @Title: Product
 * @ProjectName sage-mall
 * @Description:
 * @date 2020/04/14 8:05
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@TableName("role")
public class Role implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableId
    private Long id;
    private String name;
    private String desc;
}
