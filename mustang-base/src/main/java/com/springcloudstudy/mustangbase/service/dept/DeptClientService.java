package com.springcloudstudy.mustangbase.service.dept;

import com.alibaba.fastjson.JSONObject;
import com.springcloudstudy.mustangbase.entities.Dept;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <ul>
 * <li>文件名称：DeptClientService</li>
 * <li>文件描述：暂无描述</li>
 * <li>版权所有：版权所有(C) 2019</li>
 * <li>公 司：厦门云顶伟业信息技术有限公司</li>
 * <li>内容摘要：</li>
 * <li>其他说明：</li>
 * <li>创建日期：2020/1/19 20:40</li>
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

/**
 * fallbackfactory:增加熔断处理
 */
@FeignClient(value = "MUSTANG-DEPT-PROVIDER",path = "/mustang/",fallbackFactory = DeptClientServiceFallBackFactory.class)
public interface DeptClientService {

    @RequestMapping(value = "/provider/dept/queryDeptById")
    @ResponseBody
    String queryDept(HttpServletRequest request) throws Exception;

    @RequestMapping(value = "/provider/dept/queryDeptList")
    @ResponseBody
    String queryDeptList();

    @RequestMapping(value = "/provider/dept/queryDeptFeign")
    @ResponseBody
    String queryDeptFeign(String jsonStr);


}
