package edu.note.crypt;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.util.DigestUtils;

/**
 * @author jackylee
 * @date 2026-01-20 00:36
 */
public class Md5Test {

    @Test
    @DisplayName("测试 MD5 加密")
    void test01() throws NoSuchAlgorithmException {
        // java.security
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] bytes = "123456".getBytes();
        byte[] encrypt = md.digest(bytes);
        System.out.println(encrypt.toString());
        // 使用 spring
        byte[] md5Digest = DigestUtils.md5Digest(bytes);;
        System.out.println(md5Digest);
    }


    @Test
    @DisplayName("测试 bcrypt")
    void test02() throws NoSuchAlgorithmException {
        // todo
    }

}
