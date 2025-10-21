package edu.note.spock

import spock.lang.Specification

/**
 * @author jackylee
 * @date 2025/7/1 15:27
 */
class IT02ExceptionSpockSpec extends Specification {

    def "test 异常"() {
        when: "执行异常"
        Calculator.isOdd(num);
        then: "验证"
        def exception = thrown(expectedException)
        exception.getCode() == expectedCode
        exception.getMessage() == expectedMessage

        where: "测试数据"
        num  || expectedException | expectedCode | expectedMessage
        null || BaseException     | 300          | "num must not be null"
        -1   || BaseException     | 400          | "num must be positive"

    }

    def "test 异常2"() {
        given: "准备"
        def calculator = Mock(Calculator)
        // 闭包无法写在 where 中
        calculator.add(1, null) >> { throw new IllegalArgumentException("arg can't be null") }

        when: "执行异常"
        calculator.add(1, null)

        then: "验证"
        thrown(IllegalArgumentException)

    }

}
