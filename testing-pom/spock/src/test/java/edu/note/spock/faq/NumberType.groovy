package edu.note.spock.faq

import spock.lang.Specification

/**
 * ISSUE: Groovy 中 Map 的 Long 和 Integer 不一样
 *
 * @author jackylee
 * @date 2025/7/15 11:38
 */
class NumberType extends Specification {

    def "测试 Integer 和 Long"() {
        given:
        def i = 1
        def l = 1L

        expect:
        i == l

    }

    def "测试列表中数字"() {
        given:
        def list1 = [1, 1]
        def list2 = [1L, 1L]

        expect:
        list1 == list2
    }

    def "测试 Map 中数字类型"() {

        given:
        def map = [1: "a"]
        def map2 = [1: "a", 1L: "b"]

        expect: "验证"
        /Map<Integer, ..> 和 Map<Long, ..> 不一样/
        map.get(1L) == null
        map2.get(1L) != null
    }
}
