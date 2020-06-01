package com.mybatis.guo.executor;

import com.mybatis.guo.config.Configuration;
import com.mybatis.guo.config.MappedStatement;
import com.mybatis.guo.reflection.ReflectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author guoqinglin
 * @create 2020-06-01-15:47
 */
public class DefaultExecutor implements Executor{
    private  final Configuration configuration;

    public DefaultExecutor(Configuration configuration){
        super();
        this.configuration = configuration;
    }


    /**
     * 查询接口
     * @param ms  封装sql语句的MappedStatement对象
     * @param parameter 传入sql的参数
     * @return  将数据转换成指定对象结果集返回
     */
    @Override
    public <E> List<E> query(MappedStatement ms, Object parameter) {
        List<E> ret = new ArrayList<>();//定义返回结果集
        try {
            Class.forName(configuration.getJdbcDriver());
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            //获取连接，从MappedStatement中获取数据库
            connection = DriverManager.getConnection(configuration.getJdbcUrl(),configuration.getJdbcUsername(),configuration.getJdbcPassword());
            //prepareStatement，从MappedStatement中获取sql语句
            preparedStatement = connection.prepareStatement(ms.getSql());
            //处理sql语句中的占位符
            parameterize(preparedStatement,parameter);
            //执行查询操作获取resultSet
            resultSet = preparedStatement.executeQuery();
            //将结果通过反射技术，填充到list中
            handlerResultSet(resultSet,ret,ms.getResultType());


        }catch (SQLException e){
            e.printStackTrace();

        }finally {
            try {
                resultSet.close();
                preparedStatement.close();
                connection.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        return ret;
    }

    //对prepareStatement中的占位符进行处理
    private void parameterize(PreparedStatement preparedStatement,Object parameter) throws SQLException{
        if(parameter instanceof Integer){
            preparedStatement.setInt(1,(int)parameter);
        }else if(parameter instanceof Long){
            preparedStatement.setLong(1,(long)parameter);
        }else if(parameter instanceof String){
            preparedStatement.setString(1,(String)parameter);
        }
    }

    //读取resultSet中的数据，并转换成目标对象
    private <E> void handlerResultSet(ResultSet resultSet,List <E> ret, String className){
        Class<E> clazz = null;

        try {
            //通过反射获取类对象
            clazz = (Class<E>) Class.forName(className);
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }

        try {
            while (resultSet.next()){
                //通过反射实例化对象
                Object entity = clazz.newInstance();
                //使用反射工具类将resultSet中的数据填充到entity中
                ReflectionUtil.setPropToBeanFromResultSet(entity,resultSet);
                //对象加入返回集合中
                ret.add((E) entity);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


}
