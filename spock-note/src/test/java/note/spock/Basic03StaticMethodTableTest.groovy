package note.spock

import org.junit.runner.RunWith
import org.mockito.Mockito
import org.powermock.api.mockito.PowerMockito
import org.powermock.core.classloader.annotations.PrepareForTest
import org.powermock.core.classloader.annotations.SuppressStaticInitializationFor
import org.powermock.modules.junit4.PowerMockRunnerDelegate
import org.powermock.modules.junit4.PowerMockRunner
import spock.lang.Specification
import org.spockframework.runtime.Sputnik

/**
 * @author jackylee
 * @date 2025/7/1 16:00
 */
@RunWith(PowerMockRunner.class)
@PowerMockRunnerDelegate(Sputnik.class)
@PrepareForTest([NumberUtil.class])
@SuppressStaticInitializationFor("note.spock.NumberUtil")
class Basic03StaticMethodTableTest extends Specification {

    def userDao = Mock(UserDao.class)
    def userService = new UserService(userDao)

    void setup() {
        // Mock 静态类
        PowerMockito.mockStatic(NumberUtil.class)
    }

    def "静态方法测试"() {
        given: "创建对象"
        def user1 = new UserPO(1, "张三", 18)
        def user2 = new UserPO(2, "李四", 20)

        and: "mock 掉接口返回的信息"
        userDao.getUserById(_ as Integer) >>> [user1, user2]

        and: "mock 静态方法返回的信息"
        PowerMockito.when(NumberUtil.isOdd(Mockito.any())).thenReturn(flag)

        expect: "执行测试方法"
        userService.isOddAgeUser(1) == res

        where:
        flag  | res
        true  | true
        false | false
    }
}
