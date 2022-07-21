package com.analytics.analytics.services;

import com.analytics.analytics.dao.BuyerRepository;
import com.analytics.analytics.entity.Buyer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BuyerService {

    Logger logger = LoggerFactory.getLogger(BuyerService.class);
    @Autowired
    private BuyerRepository buyerRepository;

    public Buyer saveProduct(Buyer buyer) {
            return buyerRepository.saveBuyer(buyer);
    }

    public Buyer getProductById(String buyerId) {
        return buyerRepository.getBuyerById(buyerId);
    }

    public String updateProduct(String buyerId, Buyer buyer) {
        return buyerRepository.updateBuyer(buyerId, buyer);
    }

    public String updateProductBuyerEmail(String buyerId, String email) {
        Buyer buyer = buyerRepository.getBuyerById(buyerId);
        return buyerRepository.updateBuyer(buyerId, buyer);
    }
}
