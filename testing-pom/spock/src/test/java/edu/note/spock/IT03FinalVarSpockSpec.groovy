package edu.note.spock

import org.powermock.reflect.Whitebox
import spock.lang.Specification
import spock.lang.Unroll

/**
 * 静态方法测试
 *
 * @author jackylee
 * @date 2025/7/1 16:19
 */
class IT03FinalVarSpockSpec extends Specification {

    @Unroll
    def "final 变量测试"() {
        given: "final 变量"
        def mockAdmin = Mock(User.class)
        Whitebox.setInternalState(UserDao.class, "ADMIN", mockAdmin)
        mockAdmin.getAge() >> 18

        when: "执行测试方法"
        def age = UserDao.ADMIN.getAge()

        then: "验证结果"
        age == 18
    }

}
