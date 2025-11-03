package edu.note.spock


import spock.lang.Specification

/**
 * 预期参数
 *
 * @author jackylee
 * @date 2025/11/3 14:46
 */
@SuppressWarnings("GroovyAccessibility")
// 对访问控制进行 press
class IT03ParamsSpec extends Specification {

    def dao = Mock(UserDao)
    def service = new UserServiceImpl(userDao: dao);

    def "测试参数"() {
        given:
        def user = new User(id: 1, name: "foo", age: 12)
        _ * dao.selectById(_) >> { args ->
            assert args.get(0) > 0
            return user
        }

        when:
        def info = service.getUserInfo(1)

        then:
        info.with {
            assert it.id == 1
            assert it.name == 'foo'
            assert it.age == 12
        }
    }
}
