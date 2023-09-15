package com.sky.test;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

public class HttpClientTest {

    //get
    @Test
    public void testGet() throws Exception {
        //创建http对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //发送请求
        HttpGet httpGet = new HttpGet("http://localhost:8080/user/shop/status");
        //发送请求
        CloseableHttpResponse response = httpClient.execute(httpGet);

        //获取服务器的状态码
        int statusCode = response.getStatusLine().getStatusCode();
        System.out.println("服务器返回的状态码" + statusCode);
        HttpEntity entity = response.getEntity();
        String body = EntityUtils.toString(entity);
        //服务器返回的数据
        System.out.println("服务器返回的数据" + body);
        //关闭
        response.close();
        httpClient.close();
        //System.out.println("hello");
    }

    //post
    @Test
    public void testPost() throws Exception{
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //发送请求
        HttpPost httpPost = new HttpPost("http://localhost:8080/admin/employee/login");
        //封装参数
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("username","admin");
        jsonObject.put("password","123456");
        StringEntity stringEntity = new StringEntity(jsonObject.toString());
        //指定编码方法
        stringEntity.setContentEncoding("utf-8");
        stringEntity.setContentType("application/json");
        httpPost.setEntity(stringEntity);
        //发送数据
        CloseableHttpResponse response = httpClient.execute(httpPost);

        int statusCode = response.getStatusLine().getStatusCode();
        System.out.println("服务器返回的状态码" + statusCode);
        HttpEntity entity = response.getEntity();
        String body = EntityUtils.toString(entity);
        //服务器返回的数据
        System.out.println("服务器返回的数据" + body);
        //关闭
        response.close();
        httpClient.close();
    }
}
