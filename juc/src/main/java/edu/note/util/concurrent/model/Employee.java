package edu.note.util.concurrent.model;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class Employee {
    private int empNo;
    private String name;
    private String job;
    private BigDecimal salary;

}
