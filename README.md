# mybatis-simple
手写简单mybatis

实现思路
1. 创建SqlSessionFactory实例；
2. 实例化过程中，加载配置文件创建configuration对象；
3. 通过factory创建SqlSession；
4. 通过SqlSession获取mapper接口动态代理
5. 动态代理回调sqlsession中某查询方法；
6. SqlSession将查询方法转发给Executor；
7. Executor基于JDBC访问数据库获取数据；
8. Executor通过反射将数据转换成POJO并返回；给SqlSession；
9. 将数据返回给调用者；

