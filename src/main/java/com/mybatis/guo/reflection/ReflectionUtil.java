package com.mybatis.guo.reflection;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author guoqinglin
 * @create 2020-06-01-16:49
 */
public class ReflectionUtil {
    /**
     * 为指定bean的propName属性的值设置value
     *
     * @param bean 目标对象
     * @param propName 对象的属性名
     * @param value 值
     */
    public static void setPropToBean(Object bean,String propName, Object value){
        Field f;
        try {
            f = bean.getClass().getDeclaredField(propName);//获得对象指定的属性
            f.setAccessible(true);//将字段设置为可通过反射进行访问
            f.set(bean,value);
        }catch (NoSuchFieldException e) {
            e.printStackTrace();
        }catch (SecurityException e){
            e.printStackTrace();
        }catch (IllegalAccessException e){
            e.printStackTrace();
        }catch (IllegalArgumentException e){
            e.printStackTrace();
        }
    }

    /**
     *
     * 从resultSet中读取一行数据，并填充至指定的实体bean
     *
     * @param entity  待填充的实体bean
     * @param resultSet  从数据库加载的数据
     * @throws SQLException
     */
    public static void setPropToBeanFromResultSet(Object entity, ResultSet resultSet) throws SQLException {
        Field[] declaredFields = entity.getClass().getDeclaredFields();//通过反射获取对象所有的字段
        for(int i = 0; i<declaredFields.length; i++){//遍历所有的字段，从resultSet中读取响应一数据，并填充至对象的属性中
            if(declaredFields[i].getType().getSimpleName().equals("String")){//如果是字符串类型数据
                setPropToBean(entity,declaredFields[i].getName(),resultSet.getString(declaredFields[i].getName()));
            }else if(declaredFields[i].getType().getSimpleName().equals("Integer")){//如果是int类型数据
                setPropToBean(entity,declaredFields[i].getName(),resultSet.getInt(declaredFields[i].getName()));
            }else if(declaredFields[i].getType().getSimpleName().equals("Long")){
                setPropToBean(entity,declaredFields[i].getName(),resultSet.getLong(declaredFields[i].getName()));
            }
        }

    }
}
