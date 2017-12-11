package com.example.purchase.controller;

import com.example.purchase.model.ReceiptPurchase;
import com.example.purchase.repository.ReceiptPurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequestMapping("/receiptPurchases")
public class ReceiptPurchaseController {

    @Autowired
    private ReceiptPurchaseRepository receiptPurchaseRepository;

    @GetMapping
    public Flux<ReceiptPurchase> getAllReceiptPurchases() {
        return receiptPurchaseRepository.findAll();
    }

    @PostMapping
    public Mono<ReceiptPurchase> createReceiptPurchase(@Valid @RequestBody ReceiptPurchase receiptPurchase)  {
        return receiptPurchaseRepository.save(receiptPurchase);
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<ReceiptPurchase>> getReceiptPurchaseById(@PathVariable(value = "id") String id) {
        return receiptPurchaseRepository.findById(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<ReceiptPurchase>> updateReceiptPurchase(@PathVariable(value = "id") String id, @Valid @RequestBody ReceiptPurchase receiptPurchase) {
        return receiptPurchaseRepository.findById(id)
                .flatMap(existingReceiptPurchase -> {
                    existingReceiptPurchase.setLineData(receiptPurchase.getLineData());
                    return receiptPurchaseRepository.save(existingReceiptPurchase);
                })
                .map(updatedReceiptPurchase -> new ResponseEntity<>(updatedReceiptPurchase, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteReceiptPurchase(@PathVariable(value = "id") String id) {
        return receiptPurchaseRepository.findById(id)
                .flatMap(existingReceiptPurchase ->
                        receiptPurchaseRepository.delete(existingReceiptPurchase)
                                .then(Mono.just(new ResponseEntity<Void>(HttpStatus.OK)))
                )
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping(value = "/stream/receiptPurchases", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ReceiptPurchase> streamAllReceiptPurchases() {
        return receiptPurchaseRepository.findAll();
    }

}
