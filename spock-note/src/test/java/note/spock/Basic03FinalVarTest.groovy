package note.spock

import org.powermock.reflect.Whitebox
import spock.lang.Specification
import spock.lang.Unroll

/**
 * @author jackylee
 * @date 2025/7/1 16:19
 */
class Basic03FinalVarTest extends Specification {

    @Unroll
    def "final 变量测试"() {
        given: "final 变量"
        def mockAdmin = Mock(UserPO.class)
        Whitebox.setInternalState(UserDao.class, "ADMIN", mockAdmin)
        mockAdmin.getAge() >> 18

        when: "执行测试方法"
        def age = UserDao.ADMIN.getAge()

        then: "验证结果"
        age == 18

    }

}
