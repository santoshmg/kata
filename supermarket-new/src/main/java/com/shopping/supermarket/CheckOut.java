package com.shopping.supermarket;

import com.shopping.supermarket.utility.ShopUtil;

import java.util.HashMap;
import java.util.Map;

public class CheckOut implements ICheckOut {

    private ISpecialRules specialRules;

    private Map<String,Integer> itemMap = new HashMap<String,Integer>();

    public CheckOut(ISpecialRules specialRules){
        this.specialRules = specialRules;
    }

    public void scan(String sku) {
        if(isValidSku(sku)) {
            if (itemMap.containsKey(sku)) {
                itemMap.put(sku, (itemMap.get(sku)) + 1);
            } else {
                itemMap.put(sku, 1);
            }
        }
    }

    public int total() {
        int total = 0;
        if(!isEmpty()) {
            total = itemMap.entrySet().stream()
                .mapToInt(entry -> ShopUtil.getItemForSku(entry.getKey()).getPrice()*entry.getValue())
                .reduce(0,Integer::sum);
            if(specialRules != null){
                total=specialRules.calculateTotal(itemMap, total);
            }
        }
        return total;
    }
    

    protected boolean isEmpty(){
        return getTotalNumberOfItems() == 0;
    }

    protected int getTotalNumberOfItems() {
        int count = 0;
        for(String item:itemMap.keySet()){
            count = count+itemMap.get(item);
        }
        return count;
    }


    private boolean isValidSku(String sku) {
        return !sku.isEmpty() && ShopUtil.getItemForSku(sku) != null;
    }
}
