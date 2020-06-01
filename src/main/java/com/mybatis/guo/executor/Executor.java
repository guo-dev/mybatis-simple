package com.mybatis.guo.executor;

import com.mybatis.guo.config.MappedStatement;

import java.util.List;

/**
 * @author guoqinglin
 * @create 2020-06-01-15:43
 * Mybatis核心接口之一，定义了数据库操作最基本的方法，SqlSession的功能是基于它实现的
 */
public interface Executor {

    /**
     * 查询接口
     * @param ms 疯转sql语句的MappedStatement对象
     * @param parameter 传入sql的参数
     * @return 将数据转换成指定对象结果集返回
     */


    <E> List<E> query(MappedStatement ms,Object parameter);

}
