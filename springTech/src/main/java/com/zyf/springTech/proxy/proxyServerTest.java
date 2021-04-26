package com.zyf.springTech.proxy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;

/**
 * 代理服务器访问url
 */
public class proxyServerTest {

    /**
     * 通过代理服务器访问
     * <p/>
     * 在某些场景下不能直接访问，需要通过网络管理员提供的代理访问
     * <p/>
     * 例如在内网环境不能访问外网。
     */
    public static void main(String[] args) throws Exception {

        // 我们期望访问的地址
        URL url = new URL("");

        // ******* 打开和URL之间的连接（代理） *******
        HttpURLConnection httpURLConnection = createProxyConn(url);
        // ******* 打开和URL之间的连接（代理） *******

        // 打开和URL之间的连接（非代理）
        // HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

        // 设置连接参数（和非代理访问无差别）
        httpURLConnection.setRequestMethod("GET");
        httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        httpURLConnection.setDoInput(true);
        httpURLConnection.setConnectTimeout(30000);
        httpURLConnection.setReadTimeout(60000);
        InputStream is = httpURLConnection.getInputStream();
        String resp = "";
        BufferedReader in = new BufferedReader(new InputStreamReader(is, "UTF-8"));
        String line;
        while ((line = in.readLine()) != null) {
            resp += line;
        }
        System.out.println(resp);

    }

    /**
     * 打开和URL之间的连接
     *
     * @param url
     * @return
     * @throws IOException
     */
    public static HttpURLConnection createProxyConn(URL url) throws IOException {
        // 代理主机
        String proxyHost = "127.0.0.1";
        // 代理主机端口
        int proxyPort = 8888;

        // 代理服务器地址
        InetSocketAddress inetSocketAddress = new InetSocketAddress(proxyHost, proxyPort);
        // 代理
        Proxy proxy = new Proxy(Proxy.Type.HTTP, inetSocketAddress);
        // 通过代理打开访问地址的连接
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection(proxy);
        return httpURLConnection;
    }
}
