package com.springcloudstudy.mustangproviderdept8002.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * <ul>
 * <li>文件名称：MybatisCommonDao</li>
 * <li>文件描述：暂无描述</li>
 * <li>版权所有：版权所有(C) 2019</li>
 * <li>公 司：厦门云顶伟业信息技术有限公司</li>
 * <li>内容摘要：</li>
 * <li>其他说明：</li>
 * <li>创建日期：2020/1/17 11:09</li>
 * </ul>
 *
 * <ul>
 * <li>修改记录：</li>
 * <li>版 本 号：</li>
 * <li>修改日期：</li>
 * <li>修 改 人：</li>
 * <li>修改内容：</li>
 * </ul>
 *
 * @author majx
 * @version 1.0.0
 */
@Mapper
public interface MybatisCommonMapper {

    @Select("${sql}")
    public List<Map<Object, Object>> queryListBySql(@Param("sql") String sql) throws SQLException;

    @Select("${sql}")
    public Map<Object, Object> queryEntiryBySql(@Param("sql") String sql) throws SQLException;

    @Transactional(rollbackFor = Exception.class)
    @Update("${sql}")
    public int updateBySql(@Param("sql") String sql) throws SQLException;


}
