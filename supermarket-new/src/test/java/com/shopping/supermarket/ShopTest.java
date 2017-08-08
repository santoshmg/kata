package com.shopping.supermarket;

import com.shopping.supermarket.model.Item;
import com.shopping.supermarket.utility.ShopUtil;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ShopTest {
	
	
	@Before
	public void setUp(){
		
		Item item1=new Item("A", 40);
		Item item2=new Item("B", 50);
		Item item3=new Item("C", 25);
		Item item4=new Item("D", 20);
		
		ShopUtil.addItemToMaster(item1);
		ShopUtil.addItemToMaster(item2);
		ShopUtil.addItemToMaster(item3);
		ShopUtil.addItemToMaster(item4);
	}
	
	@Test
	public void checksizeOfShop() throws Exception {		
       assertThat(ShopUtil.size()).isEqualTo(4);
    }
	
	
	@Test
	public void getItemPriceFromShopForSku() throws Exception {

       assertThat(ShopUtil.getPriceForSku("A")).isEqualTo(40);
       assertThat(ShopUtil.getPriceForSku("B")).isEqualTo(50);
       assertThat(ShopUtil.getPriceForSku("I")).isEqualTo(0);

    }
	
	@Test
	public void getItemFromShopForSku() throws Exception {

       Item item=new Item("A",40);
       assertThat(ShopUtil.getItemForSku("A")).isEqualTo(item);
       assertThat(ShopUtil.getItemForSku("I")).isEqualTo(null);
    }

}
