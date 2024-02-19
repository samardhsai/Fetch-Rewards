package com.fetchrewards.ReceiptProcessor.Model;

import jakarta.persistence.*;


@Entity
@Table(name = "item")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String shortDescription;
    private String price;

    @ManyToOne
    @JoinColumn(name = "receipt_id")
    private Receipt receipt;

    public Item() {
    }


    public Item(Long id, String shortDescription, String price, Receipt receipt) {
        this.receipt = receipt;
        this.id = id;
        this.shortDescription = shortDescription;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Receipt getReceipt() {
        return receipt;
    }

    public void setReceipt(Receipt receipt) {
        this.receipt = receipt;
    }
}
