package edu.note.java.classLoader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;

/**
 * @author jackylee
 * @date 2024/11/28 下午10:53
 */
public class CustomClassLoader extends ClassLoader {

    private final String path;

    public CustomClassLoader(String path) {
        this.path = path;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        String simpleName = name.substring(name.lastIndexOf(".") + 1);
        String fileName = path + "/" + simpleName + ".class";
        System.out.println(fileName);
        try {
            byte[] bytes = getClassBytes(fileName);
            return this.defineClass(null, bytes, 0, bytes.length);
        } catch (Exception e) {
            System.out.println(e);
        }
        return super.findClass(fileName);
    }


    private byte[] getClassBytes(String fileName) throws Exception {
        File file = new File(fileName);
        // 这里要读入.class的字节，因此要使用字节流
        FileInputStream fis = new FileInputStream(file);
        FileChannel fc = fis.getChannel();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        WritableByteChannel wbc = Channels.newChannel(byteArrayOutputStream);
        ByteBuffer by = ByteBuffer.allocate(1024);

        while (true) {
            int i = fc.read(by);
            if (i == 0 || i == -1) {
                break;
            }
            by.flip();
            wbc.write(by);
            by.clear();
        }

        fis.close();

        return byteArrayOutputStream.toByteArray();
    }
}
