package com.mgbt.turismoargentina_backend.utility_classes;

import com.mgbt.turismoargentina_backend.model.entity.Purchase;

public class PurchaseWithMessage {
    private Purchase purchase;
    private String message;

    public Purchase getPurchase() {
        return purchase;
    }

    public void setPurchase(Purchase purchase) {
        this.purchase = purchase;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
