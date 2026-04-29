package com.app.service.impl;

import com.app.entity.Inventory;
import com.app.exception.InsufficienStockException;
import com.app.exception.InvalidQuantityException;
import com.app.exception.InventoryNotFoundException;
import com.app.repository.InventoryRepository;

import com.app.service.InventoryMgmtService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class InventoryMgmtServiceImpl implements InventoryMgmtService {

    private final InventoryRepository repo;

    @Override
    @Transactional
    public String addStock(Inventory inventory) {


        if (inventory.getQuantity() == null || inventory.getQuantity() <= 0) {
            throw new InvalidQuantityException("Quantity must be greater than 0");
        }


        Inventory existing = repo.findByProductAndBranch(
                inventory.getProduct(),
                inventory.getBranch()
        ).orElse(null);


        if (existing != null) {
            existing.setQuantity(existing.getQuantity() + inventory.getQuantity());
            repo.save(existing);
        }


        else {
            Inventory newInventory = new Inventory();
            newInventory.setProduct(inventory.getProduct());
            newInventory.setBranch(inventory.getBranch());
            newInventory.setQuantity(inventory.getQuantity());

            repo.save(newInventory);
        }

        return "Stock added successfully";
    }

    @Override
    @Transactional
    public String reduceStock(Inventory inventory) throws IllegalArgumentException{

        if (inventory.getQuantity() == null || inventory.getQuantity() <= 0){
            throw new InvalidQuantityException("quantity must be > 0");
        }

       Inventory existing = repo.findByProductAndBranch(inventory.getProduct(),inventory.getBranch())
               .orElseThrow(()-> new InventoryNotFoundException("Inventory Not found"));

        if (inventory.getQuantity() > existing.getQuantity()){
            throw new InsufficienStockException("Insufficient stock");
        }

        existing.setQuantity(existing.getQuantity() - inventory.getQuantity());

        repo.save(existing);

        return "Stock Reduced successful";
    }
}
