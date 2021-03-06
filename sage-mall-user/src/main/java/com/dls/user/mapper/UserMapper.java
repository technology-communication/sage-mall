package com.dls.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dls.user.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
