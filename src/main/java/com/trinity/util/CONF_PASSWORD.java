package com.trinity.util;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

public class CONF_PASSWORD {
    public String getPassword(String password, String salt, int times) {
        //可选择加密方式&加密次数
        //SHA256算法似乎不需要多次迭代即可保证安全性
        SimpleHash simpleHash = new SimpleHash("SHA-256", password, ByteSource.Util.bytes(salt), times);
        return simpleHash.toString();
    }
}
