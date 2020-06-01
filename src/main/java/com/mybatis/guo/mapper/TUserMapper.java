package com.mybatis.guo.mapper;

import com.mybatis.guo.entity.TUser;

import java.util.List;

public interface TUserMapper {
	


    TUser selectByPrimaryKey(Integer id);
    
    List<TUser> selectAll();

    
}