package edu.note.spock;

/**
 * @author jackylee
 * @date 2025/11/3 14:54
 */
public interface UserService {

    String getServiceInfo();

    User getUserInfo(Integer idNumber);

    Boolean isOddAgeUser(Integer idNumber);
}
