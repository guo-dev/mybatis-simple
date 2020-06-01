package com.mybatis.guo.session;

import java.util.List;

/**
 * @author guoqinglin
 * @create 2020-06-01-15:22
 * Mybatis暴露给外部的接口，实现增删改查的能力
 * 1.对外提供数据访问的api
 * 对内将请求转发给excutor
 */
public interface SqlSession {
    /**
     * 根据传入的条件查询单一结果
     * @param parameter 要传入的sql语句的查询参数
     * @param statement 方法对应的sql语句 namespace+id
     * @return 返回指定的结果对象
     */
    <T> T selectOne(String statement,Object parameter);

    /**
     * 根据条件查询，返回泛型集合
     * @return 返回指定结果对应的list
     */
    <E> List<E> selectList(String statement, Object parameter);

    /**
     * 根据mapper接口获取接口对应的动态代理实现
     * @param type 指定的mapper接口
     */
    <T> T getMapper(Class<T> type);

}
