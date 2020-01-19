package com.springcloudstudy.mustangconsumerdept8101.controller;

import com.alibaba.fastjson.JSONObject;
import com.springcloudstudy.mustangconsumerdept8101.util.ControllerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
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

//    private static  final String REST_URL_PREFIX = "http://eureka7001:8001/mustang";

    private static  final String REST_URL_PREFIX = "http://MUSTANG-DEPT-PROVIDER/mustang/";

    /**
     * 使用：
     * url：rest请求地址
     * requestMap：请求参数
     * responseBean。class http相应转换被转换成的对象类型
     */
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ControllerUtil controllerUtil;

    @RequestMapping(value = "/consumer/dept/queryDeptByid")
    public String getDeptById(HttpServletRequest request) throws IOException {
        String requestParam = controllerUtil.readRequestParam(request);
        JSONObject jsonObject = JSONObject.parseObject(requestParam);
        String s = restTemplate.postForObject(REST_URL_PREFIX + "/provider/dept/queryDeptById", jsonObject.toJSONString(), String.class);
        return s;
    }

    @RequestMapping(value = "/consumer/dept/queryDeptList")
    public String getDeptList(){
        return restTemplate.postForObject(REST_URL_PREFIX+"/provider/dept/queryDeptList",null,String.class);
    }
}
