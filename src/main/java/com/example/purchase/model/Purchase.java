package com.example.purchase.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Entity
@Getter
@Setter
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String userId;
    @ElementCollection
    private List<String> lineData;
    private String vendor;
    private Date purchaseData;
    private Date scanDate;
    @ElementCollection
    private List<String> potentialPurchaseId;
    @ElementCollection
    private Map<String, String> actualPurchaseIdVsWarrantyIdMap;
    private String imageCdnId;
    private String imageCdnShaHash;
    private String imageCdnUri;
}
