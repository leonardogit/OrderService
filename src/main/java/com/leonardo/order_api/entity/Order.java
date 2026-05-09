package com.leonardo.order_api.entity;

import jakarta.persistence.*;

/*
Tells Hibernate:
“This class represents a database table.”
*/

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String customer;

    private String product;

    private Double price;

    public Order() {
    }

    public Order(String customer, String product, Double price) {
        this.customer = customer;
        this.product = product;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
