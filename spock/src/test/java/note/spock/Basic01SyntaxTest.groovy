package note.spock

import spock.lang.Specification

/**
 * @author jackylee
 * @date 2025/7/1 14:49
 * @description Spock Mock 测试语法
 */
class Basic01SyntaxTest extends Specification {

    // mock 的类必须是接口类
    def userDao = Mock(UserDao)
    def userService = new UserService(userDao)

    def "test Mock 测试"() {
        given: "准备数据"
        def user1 = new UserPO(1, "张三", 18)
        def user2 = new UserPO(2, "李四", 20)

        and: "mock 方法"
        // 语法 1: 普通语法
        // userDao.getUserById(1) >> user1
        // userDao.getUserById(2) >> user2
        // 语法 2
        // userDao.getUserById(_ as Integer) >> user1 >> user2
        // 语法 3
        userDao.getUserById(_ as Integer) >>> [user1, user2]
        // 语法 4: 列表语法
        // userDao.getUserById(_) >>> [[user1, user2],[user3, user4],[user5, user6]]
        // userDao.getUserById(_) >> [user1, user2] >> [user3, user4] >> [user5, user6]
        // 语法 5: 参数匹配
        // userDao.getUserById("specific") // 期望传入特定参数
        // userDao.getUserById(_) // 接受任意参数
        // userDao.getUserById(!null) // 参数不能为 null
        // userDao.getUserById({ it > 0 }) // 自定义参数验证
        // 语法 6: 如果希望根据不同的参数返回不同的结果
        // userDao.getUserById(1) >> user1
        // userDao.getUserById(2) >> user2
        // userDao.getUserById(_) >> new UserPO(3, "王五", 25)
        // userDao.getUserById(_) >> { args -> args[0] > 0 ? user1 : user2 } // 使用闭包


        when: "执行测试方法"
        def user = userService.getUserInfo(1)

        then: "结果验证 | 断言"
        // 语法 1: 验证调用次数
        // _ * userDao.getUserById(_) // 不关心调用次数
        // 2 * userDao.getUserById(_) // 调用两次
        // 0 * userDao.getUserById(_) // 期望不被调用
        // (1..3) * userDao.getUserById(_)  // 期望被调用 1-3 次
        with(user) {
            name == "张三"
            age == 18
            id == 1
        }
        with([user1, user2]) {
            user1.name == "张三"
            user2.name == "李四"
        }

    }


}
