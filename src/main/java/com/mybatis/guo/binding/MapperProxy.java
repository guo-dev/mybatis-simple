package com.mybatis.guo.binding;

import com.mybatis.guo.session.SqlSession;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Collection;

/**
 * @author guoqinglin
 * @create 2020-06-01-17:56
 */
public class MapperProxy implements InvocationHandler {
    private SqlSession session;
    public MapperProxy(SqlSession session){
        super();
        this.session = session;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Class<?> returnType = method.getReturnType();
        if(Collection.class.isAssignableFrom(returnType)){
            return session.selectList(method.getDeclaringClass().getName()+"."+method.getName(),args==null?null:args[0]);
        }else{
            return session.selectOne(method.getDeclaringClass().getName()+"."+method.getName(),args==null?null:args[0]);
        }
    }
}
