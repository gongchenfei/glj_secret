package com.exclouds.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.exclouds.service.IAesEncodeService;
import com.exclouds.util.AES4CzUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Slf4j
@Service
public class AesEncodeServiceImpl implements IAesEncodeService {

    @Value("${url}")
    private String url;
    @Value("${encode.key}")
    private String sKey;
    @Value("encode.iv")
    private String iv;

    @Override
    public String encrypt(String encodeStr) {
        if ("".equals(encodeStr) || encodeStr == null) {
            return "";
        }
        System.out.println("加密前的字串是：" + encodeStr);
        // 加密
        long lStart = System.currentTimeMillis();
        String enString = null;
        try {
            enString = AES4CzUtil.getInstance().encrypt(encodeStr, sKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("加密后的字串是：" + enString);

        String result = httpPost(url, enString);
        System.out.println("请求接口返回值" + result);

        // 解密
        lStart = System.currentTimeMillis();
        String deString = null;
        try {
            deString = AES4CzUtil.getInstance().decrypt(result, sKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("解密后的字串是：" + deString);


        return deString;
    }


    /**
     * post 提交
     *
     * @param url   请求地址
     * @param param json参数
     * @return
     */
    public String httpPost(String url, String param) {

        JSONObject jsonObject = null;
        CloseableHttpClient client = null;
        CloseableHttpResponse response = null;
        try {
            /**
             *  创建一个httpclient对象
             */
            client = HttpClients.createDefault();
            /**
             * 创建一个post对象
             */
            HttpPost post = new HttpPost(url);
            /**
             * 包装成一个Entity对象
             */
            StringEntity entity = new StringEntity(param, "UTF-8");
            /**
             * 设置请求的内容
             */
            post.setEntity(entity);
            /**
             * 设置请求的报文头部的编码
             */
            post.setHeader(new BasicHeader("Content-Type", "application/x-www-form-urlencoded; charset=utf-8"));
            /**
             * 设置请求的报文头部的编码
             */
            post.setHeader(new BasicHeader("Accept", "text/plain;charset=utf-8"));
            /**
             * 执行post请求
             */
            response = client.execute(post);
            /**
             * 获取响应码
             */
            int statusCode = response.getStatusLine().getStatusCode();
            if (200 == statusCode) {
                /**
                 * 通过EntityUitls获取返回内容
                 */
                String result = EntityUtils.toString(response.getEntity(), "UTF-8");
                /**
                 * 转换成json,根据合法性返回json或者字符串
                 */
                try {
                    jsonObject = JSONObject.parseObject(result);
                } catch (Exception e) {
                    return result;
                }
            } else {
                log.error("HttpClientService-line: {}, errorMsg：{}", 146, "POST请求失败！");
            }
        } catch (Exception e) {
            log.error("HttpClientService-line: {}, Exception：{}", 149, e);
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
            } catch (IOException exception) {
                exception.printStackTrace();
            }
            try {
                if (client != null) {
                    client.close();
                }
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
        return null;
    }


}
