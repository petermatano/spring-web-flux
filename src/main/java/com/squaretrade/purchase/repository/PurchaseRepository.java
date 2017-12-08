package com.squaretrade.purchase.repository;

import com.squaretrade.purchase.model.Purchase;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "purchases", path = "purchases")
public interface PurchaseRepository extends PagingAndSortingRepository<Purchase, Long> {
}
