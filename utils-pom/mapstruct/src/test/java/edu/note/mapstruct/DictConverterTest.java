package edu.note.mapstruct;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import edu.note.mapstruct.dictmapping.DictConverter;
import edu.note.mapstruct.dictmapping.Source;
import edu.note.mapstruct.dictmapping.Target;

/**
 * @author jackylee
 * @date 2025-12-08 14:34
 */
public class DictConverterTest {

    @Test
    @DisplayName("测试")
    void test01() {
        Source source = new Source();
        source.setId(1L);
        Map<String, String> map = new HashMap<>();
        map.put("host", "localhost");
        // map.put("port", "3306");
        source.setMap(map);

        Target target = DictConverter.INSTANCE.toTarget(source);
        assertEquals(target.getId(), 1L);
        assertEquals(target.getHost(), "localhost");
        // assertEquals(target.getPort(), "3306");
        assertEquals(target.getPort(), null);
    }

}
