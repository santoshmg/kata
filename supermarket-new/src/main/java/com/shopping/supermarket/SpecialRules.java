package com.shopping.supermarket;

import com.shopping.supermarket.model.ItemRule;
import com.shopping.supermarket.utility.ShopUtil;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class SpecialRules implements ISpecialRules {
    
    private Set<ItemRule> itemRules = new HashSet<ItemRule>();
    
    @Override
    public void addRule(ItemRule rule) {
        itemRules.add(rule);
    }

    @Override
    public int calculateTotal(Map<String, Integer> itemMap, int total) {
        String sku = "";
        for (ItemRule itemRule : itemRules) {
            sku = itemRule.getSku();
            if(itemMap.containsKey(sku)){
               int actualDiscount= getActualPrice(sku, itemRule) - itemRule.getDiscountPrice();
               int totalDiscount=(itemMap.get(sku)/itemRule.getDiscountCount())* actualDiscount;
               total = total-totalDiscount;
            }
        }
        return total;
    }

    private int getActualPrice(String sku, ItemRule itemRule) {
        return itemRule.getDiscountCount()* ShopUtil.getPriceForSku(sku);
    }
}
