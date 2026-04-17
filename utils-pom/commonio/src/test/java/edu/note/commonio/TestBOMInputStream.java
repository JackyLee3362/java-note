package edu.note.commonio;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.input.BOMInputStream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * @author jackylee
 * @date 2026-04-17 14:40
 */
public class TestBOMInputStream {

    // 构造带 BOM 的 InputStream
    private InputStream withBom(String content) {
        byte[] bom = { (byte) 0xEF, (byte) 0xBB, (byte) 0xBF };
        byte[] bytes = content.getBytes(StandardCharsets.UTF_8);
        byte[] result = new byte[bom.length + bytes.length];
        System.arraycopy(bom, 0, result, 0, bom.length);
        System.arraycopy(bytes, 0, result, bom.length, bytes.length);
        return new ByteArrayInputStream(result);
    }

    @Test
    @DisplayName("BOMInputStream vs InputStream 对比")
    void test_bom_input_stream_diff() throws IOException {
        // given: 构造带 BOM 的字节数组
        // ---- 普通 InputStream ----
        System.out.println("=== 普通 InputStream ===");
        try (Reader reader = new InputStreamReader(withBom("id,name,age"), StandardCharsets.UTF_8)) {
            char[] buf = new char[20];
            int len = reader.read(buf);
            String result = new String(buf, 0, len);
            System.out.println("原始内容: " + result);
            System.out.println("第一个字符 Unicode: U+" + String.format("%04X", (int) result.charAt(0)));
            System.out.println("是否含 BOM: " + result.startsWith("\uFEFF"));
        }

        System.out.println();

        // ---- BOMInputStream ----
        System.out.println("=== BOMInputStream ===");
        try (BOMInputStream bomInputStream = new BOMInputStream(withBom("id,name,age"));
                Reader reader = new InputStreamReader(bomInputStream, StandardCharsets.UTF_8)) {
            char[] buf = new char[20];
            int len = reader.read(buf);
            String result = new String(buf, 0, len);
            System.out.println("原始内容: " + result);
            System.out.println("第一个字符 Unicode: U+" + String.format("%04X", (int) result.charAt(0)));
            System.out.println("是否含 BOM: " + result.startsWith("\uFEFF"));
            System.out.println("检测到的 BOM: " + bomInputStream.getBOMCharsetName()); // 打印检测到的编码
        }
    }
}
