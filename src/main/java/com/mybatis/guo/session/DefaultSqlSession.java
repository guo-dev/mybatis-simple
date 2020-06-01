package com.mybatis.guo.session;

import com.mybatis.guo.binding.MapperProxy;
import com.mybatis.guo.config.Configuration;
import com.mybatis.guo.config.MappedStatement;
import com.mybatis.guo.executor.DefaultExecutor;
import com.mybatis.guo.executor.Executor;

import java.lang.reflect.Proxy;
import java.util.List;


/**
 * @author guoqinglin
 * @create 2020-06-01-15:32
 */
public class DefaultSqlSession implements  SqlSession{
    private  final Configuration configuration;

    private Executor executor;

    public DefaultSqlSession(Configuration configuration){
        super();
        this.configuration = configuration;
        executor = new DefaultExecutor(configuration);
    }


    @Override
    public <T> T selectOne(String statement, Object parameter) {
        List<Object> selectList = this.selectList(statement,parameter);
        if(selectList == null || selectList.size() == 0){
            return  null;
        }
        if(selectList.size() == 1){
            return (T) selectList.get(0);
        }else{
            throw new RuntimeException("Too Many Results!");
        }
    }

    @Override
    public <E> List<E> selectList(String statement, Object parameter) {
        MappedStatement ms = configuration.getMappedStatements().get(statement);
        return executor.query(ms,parameter);
    }

    @Override
    public <T> T getMapper(Class<T> type) {
        MapperProxy mp = new MapperProxy(this);
        return (T) Proxy.newProxyInstance(type.getClassLoader(),new Class[]{type},mp);
    }
}
