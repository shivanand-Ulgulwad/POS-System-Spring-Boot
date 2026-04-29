package com.app.service;

import com.app.entity.Inventory;

public interface InventoryMgmtService {
    public String addStock(Inventory inventory) throws IllegalAccessException;
    public String reduceStock(Inventory inventory)throws IllegalArgumentException;
}
