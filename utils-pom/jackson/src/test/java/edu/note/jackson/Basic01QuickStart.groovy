package edu.note.jackson

import com.fasterxml.jackson.databind.ObjectMapper
import spock.lang.Specification

/**
 * @author jackylee
 * @date 2025/7/1 17:28
 */
class Basic01QuickStart extends Specification {

    def mapper = new ObjectMapper()

    def "测试序列化"() {
        given:
        def person = new Person("Foo", 23)
        when:
        def json = mapper.writeValueAsString(person)
        then:
        json == "{\"name\":\"Foo\",\"age\":23}"
    }

    def "测试反序列化"() {
        given:
        def json = "{\"name\":\"Foo\",\"age\":23}"
        when:
        def person = mapper.readValue(json, Person.class)
        then:
        person.name == "Foo"
        person.age == 23
    }
}
