package edu.note

import spock.lang.Specification

/**
 * @author jackylee
 * @date 2025/9/14 16:42
 */
class CalculatorSpec extends Specification {
    def "Multiply: #a * #b = #expectedResult"() {
        given: "Calculator"
        def calc = new Calculator()
        when: "multiply"
        def result = calc.multiply(a, b)
        then: "result is as expected"
        result == expectedResult
        println "result = ${result}"
        where:
        a  | b | expectedResult
        1  | 2 | 2
        -5 | 2 | -10
    }
}
