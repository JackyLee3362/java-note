package edu.note.spring.starter.autoconfig;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class UserBmiService {

    private User user;

    public Double calculateBMI() {
        Double height = user.getHeight();
        Double weight = user.getWeight();
        return weight / (height * height);
    }
}
