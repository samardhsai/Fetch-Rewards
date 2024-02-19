package com.fetchrewards.ReceiptProcessor.Controller;

import com.fetchrewards.ReceiptProcessor.Model.Receipt;
import com.fetchrewards.ReceiptProcessor.Service.ReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
;

@RestController
@RequestMapping("/receipts")
public class ReceiptController {

    @Autowired
    private ReceiptService receiptService;

    @PostMapping("/process")
    public String processReceipt(@RequestBody Receipt receipt) {
        Long receiptId = receiptService.saveReceipt(receipt);
        return "{\"id\": \"" + receiptId + "\"}";
    }

    @GetMapping("/{id}/points")
    public String getPointsForReceipt(@PathVariable Long id) {
        int points = receiptService.calculatePointsForReceipt(id);
        return "{\"points\": " + points + "}";
    }
}
