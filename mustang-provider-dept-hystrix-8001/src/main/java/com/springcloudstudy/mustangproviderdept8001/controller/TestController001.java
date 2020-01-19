package com.springcloudstudy.mustangproviderdept8001.controller;

import com.alibaba.fastjson.JSONObject;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.springcloudstudy.mustangbase.entities.Dept;
import com.springcloudstudy.mustangproviderdept8001.service.TestService001;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

/**
 * <ul>
 * <li>文件名称：TestController001</li>
 * <li>文件描述：暂无描述</li>
 * <li>版权所有：版权所有(C) 2019</li>
 * <li>公 司：厦门云顶伟业信息技术有限公司</li>
 * <li>内容摘要：</li>
 * <li>其他说明：</li>
 * <li>创建日期：2020/1/17 11:21</li>
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
public class TestController001 {

    @Autowired
    private DiscoveryClient client;

    @Resource
    private TestService001 testService001;

    @Autowired
    private ControllerUtil controllerUtil;


    @RequestMapping(value = "/provider/dept/queryDeptById", produces = "application/json;charset=utf-8")
    @ResponseBody
    public String queryDept(HttpServletRequest request) throws Exception {
        String requestParam = controllerUtil.readRequestParam(request);
        JSONObject jsonObject = JSONObject.parseObject(requestParam);
        if(StringUtils.isEmpty(jsonObject.get("id").toString())){
            return "id不可为空！";
        }
        Dept dept = testService001.queryDeptById(jsonObject.get("id").toString());
        if(dept==null){
            throw new RuntimeException("查不到对应的dept");
        }
        return JSONObject.toJSONString(dept);
    }

    @RequestMapping(value = "/provider/dept/queryDeptList", produces = "application/json;charset=utf-8")
    @ResponseBody
    public String queryDeptList() throws Exception {
        List<Dept> depts = testService001.queryAllDept();
        return JSONObject.toJSONString(depts);
    }

    @RequestMapping(value = "/provider/dept/queryDeptFeign", produces = "application/json;charset=utf-8")
    @ResponseBody
    public String queryDeptFeign(HttpServletRequest request) throws Exception {

        String requestParam = controllerUtil.readRequestParam(request);
        JSONObject jsonObject = JSONObject.parseObject(requestParam);
        if(StringUtils.isEmpty(jsonObject.get("id").toString())){
            return "id不可为空！";
        }
        Dept dept = testService001.queryDeptById(jsonObject.get("id").toString());
        return JSONObject.toJSONString(dept);
    }

    /**
     * 声明服务发现
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/provider/dept/discovery")
    public Object discovery(){
        List<String> list = client.getServices();
        for (String s : list) {
            List<ServiceInstance> instances = client.getInstances(s);
            for (ServiceInstance instance : instances) {
                System.out.println("****  discovery  ****");
                System.out.println(instance.getServiceId()+"\t"+instance.getHost()+"\t"+instance.getPort()+"\t"
                        +instance.getUri());
                System.out.println("****  discovery over  ****");
            }
        }
        return this.client;
    }


}
