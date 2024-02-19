package com.fetchrewards.ReceiptProcessor.Repository;

import com.fetchrewards.ReceiptProcessor.Model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository  extends JpaRepository<Item, Long> {
}
