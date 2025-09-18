package edu.note.thread.util;

import lombok.Getter;
import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class UnsafeAccessor {
    @Getter
    private static final Unsafe unsafe;

    static {
        try {
            Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
            theUnsafe.setAccessible(true);
            unsafe = (Unsafe) theUnsafe.get(null);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new Error(e);
        }
    }

}
