package edu.note.spock

import org.junit.runner.RunWith
import org.mockito.Mockito
import org.powermock.api.mockito.PowerMockito
import org.powermock.core.classloader.annotations.PrepareForTest
import org.powermock.core.classloader.annotations.SuppressStaticInitializationFor
import org.powermock.modules.junit4.PowerMockRunner
import org.powermock.modules.junit4.PowerMockRunnerDelegate
import org.spockframework.runtime.Sputnik
import spock.lang.Specification

/**
 * @author jackylee
 * @date 2025/7/1 15:46
 */
@RunWith(PowerMockRunner.class)
@PowerMockRunnerDelegate(Sputnik.class)
@PrepareForTest([Calculator.class])
// 限制静态方法初始化，因为可能会导致【静态代码块】执行
@SuppressStaticInitializationFor("edu.note.spock.Calculator")
class IT03StaticMethodSpockSpec extends Specification {

    def userDao = Mock(UserDao.class)
    def userService = new UserService(userDao)

    void setup() {
        // Mock 静态类
        PowerMockito.mockStatic(Calculator.class)
    }

    def "静态方法测试"() {
        given: "创建对象"
        def user1 = new UserPO(1, "张三", 18)
        def user2 = new UserPO(2, "李四", 20)

        and: "mock 掉接口返回的信息"
        userDao.getUserById(_ as Integer) >>> [user1, user2]

        and :"mock 静态方法返回的信息"
        PowerMockito.when(Calculator.isOdd(Mockito.any())).thenReturn(true)

        when: "执行测试方法"
        def res1 = userService.isOddAgeUser(1)
        def res2 = userService.isOddAgeUser(2)

        then: "验证结果"
        res1 == true
        res2 == true
        // 静态方法调用验证
        PowerMockito.verifyStatic(Calculator.class, Mockito.times(2))
    }
}
