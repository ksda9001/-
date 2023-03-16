package com.shop.util;

import java.io.FileWriter;
import java.io.IOException;

/**
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */
public class AlipayConfig {
    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
    public static String app_id = "2021000119669102";
    // 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCCng4jQKrtbFyZsuazKhxKn5sWetOeGHvT0xG2riI7e0HSFylnSOgAcBfb5yWv24C0QUVqkZA9rdcKpq4g5wsLBS5wAHWAODLMPrEzAfE3ew247l5R59qu86pzH5w/YDlzLbUgS+MPb/wkQaSHw8i7P54yX4f9B+gVcN0hlN2+Uym+khn9xLr4aECsy7gw9bWIimXzNL++m3L0FN4Vbc8FzpXZROuTxcEd2zEPmoDwxMEbtz65eTKjWpixZpEYOZiHouvT+fslKiPYexL3Va1JX6MENKwzI2WfbIS4JtGLXiZmlAjnyRN/ZTVlIUGdHVmD8NYYWEoXt9ekDe557t1PAgMBAAECggEAYxsF4x2oE6mKwVwKy5qCCOT9SZs8gdJV8i1OXWvWsYceNAg69BFPZzWmBMZnw/0qtJppzyhPZ11FsuExcemcaWovB/BxNy5s7KgMz4UDv3LwwIasrjzboq9jfyJW7YdXT9s4eR7bU9VGXUQpUJHu9pSYtePiTMz4/Xu7bUWE0mkzcBf54oKguDlFqwun8N2Fot6WdkBwaXh2S4HS8s9gGP9vmwxB0osz+EWQD24FK3KPzkxKsH+ehdW/tPgH4/m0jD+L5FLHeeurp+xyjm9Z5cRAJAQf3KwcYB8YE9kH1DJM33FUP0iExfmDtofFNe9u0ZhdzJ2/1j4m2Avk0zF+sQKBgQDQumtZkYYhw1AR61dOlLbBoATkBBa02SR9kUnPJTK6Wqd96eV3inMoyAH3Mxk/CxOxH01ffU3Nt9mBtdt1L56XIiOPdyA06A2oVsmJAyWpjaELpkIXxH6fitSlOWvvDvztgBG1RhCKiyEJ68Ml8v9UpA3q2azq0eIyPc9i+UysswKBgQCgMvOepebgfDOc5RgZmrwvgyRKytlE8rON4EkQS1T2FhVeyEHoiU1KLYpajCFvkIqmfAGgPMkj9m1nxmJh2ilkWwvQR8/HDw9Lfxko3USoPm+uEbjGvIakGd4lJT+WMX5DUlzzx3atwudcBYEt1DSq07hgkUerZ5N0gCq66XMS9QKBgQCFZI3UeQ8KpsKWIRvzyVCen4BgpEhKnXcXlHFiI7iupd7itkcCxYVhK6kt7QcWZiLYkFOnUfIWJYeowKsW0i79EUE+n3gCt8Z6rbQwjsVT/YJNapxUiiwOE/BgAzl6Vs7HjZBDlBpgIBgmOfjGWOxakFmgnCpJC4cOargN3ZrlxwKBgGwHL+WR9oj2+E8a5g+OGsznYHfXJoCqMY7A6kREqHxyE+Mgo+FNhYM6Boxc5sc272ipQudtVi4cJVbPntRhq3vzMZo4ctLHQwckJDE8VOongGQfqzzJMPpEP1tJLQH5Si3XwrrZjRxLzHxvcdcXvJRdxX3JOk1JwVPUgfwdJO5pAoGBAKVvyxHv/2eM+hniFf0DrpJKX96K3pqdZZoMLiocy87qc7HuaY6AM7aN5gQOrysqm9u/1F0g1e+UpkacwG7+eSZbmVG5Mc0y4YbeMCvRdLDIB2HWWlV8lTjAMXnpS2W6insarK3AlNSjXt9/C/Gd8j5z1mAtA1HxnbQLnzXqUpJW";
    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA2eUV7ErrDWw2uxoa3sBZYXz15nRqNpAbtmR8AQcID9WuG7wZU5RCFdKSUNrk6i3eBd2jlQvhcl+sgRQnMo4rolRuS7hMgV085kvTYEdWJuTs8w911kBgsfRQUSK5PCRmHVR0q+uIOz5KjOPe3WupZlFIGqBnHuDDL+4oKUdo/6qfGaSxKn7hS/YJMmT0oqiZ8AVe/yTW97ex2ZG0ScwdW51YWjCoxngLdXzrZUPbyNM/ojjdpe21esOHIv4nqIF/gIeIOgequm9SsVOWc2cItOae8jDTIHz+fhbMt4H8JQearJ79Koq7+oMQNWmtxzlkG+JxHDqBPeys83iwdFpyWwIDAQAB";
    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "http://localhost:8082/alipayNotify";
    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String return_url = "http://localhost:8082/alipayReturn";
    // 签名方式
    public static String sign_type = "RSA2";
    // 字符编码格式
    public static String charset = "utf-8";
    // 支付宝网关
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";
    // 日志目录生成位置
    public static String log_path = "D:\\";

    /**
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
