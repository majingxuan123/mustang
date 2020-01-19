package com.springcloudstudy.mustangproviderdept8003.service;

import com.springcloudstudy.mustangbase.entities.Dept;
import com.springcloudstudy.mustangproviderdept8003.utils.mybatisutil.MyJdbcUtil;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

/**
 * <ul>
 * <li>文件名称：TestService001</li>
 * <li>文件描述：暂无描述</li>
 * <li>版权所有：版权所有(C) 2019</li>
 * <li>公 司：厦门云顶伟业信息技术有限公司</li>
 * <li>内容摘要：</li>
 * <li>其他说明：</li>
 * <li>创建日期：2020/1/17 11:20</li>
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
@Service
public class TestService001 {


    @Resource
    private MyJdbcUtil myJdbcUtil;


    public Dept queryDeptById(String id) throws Exception {

        return myJdbcUtil.queryEntityBySql(Dept.class," select * from dept where deptno = '"+id+"' ");
    }


    public List<Dept> queryAllDept() throws Exception {
        return myJdbcUtil.queryListBySql(Dept.class," select * from dept ");
    }


}
