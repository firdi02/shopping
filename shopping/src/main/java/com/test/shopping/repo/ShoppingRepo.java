package com.test.shopping.repo;

import com.test.shopping.model.Shopping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingRepo extends JpaRepository<Shopping, Long> {


}
