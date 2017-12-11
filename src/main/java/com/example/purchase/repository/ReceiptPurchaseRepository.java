package com.example.purchase.repository;

import com.example.purchase.model.ReceiptPurchase;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReceiptPurchaseRepository extends ReactiveMongoRepository<ReceiptPurchase, String> {
}
