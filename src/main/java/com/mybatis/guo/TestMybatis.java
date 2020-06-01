package com.mybatis.guo;


import com.mybatis.guo.entity.TUser;
import com.mybatis.guo.mapper.TUserMapper;
import com.mybatis.guo.session.SqlSession;
import com.mybatis.guo.session.SqlSessionFactory;

import java.util.List;

public class TestMybatis {
    public static void main( String[] args )
    {
        //1. 实例化SqlSessionFactory,加载数据库配置文件以及mapper.xml文件到configuration对象
        SqlSessionFactory factory = new SqlSessionFactory();
        //2. 获取SqlSession对象
        SqlSession session = factory.openSession();
        System.out.println(session);

        //3.动态代理对Mapper接口进行增强
        TUserMapper userMapper = session.getMapper(TUserMapper.class);

        //4.通过jdbc规范，及底层四大对象完成数据查询及转化
        TUser user = userMapper.selectByPrimaryKey(1);
        System.out.println(user);

        System.out.println("--------------------------");

        List<TUser> selectAll = userMapper.selectAll();
        if(selectAll != null && selectAll.size()>0){
            for(TUser tUser:selectAll){
                System.out.println(tUser);
            }
        }

    }
}
