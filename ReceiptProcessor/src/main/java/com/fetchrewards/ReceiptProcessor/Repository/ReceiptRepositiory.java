package com.fetchrewards.ReceiptProcessor.Repository;

import com.fetchrewards.ReceiptProcessor.Model.Receipt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReceiptRepositiory extends JpaRepository<Receipt, Long> {
    Optional<Receipt> findById(Long id);
}
