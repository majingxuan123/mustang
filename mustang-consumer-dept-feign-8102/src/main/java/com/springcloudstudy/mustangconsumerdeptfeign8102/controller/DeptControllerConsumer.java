package com.springcloudstudy.mustangconsumerdeptfeign8102.controller;

import com.alibaba.fastjson.JSONObject;
import com.springcloudstudy.mustangbase.service.dept.DeptClientService;
import com.springcloudstudy.mustangconsumerdeptfeign8102.util.ControllerUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * <ul>
 * <li>文件名称：DeptControllerConsumer</li>
 * <li>文件描述：暂无描述</li>
 * <li>版权所有：版权所有(C) 2019</li>
 * <li>公 司：厦门云顶伟业信息技术有限公司</li>
 * <li>内容摘要：</li>
 * <li>其他说明：</li>
 * <li>创建日期：2020/1/18 20:20</li>
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
@RestController
public class DeptControllerConsumer {

    @Autowired
    private ControllerUtil controllerUtil;

    @Autowired
    private DeptClientService service;

    @ResponseBody
    @RequestMapping(value = "/consumer/dept/queryDeptByid")
    public String getDeptById(HttpServletRequest request) throws Exception {
        String requestParam = controllerUtil.readRequestParam(request);
        JSONObject jsonObject = JSONObject.parseObject(requestParam);
        if(StringUtils.isEmpty(jsonObject.get("id").toString())){
            return "id不可为空！";
        }
        String str = service.queryDeptFeign(jsonObject.toJSONString());
        return str;
    }
    @ResponseBody
    @RequestMapping(value = "/consumer/dept/queryDeptList")
    public String getDeptList() {
        String str = service.queryDeptList();
        return str;
    }


}
