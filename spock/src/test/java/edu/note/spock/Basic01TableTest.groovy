package edu.note.spock

import spock.lang.Specification
import spock.lang.Unroll

/**
 * @author jackylee
 * @date 2025/6/30 19:56
 */
class Basic01TableTest extends Specification {
    // 表格语法，将表格的每行单独作为一个测试用例执行
    @Unroll
    def "test 是否是奇数"() {

        expect: "when + then 的组合"
        NumberUtil.isOdd(number) == result
        where: "表格方式测试不同的分支逻辑"
        number || result
        123    || true
        123456 || false
    }

    @Unroll
    def "test 是否是奇数 - 2"() {

        when:
        def response = NumberUtil.isOdd(num)


        then: "when + then 的组合"
        response == res
        where: "表格方式测试不同的分支逻辑"
        num    || res
        123    || true
        123456 || false
    }
}
