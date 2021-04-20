package com.employee.demo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    private String name;
    private String car;
    private String company;   

    public void setName(String value) {
        name = value;
    }

    public void setCar(String value) {
        car = value;
    }

    public void setCompamny(String value) {
        company = value;
    }

    public String getName() {
        return name;
    }

    public String getCar() {
        return car;
    }

    public String getCompany() {
        return company;
    }

}