package com.kingdee.finance.study.spider;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
 
public class HttpClientLoginTest {
    @Test
    // ��ȡһ��HTMLҳ������ݣ�һ���򵥵�getӦ��
    public void grabPageHTML() throws Exception {
        HttpClient httpclient = new DefaultHttpClient();
        HttpGet httpget = new HttpGet("http://www.baidu.com/");
        HttpResponse response = httpclient.execute(httpget);
        HttpEntity entity = response.getEntity();
        String html = EntityUtils.toString(entity, "GBK");
 
        // releaseConnection��ͬ��reset������������request״̬λ��Ϊ�´�ʹ������׼����
        // ��ʵ������һ��HttpGet��ȡ���ҳ����������Ч����������Ժ��Դ˷�����
        httpget.releaseConnection();
 
        System.out.println(html);
    }
 
    // ����һ���ļ������أ���ʾ����Ϊһ����֤��ͼƬ��
    @Test
    public void downloadFile() throws Exception {
        String url = "http://www.lashou.com/account/captcha";
        File dir = new File("D:\\TDDOWNLOAD");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        String destfilename = "D:\\TDDOWNLOAD\\yz.png";
        HttpClient httpclient = new DefaultHttpClient();
        HttpGet httpget = new HttpGet(url);
        File file = new File(destfilename);
        if (file.exists()) {
            file.delete();
        }
 
        HttpResponse response = httpclient.execute(httpget);
        HttpEntity entity = response.getEntity();
        InputStream in = entity.getContent();
        try {
            FileOutputStream fout = new FileOutputStream(file);
            int l = -1;
            byte[] tmp = new byte[2048];
            while ((l = in.read(tmp)) != -1) {
                fout.write(tmp);
            }
            fout.close();
        } finally {
            // ����InputStream����HttpEntityʱ���м�Ҫ�رյͲ�����
            in.close();
        }
 
        httpget.releaseConnection();
    }
 
    @Test
    // Post������ģ����ύ������¼����վ��
    // �������������������grabPageHTML/downloadFile��ͬʱ������Post�Ĵ��롣
    public void login2Lashou() throws Exception {
        // ��һ������������֤�뵽����
        String url = "http://www.lashou.com/account/captcha";
        String destfilename = "D:\\TDDOWNLOAD\\yz.png";
        HttpClient httpclient = new DefaultHttpClient();
        HttpGet httpget = new HttpGet(url);
        File file = new File(destfilename);
        if (file.exists()) {
            file.delete();
        }
 
        HttpResponse response = httpclient.execute(httpget);
        HttpEntity entity = response.getEntity();
        InputStream in = entity.getContent();
        try {
            FileOutputStream fout = new FileOutputStream(file);
            int l = -1;
            byte[] tmp = new byte[2048];
            while ((l = in.read(tmp)) != -1) {
                fout.write(tmp);
            }
            fout.close();
        } finally {
            in.close();
        }
        httpget.releaseConnection();
 
        // �ڶ�������Post���������ɲ������Ե�¼����Ҫ�ֹ�����������֤������ʾ����ĸ������
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("������������������֤������ʾ������...");
        String yan = br.readLine();
 
        HttpPost httppost = new HttpPost("http://www.lashou.com/account/login/");
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("user_id", "testuser007"));
        params.add(new BasicNameValuePair("pwd", "asdfg123"));
        params.add(new BasicNameValuePair("yan", yan));
        params.add(new BasicNameValuePair("save_user", "on"));
        params.add(new BasicNameValuePair("save_pwd", "on"));
        params.add(new BasicNameValuePair("sub", "��¼"));
        httppost.setEntity(new UrlEncodedFormEntity(params));
 
        response = httpclient.execute(httppost);
        entity = response.getEntity();
        // �����������Jsoup֮��Ĺ��߶Է��ؽ�����з��������жϵ�¼�Ƿ�ɹ�
        String postResult = EntityUtils.toString(entity, "GBK");
        // ��������ֻ�Ǽ򵥵Ĵ�ӡ����ǰCookieֵ���жϵ�¼�Ƿ�ɹ���
        CookieStore cookieStore = ((AbstractHttpClient) httpclient).getCookieStore();
        List<Cookie> cookies = ((AbstractHttpClient) httpclient)
                .getCookieStore().getCookies();
        for (Cookie cookie : cookies)
            System.out.println("cookie begin***\n" + cookie + "\n cookie end");
        httppost.releaseConnection();
 
        // ���������򿪻�Աҳ�����жϵ�¼�ɹ���δ��¼�û��Ǵ򲻿���Աҳ��ģ�
        String memberpage = "http://www.lashou.com/account/orders/";
        httpget = new HttpGet(memberpage);
        response = httpclient.execute(httpget); // ������ͬһ��HttpClient��
        entity = response.getEntity();
        String html = EntityUtils.toString(entity, "GBK");
        httpget.releaseConnection();
 
        System.out.println(html);
    }
 
    @Test
    public void testSystemIn() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(
                System.in));
        String hello = reader.readLine();
        System.out.println(hello);
    }
 
    @Test
    // �����ѻ�ȡ��cookie���鿴��Ҫ��¼����ܲ鿴��ҳ��
    public void testGetinfoByLoginCookie() throws Exception, IOException {
        DefaultHttpClient httpclient = new DefaultHttpClient();
        CookieStore cookieStore = new BasicCookieStore();
        BasicClientCookie cookie1 = new BasicClientCookie("ThinkID","5s4tmqem08gh091v3egoa7sqf7");
        cookie1.setDomain(".lashou.com");
        BasicClientCookie cookie2 = new BasicClientCookie("city_b","2419");
        cookie2.setDomain("lashou.com");
        BasicClientCookie cookie3 = new BasicClientCookie("client_key","1425104707wd157b4b24ff70adcb875a");
        cookie3.setDomain("lashou.com");
        BasicClientCookie cookie4 = new BasicClientCookie("login_name2","testuser007");
        cookie4.setDomain("lashou.com");
        BasicClientCookie cookie5 = new BasicClientCookie("pwd2","4c88a4062736c26572d3ec382868fa2b");
        cookie5.setDomain("lashou.com");
        cookieStore.addCookie(cookie1);
        cookieStore.addCookie(cookie2);
        cookieStore.addCookie(cookie3);
        cookieStore.addCookie(cookie4);
        cookieStore.addCookie(cookie5);
        List<Cookie> cookies = new ArrayList<Cookie>();
        httpclient.setCookieStore(cookieStore);
         
        List<Cookie> cookieList = httpclient.getCookieStore().getCookies();
        for(int i=0;i<cookieList.size();i++){
            System.out.println("cookie"+i+":"+cookieList.get(i));
        }
         
        // �����ѵ�¼��cookie
        String memberpage = "http://www.lashou.com/account/orders/";
        HttpGet httpget = new HttpGet(memberpage);
        HttpResponse response = httpclient.execute(httpget); // ������ͬһ��HttpClient��
        HttpEntity entity = response.getEntity();
        entity = response.getEntity();
        String html = EntityUtils.toString(entity, "GBK");
        httpget.releaseConnection();
 
        System.out.println(html);
    }
}