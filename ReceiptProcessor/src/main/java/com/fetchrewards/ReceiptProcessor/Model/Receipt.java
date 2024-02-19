package com.fetchrewards.ReceiptProcessor.Model;

import jakarta.persistence.*;

import java.util.List;


@Entity
@Table(name="receipt")
public class Receipt {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String retailer;
    private String purchaseDate;
    private String purchaseTime;
    @OneToMany(mappedBy = "receipt", cascade = CascadeType.ALL)
    private List<Item> items;
    private String total;

    public Receipt() {
    }

    public Receipt(Long id, String retailer, String purchaseDate, String purchaseTime, List<Item> items, String total) {
        this.id = id;
        this.retailer = retailer;
        this.purchaseDate = purchaseDate;
        this.purchaseTime = purchaseTime;
        this.items = items;
        this.total = total;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRetailer() {
        return retailer;
    }

    public void setRetailer(String retailer) {
        this.retailer = retailer;
    }

    public String getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(String purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public String getPurchaseTime() {
        return purchaseTime;
    }

    public void setPurchaseTime(String purchaseTime) {
        this.purchaseTime = purchaseTime;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}
