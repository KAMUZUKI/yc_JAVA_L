package com.mu.net.comcat1;

import org.apache.log4j.Logger;

import java.io.File;
import java.io.InputStream;
import java.net.Socket;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author : MUZUKI
 * @date : 2023-01-04 21:10
 **/

public class HttpServletRequest {
    private Logger logger = Logger.getLogger(HttpServletRequest.class);

    private Socket s;

    private InputStream iis;
    /**
     * GET,POST,PUT,DELETE,HEAD,OPTIONS,TRACE
     */
    private String method;
    /**
     * 定位符 http://localhost:81/res/doUpload.action?uname=z&pwd=b
     */
    private String requestURL;
    /**
     * 标识符 /res/doUpload.action
     */
    private String requestURI;
    /**
     * 上下文 /res
     */
    private String contextPath;
    /**
     * 请求字符串 请求的地址栏参数 uname=z&pwd=b
     */
    private String queryString;
    /**
     * 参数： 地址栏参数 uname=z&pwd=b 表单中的参数-请求实体：sex=男&age=18
     */
    private Map<String, String[]> parameterMap = new ConcurrentHashMap<>();
    /**
     * 协议类型
     */
    private String scheme;
    /**
     * 协议版本
     */
    private String protocol;
    /**
     * 项目的真实路径
     */
    private String realPath;

    public HttpServletRequest(Socket s,InputStream iis) {
        this.s = s;
        this.iis = iis;
        this.parseRequest();
    }

    private void parseRequest() {
        // TODO Auto-generated method stub
        //从输入流中读取http请求信息
        String requestInfoString = readFromInputStream();
        if(requestInfoString == null || "".equals(requestInfoString.trim())){
            throw new RuntimeException("读取输入流异常");
        }
        parseRequestInfoString(requestInfoString);
    }

    private void parseRequestInfoString(String requestInfoString) {
        StringTokenizer st = new StringTokenizer(requestInfoString);
        this.method = st.nextToken();
        this.requestURI = st.nextToken();
        //requestURI要考虑地址栏参数
        int questionIndex = this.requestURI.lastIndexOf("?");
        if (questionIndex >= 0) {
            //有？说明有地址栏参数 -> 参数存 queryString属性
            this.queryString = this.requestURI.substring(questionIndex + 1);
            this.requestURI = this.requestURI.substring(0, questionIndex);
        }
        //第三部分:协议版本 HTTP/1.1
        this.protocol = st.nextToken();
        this.scheme = this.protocol.substring(0, this.protocol.indexOf("/"));
        int slash2Index = this.requestURI.indexOf("/", 1);
        if (slash2Index >= 0) {
            this.contextPath = this.requestURI.substring(0, slash2Index);
        } else {
            this.contextPath = this.requestURI;
        }
        //requsetURL: URL 统一资源定位符 http://ip:port/项目名/资源名
        this.requestURL = this.scheme + "://" + this.s.getLocalAddress() + this.requestURI;

        //解析地址栏参数  :/res/index.html?uname=z&pwd=b
        //从 queryString中解析出地址栏参数
        if(this.queryString != null && this.queryString.length()>0){
            String[] ps = this.queryString.split("&");
            for (String p : ps) {
                String[] params = p.split("=");
                this.parameterMap.put(params[0], params[1].split(","));
            }
            //TODO: 还有post实体中也有可能有参数
        }
        this.realPath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + "webapp";
    }

    public String readFromInputStream() {
        // TODO Auto-generated method stub
        int length = -1;
        StringBuffer sb = null;
        byte[] bs = new byte[300*1024];
        try{
            length = this.iis.read(bs,0,bs.length);
            sb = new StringBuffer();
            for (int i = 0; i < length; i++) {
                sb.append((char)bs[i]);
            }
        }catch(Exception e){
            logger.error("读取请求信息失败");
            e.printStackTrace();
        }
        return sb.toString();
    }

    public String[] getParameterValues(String name){
        if (parameterMap==null||parameterMap.size()<=0) {
            return null;
        }
        String[] values = this.parameterMap.get(name);
        return values;
    }

    public String getParameter(String name){
        String[] values = getParameterValues(name);
        if (values==null||values.length<=0) {
            return null;
        }
        return values[0];
    }

    public String getMethod() {
        return method;
    }

    public String getRequestURL() {
        return requestURL;
    }

    public String getRequestURI() {
        return requestURI;
    }

    public String getContextPath() {
        return contextPath;
    }

    public String getQueryString() {
        return queryString;
    }

    public Map<String, String[]> getParameterMap() {
        return parameterMap;
    }

    public String getScheme() {
        return scheme;
    }

    public String getProtocol() {
        return protocol;
    }

    public String getRealPath() {
        return realPath;
    }
}
