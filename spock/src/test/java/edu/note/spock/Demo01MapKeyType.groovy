package edu.note.spock

import spock.lang.Specification

/**
 * @author jackylee
 * @date 2025/7/15 11:38
 */
class Demo01MapKeyType extends Specification {

    def "测试 Map 中键类型"() {

        given:
        def map = [1: "a"]
        def map2 = [1: "a", 1L: "b"]

        expect: "验证"
        /Map<Integer, ..> 和 Map<Long, ..> 不一样/
        map.get(1L) == null
        map2.get(1L) != null
    }
}
