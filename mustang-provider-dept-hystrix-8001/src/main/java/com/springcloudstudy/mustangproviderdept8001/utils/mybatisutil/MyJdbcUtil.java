package com.springcloudstudy.mustangproviderdept8001.utils.mybatisutil;

import com.springcloudstudy.mustangproviderdept8001.mapper.MybatisCommonMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.*;

/**
 * <ul>
 * <li>文件名称: JdbcUtils</li>
 * <li>文件描述:</li>
 * <li>版权所有: 版权所有(C) 2017</li>
 * <li>公 司: 厦门云顶伟业信息技术有限公司</li>
 * <li>内容摘要:</li>
 * <li>其他说明:</li>
 * <li>创建日期:2018/2/25 0025</li>
 * </ul>
 * <ul>
 * <li>修改记录:</li>
 * <li>版 本 号:</li>
 * <li>修改日期:</li>
 * <li>修 改 人:</li>
 * <li>修改内容:</li>
 * </ul>
 *
 * @author majx
 * @version 1.0.0
 */

@Service
public class MyJdbcUtil {

    @Resource
    private MybatisCommonMapper mybatisCommonMapper;

    @Resource
    private JdbcTemplate jdbcTemplate;

    /**
     * 查询list 多条记录
     *      * 这个方法用于泛型是基本类型的
     * @param sql
     * @param clz
     * @param <T>
     * @return
     */
    public <T> List<T> queryForList(String sql, Class<T> clz) {
        return jdbcTemplate.queryForList(sql, clz);
    }


    /**
     * @param sql
     * @return
     * @throws SQLException
     */
    public int updateBySql(String sql) throws SQLException {
        int num = 0;
        num = mybatisCommonMapper.updateBySql(sql);
        return num;
    }


    /**
     * 传入类    sql语句  返回list
     * <p>
     * 用于查询实体类
     *
     * @param clazz 类类型
     * @param sql   sql语句
     * @return
     * @throws Exception
     */
    public <T> List<T> queryListBySql(Class clazz, String sql) throws Exception {
        List<Map<Object, Object>> list = mybatisCommonMapper.queryListBySql(sql);
        List<T> entityList = changeMapToJavaBean(clazz, list);
        return entityList;
    }

    /**
     * 传入 类    sql语句   返回一个object   自己强转成实体类
     * @param t   类类型
     * @param sql sql语句
     * @return
     * @throws ClassNotFoundException
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public <T> T queryEntityBySql(Class<T> t, String sql) throws Exception {
        Map<Object, Object> objectMap = mybatisCommonMapper.queryEntiryBySql(sql);
        T o = changeOneMapToJavaBean(t, objectMap);
        return o;
    }

    /**
     * 通过反射   将查询的结果map类型转化为entity（javabean）
     *
     * @param clz     类的类型
     * @param mapList map<object,object>
     * @return
     * @throws ClassNotFoundException
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     */
    public <T> List<T> changeMapToJavaBean(Class<T> clz, List<Map<Object, Object>> mapList) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        List<T> list = new ArrayList<T>();

        for (Map<Object, Object> objectObjectMap : mapList) {
            //获得实例
            T o = clz.newInstance();

            Field[] fields = o.getClass().getDeclaredFields();

            for (Field field : fields) {
                String methodName = "set" + (field.getName().charAt(0) + "").toUpperCase() + field.getName().substring(1);
                //属性类型
                Class<?> fieldType = field.getType();
                //获得set方法
                Method method = clz.getMethod(methodName, fieldType);
                Object o1 = objectObjectMap.get(field.getName());
                if ("String".equals(fieldType.getSimpleName())) {
                    method.invoke(o, (String) o1);
                    continue;
                } else if ("Date".equals(fieldType.getSimpleName())) {
                    method.invoke(o, (Date) o1);
                    continue;
                } else if ("Integer".equals(fieldType.getSimpleName())
                        || "int".equals(fieldType)) {
                    Integer intval = Integer.parseInt((String) o1);
                    method.invoke(o, intval);
                    continue;
                } else if ("Long".equalsIgnoreCase(fieldType.getSimpleName())) {
                    method.invoke(o, o1);
                    continue;

                } else if ("Double".equalsIgnoreCase(fieldType.getSimpleName())) {
                    Double temp = ((BigDecimal) o1).doubleValue();
                    method.invoke(o, temp);
                    continue;

                } else if ("Boolean".equalsIgnoreCase(fieldType.getSimpleName())) {
                    Boolean temp = Boolean.parseBoolean((String) o1);
                    method.invoke(o, temp);
                    continue;
                } else {
                    System.out.println("not supper type" + fieldType);
                }
            }
            list.add(o);
        }
        return list;
    }

    /**
     * 通过反射   将查询的结果map类型转化为entity（javabean）
     *
     * @param clazz 类的类型
     * @return
     * @throws Exception
     */
    public <T> T changeOneMapToJavaBean(Class<T> clazz, Map<Object, Object> objectMap) throws Exception {
        T o = clazz.newInstance();
        //使用iterator  遍历 map
        Iterator<Map.Entry<Object, Object>> it = objectMap.entrySet().iterator();
        while (it.hasNext()) {
            it.next();
            Field[] fields = o.getClass().getDeclaredFields();
            for (Field field : fields) {
                //
                String methodName = "set" + (field.getName().charAt(0) + "").toUpperCase() + field.getName().substring(1);
                //属性类型
                Class<?> fieldType = field.getType();
                //获得set方法
                Method method = clazz.getMethod(methodName, fieldType);
                Object o1 = objectMap.get(field.getName());
                if ("String".equals(fieldType.getSimpleName())) {
                    method.invoke(o, (String) o1);
                    continue;
                } else if ("Date".equals(fieldType.getSimpleName())) {
                    method.invoke(o, (Date) o1);
                    continue;
                } else if ("Integer".equals(fieldType.getSimpleName())
                        || "int".equals(fieldType)) {
                    Integer intval = Integer.parseInt((String) o1);
                    method.invoke(o, intval);
                    continue;
                } else if ("Long".equalsIgnoreCase(fieldType.getSimpleName())) {
                    method.invoke(o, o1);
                    continue;
                } else if ("Double".equalsIgnoreCase(fieldType.getSimpleName())) {
                    Double temp = ((BigDecimal) o1).doubleValue();
                    method.invoke(o, temp);
                    continue;
                } else if ("Boolean".equalsIgnoreCase(fieldType.getSimpleName())) {
                    Boolean temp = Boolean.parseBoolean((String) o1);
                    method.invoke(o, temp);
                    continue;
                } else {
                    System.out.println("not supper type" + fieldType);
                }
            }
        }
        return o;
    }

}
