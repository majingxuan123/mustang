package com.springcloudstudy.mustangproviderdept8001.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * <ul>
 * <li>文件名称：ControllerUtil</li>
 * <li>文件描述：暂无描述</li>
 * <li>版权所有：版权所有(C) 2019</li>
 * <li>公 司：厦门云顶伟业信息技术有限公司</li>
 * <li>内容摘要：</li>
 * <li>其他说明：</li>
 * <li>创建日期：2020/1/18 22:43</li>
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
@Component
public class ControllerUtil {


    public String readRequestParam(HttpServletRequest request) throws IOException {
        StringBuffer requestData = new StringBuffer();

        BufferedReader bufferedReader = request.getReader();
        String tempData ;
        while (true) {
            tempData = bufferedReader.readLine();
            if (StringUtils.isEmpty(tempData)) {
                break;
            }
            requestData.append(tempData);
        }
       return requestData.toString();
    }


}
