package com.dls.web.mapper;


import com.dls.web.bean.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper{
    User selectUserByName(String userName);
}