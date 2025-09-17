package edu.note.spock

import spock.lang.Specification

/**
 * @author jackylee
 * @date 2025/7/1 15:27
 */
class Basic02ExceptionTest extends Specification {

    def "test 异常"() {
        when: "执行异常"
        NumberUtil.isOdd(num);
        then: "验证"
        def exception = thrown(expectedException)
        exception.getCode() == expectedCode
        exception.getMessage() == expectedMessage

        where: "测试数据"
        num  || expectedException | expectedCode | expectedMessage
        null || BaseException     | 300          | "num must not be null"
        -1   || BaseException     | 400          | "num must be positive"


    }
}
