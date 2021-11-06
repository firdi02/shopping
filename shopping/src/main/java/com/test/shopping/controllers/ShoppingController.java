package com.test.shopping.controllers;

import com.test.shopping.model.Shopping;
import com.test.shopping.model.User;
import com.test.shopping.service.ShoppingService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ShoppingController {

    @Autowired
    ShoppingService shoppingService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/shop/getAll")
    public List<Shopping> getAll(){
        return shoppingService.getAll();
    }

    @PostMapping("/shop/tambah")
    public Shopping saveShop(@RequestBody Shopping shopping){
        return shoppingService.saveShop(shopping);
    }

    @DeleteMapping("/shopDelete/{id}")
    public String deleteMobil(@PathVariable long id){
        shoppingService.deleteShop(id);
        return "DATA shopping BERHASIL DIHAPUS "+id;
    }


}
