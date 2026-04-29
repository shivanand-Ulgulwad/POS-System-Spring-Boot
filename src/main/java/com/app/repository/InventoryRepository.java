package com.app.repository;

import com.app.entity.Branch;
import com.app.entity.Inventory;
import com.app.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InventoryRepository extends JpaRepository<Inventory,Long> {


    Optional<Inventory> findByProductAndBranch(Product product, Branch branch);
}
