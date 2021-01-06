package com.dls.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dls.user.entity.UserRole;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author royzhang
 * @version 1.0
 * @date 2020/12/29 10:01 下午
 * @desc
 */

@Repository
@Mapper
public interface UserRoleMapper extends BaseMapper<UserRole> {
}
