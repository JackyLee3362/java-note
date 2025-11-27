package edu.note.spock

import spock.lang.Specification

/**
 * @author jackylee
 * @date 2025/11/25 16:13
 */
class IT03StaticMethodSpockV2Spec extends Specification {

    def userDao = Mock(UserDao.class)
    def userService = new UserServiceImpl(userDao)

    def "静态方法测试 - 直接静态方法"() {
        given: "创建对象"
        GroovyMock(Calculator, global: true)
        // 类似的方法还有 GroovySpy GroovyStub
        Calculator.isOdd(_ as Integer) >>> [true, true]

        when: "执行测试方法"
        def res1 = Calculator.isOdd(18)
        def res2 = Calculator.isOdd(20)

        then: "验证结果"
        res1
        res2
    }

    // https://www.cnblogs.com/auv2009/p/18630755
    // https://github.com/spockframework/spock/blob/master/spock-specs/src/test/groovy/org/spockframework/smoke/mock/GroovySpiesThatAreGlobal.groovy
    def "静态方法测试"() {
        given: "创建对象"
        def user1 = new User(1, "张三", 18)
        def user2 = new User(2, "李四", 20)
        // GroovyMock(Calculator, global: true);
        GroovyStub(Calculator, global: true);
        userDao.selectById(_ as Integer) >>> [user1, user2]
        Calculator.isOdd(_ as Integer) >>> [true, true]

        when: "执行测试方法"
        def res1 = userService.isOddAgeUser(1)
        def res2 = userService.isOddAgeUser(2)

        then: "验证结果"
        res1 == true
        // res2 == true
    }

}
