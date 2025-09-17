package edu.note.jackson;

import lombok.Data;

import java.util.List;

@Data
public class Teacher {
    public String name;
    public List<Info> info;

    @Data
    public static class Info{
        public String key1;
    }
}