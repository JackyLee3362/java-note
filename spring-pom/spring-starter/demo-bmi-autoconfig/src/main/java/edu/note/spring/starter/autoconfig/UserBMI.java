package edu.note.spring.starter.autoconfig;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Component;

// @Component
public class UserBMI {
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    // @Autowired
    private User user;

    public void calculateBMI() {
        Double height = user.getHeight();
        Double weight = user.getWeight();
        System.out.println("用户"+ user.getName() +"的 BMI 是" + weight / height / height);
    }
}
