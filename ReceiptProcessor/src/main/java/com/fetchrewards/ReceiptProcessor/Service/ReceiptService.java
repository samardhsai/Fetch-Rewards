package com.fetchrewards.ReceiptProcessor.Service;

import com.fetchrewards.ReceiptProcessor.Model.Item;
import com.fetchrewards.ReceiptProcessor.Model.Receipt;
import com.fetchrewards.ReceiptProcessor.Repository.ItemRepository;
import com.fetchrewards.ReceiptProcessor.Repository.ReceiptRepositiory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReceiptService {

    @Autowired
    private ReceiptRepositiory receiptRepositiory;
    @Autowired
    private ItemRepository itemRepository;

    public Long saveReceipt(Receipt receipt) {
        List<Item> items = receipt.getItems();
        receipt.setItems(new ArrayList<>());

        Receipt savedReceipt = receiptRepositiory.save(receipt);

        for (Item item : items) {
            item.setReceipt(savedReceipt);
            itemRepository.save(item);
        }
        return savedReceipt.getId();
    }

    public int calculatePointsForReceipt(Long id) {
        Receipt receipt = receiptRepositiory.findById(id)
                .orElseThrow(() -> new RuntimeException("Receipt not found for id: " + id));
        int totalPoints = 0;

        totalPoints += receipt.getRetailer().replaceAll("[^a-zA-Z0-9]", "").length();

        if (isRoundDollarAmount(receipt.getTotal())) {
            totalPoints += 50;
        }

        if (isMultipleOfQuarter(receipt.getTotal())) {
            totalPoints += 25;
        }

        totalPoints += (receipt.getItems().size() / 2) * 5;

        for (Item item : receipt.getItems()) {
            int descriptionLength = item.getShortDescription().trim().length();
            if (descriptionLength % 3 == 0) {
                double price = Double.parseDouble(item.getPrice());
                totalPoints += Math.ceil(price * 0.2);
            }
        }

        int dayOfMonth = Integer.parseInt(receipt.getPurchaseDate().split("-")[2]);
        if (dayOfMonth % 2 != 0) {
            totalPoints += 6;
        }

        int hour = Integer.parseInt(receipt.getPurchaseTime().split(":")[0]);
        if (hour >= 14 && hour < 16) {
            totalPoints += 10;
        }

        return totalPoints;
    }

    private boolean isRoundDollarAmount(String total) {
        double amount = Double.parseDouble(total);
        return amount == Math.floor(amount);
    }

    private boolean isMultipleOfQuarter(String total) {
        double amount = Double.parseDouble(total);
        return amount % 0.25 == 0;
    }
}

