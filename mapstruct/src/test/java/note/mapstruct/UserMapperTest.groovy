package note.mapstruct

import spock.lang.Specification

/**
 * @author jackylee
 * @date 2025/7/1 17:13
 */
class UserMapperTest extends Specification {

    def mapper = UserMapper.INSTANCE

    def "测试映射"() {

        given:
        def userPO = new UserPO(1, "Foo", 18, new Date(), new Date())
        def userDTO = mapper.toUserDTO(userPO)

        expect:
        with(userDTO) {
            id == userPO.getId()
            name == userPO.getName()
            age == userPO.getAge()
        }

    }

}
