package edu.note.spock

import spock.lang.Specification

/**
 * @author jackylee
 * @date 2025/7/14 10:10
 */
class IT01BasicTypeSpockSpec extends Specification  {

    def "test Mock 测试"() {
        given: "准备数据"
        def list = [1, 2, 3]
        def map = ["a": 1, "b": 2]

        expect: "结果验证 | 断言"
        list.size() == 3
        map.size() == 2

    }
}
