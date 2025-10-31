package edu.note.thirft.sdk.anno;

import com.facebook.swift.codec.ThriftField;
import com.facebook.swift.codec.ThriftStruct;

/**
 * @author jackylee
 * @date 2025/9/3 10:37
 */
@ThriftStruct
public class User {
    private Integer id;
    private String name;

    @ThriftField(1)
    public Integer getId() {
        return id;
    }

    @ThriftField
    public void setId(Integer id) {
        this.id = id;
    }

    @ThriftField(2)
    public String getName() {
        return name;
    }

    @ThriftField
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", name=" + name + "]";
    }

}
