package com.shopping.supermarket.utility;

import com.shopping.supermarket.model.Item;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * Shop to hold all items which will return other properties of Item based on Sku.
 *
 */
public class ShopUtil {
	
	private static Set<Item> allItemSet=new HashSet<Item>();
	
    public static void addItemToMaster(Item item){
    	allItemSet.add(item);
    }
    
    public static Item getItemForSku(String sku){
        Item itemToReturn = null;
        Optional<Item> itemOptional = allItemSet.stream().filter(item -> item.getSku().equals(sku)).findFirst();
        if(itemOptional.isPresent()){
            itemToReturn = itemOptional.get();
        }
    	return itemToReturn;
    }
    
    public static int getPriceForSku(String sku){
        int price = 0;
        Optional<Item> itemOptional = allItemSet.stream().filter(item -> item.getSku().equals(sku)).findFirst();
        if(itemOptional.isPresent()){
            price = itemOptional.get().getPrice();
        }

        return price;
    }
    
    public static int size(){
    	return allItemSet.size();
    }


}
