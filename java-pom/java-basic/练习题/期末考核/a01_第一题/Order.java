package 其他.d38_期末考核.a01_第一题;

import java.time.LocalDateTime;

public class Order {
    private int id;
    private String name;

    private LocalDateTime dateTime;
    private Double price;

    public Order() {
    }

    public Order(int id, String name, LocalDateTime dateTime, Double price) {
        this.id = id;
        this.name = name;
        this.dateTime = dateTime;
        this.price = price;
    }

    /**
     * 获取
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * 设置
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * 获取
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * 设置
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取
     * @return dateTime
     */
    public LocalDateTime getDateTime() {
        return dateTime;
    }

    /**
     * 设置
     * @param dateTime
     */
    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    /**
     * 获取
     * @return price
     */
    public Double getPrice() {
        return price;
    }

    /**
     * 设置
     * @param price
     */
    public void setPrice(Double price) {
        this.price = price;
    }

    public String toString() {
        return "Order{id = " + id + ", name = " + name + ", dateTime = " + dateTime + ", price = " + price + "}";
    }
}
