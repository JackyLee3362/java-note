package note.spock;

import lombok.Data;

/**
 * @author jackylee
 * @date 2025/7/1 15:36
 */
public class BaseException extends RuntimeException {

    private Integer code;
    private String message;

    BaseException(Integer code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}
