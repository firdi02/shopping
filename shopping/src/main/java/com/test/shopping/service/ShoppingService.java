package com.test.shopping.service;

import com.test.shopping.model.Shopping;
import com.test.shopping.model.User;
import com.test.shopping.repo.ShoppingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoppingService {
    @Autowired
    ShoppingRepo shoppingRepo;

    public List<Shopping> getAll() {
        return shoppingRepo.findAll();
    }

    public Shopping saveShop(Shopping shopping) {
        return shoppingRepo.save(shopping);
    }

    public void deleteShop(Long id) {
        shoppingRepo.deleteById(id);
    }
}
