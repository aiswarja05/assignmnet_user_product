package com.test.spring.security.login.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "products")
public class Product {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank
  @Size(max = 100)
  private String productname;

  private double price;

  @NotBlank
  @Size(max = 50)
  private String datetime;


  public Product() {
  }

  public Product(Long id, @NotBlank @Size(max = 100) String productname, @NotBlank double price, @NotBlank @Size(max = 50) String datetime) {
    this.id = id;
    this.productname = productname;
    this.price = price;
    this.datetime = datetime;
  }

  public Product(@NotBlank @Size(max = 100) String productname, @NotBlank double price, @NotBlank @Size(max = 50) String datetime) {
    this.productname = productname;
    this.price = price;
    this.datetime = datetime;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getProductname() {
    return productname;
  }

  public void setProductname(String productname) {
    this.productname = productname;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public String getDatetime() {
    return datetime;
  }

  public void setDatetime(String datetime) {
    this.datetime = datetime;
  }
}
