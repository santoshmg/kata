package com.shopping.supermarket;

import com.shopping.supermarket.model.Item;
import com.shopping.supermarket.model.ItemRule;
import com.shopping.supermarket.utility.ShopUtil;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CheckOutTest {

    private CheckOut cartWithNoItem;
    private CheckOut cartWithOneItem;
    private CheckOut cartWithManyItems;


    @Before
    public void setUp(){

        Item item1=new Item("A",40);
        Item item2=new Item("B", 50);
        Item item3=new Item("C", 25);
        Item item4=new Item("D", 20);

        ShopUtil.addItemToMaster(item1);
        ShopUtil.addItemToMaster(item2);
        ShopUtil.addItemToMaster(item3);
        ShopUtil.addItemToMaster(item4);

        cartWithNoItem = new CheckOut(new SpecialRules());

        cartWithOneItem = new CheckOut(new SpecialRules());
        cartWithOneItem.scan("A");

        cartWithManyItems = new CheckOut(new SpecialRules());
        cartWithManyItems.scan("A");
        cartWithManyItems.scan("B");
        cartWithManyItems.scan("A");
    }

    @Test
    public void isEmptyShouldReturnTrueForEmptyShoppingCart()  {
        assertThat(cartWithNoItem.isEmpty()).isTrue();
    }

    @Test
    public void isEmptyShouldReturnFalseForNonEmptyShoppingCart() {
        assertThat(cartWithOneItem.isEmpty()).isFalse();
    }

    @Test
    public void getTotalNumberOfItemsShouldReturnCountOfItemsInShoppingCart() throws Exception {
        assertThat(cartWithNoItem.getTotalNumberOfItems()).isEqualTo(0);
        assertThat(cartWithOneItem.getTotalNumberOfItems()).isEqualTo(1);
        assertThat(cartWithManyItems.getTotalNumberOfItems()).isEqualTo(3);
    }

    @Test
    public void totalPriceWithoutAnyRule() {

        CheckOut checkOut = new CheckOut(null);
        checkOut.scan("");
        assertThat(checkOut.total()).isEqualTo(0);

        checkOut.scan("A");
        assertThat(checkOut.total()).isEqualTo(40);

        checkOut = getCheckOut("AAAA", null);
        assertThat(checkOut.total()).isEqualTo(160);

        checkOut = getCheckOut("BBB", null);
        assertThat(checkOut.total()).isEqualTo(150);

       
    }

   


    @Test
    public void totalPriceWithSpecialRule() throws Exception {
        ISpecialRules rules = getISpecialRules();

        CheckOut checkOut = getCheckOut("",rules);
        assertThat(checkOut.total()).isEqualTo(0);

        checkOut = getCheckOut("A",rules);
        assertThat(checkOut.total()).isEqualTo(40);

        checkOut = getCheckOut("B",rules);
        assertThat(checkOut.total()).isEqualTo(50);

        checkOut = getCheckOut("ABC",rules);
        assertThat(checkOut.total()).isEqualTo(115);

        checkOut = getCheckOut("AAA",rules);
        assertThat(checkOut.total()).isEqualTo(100);

        checkOut = getCheckOut("AAAB",rules);
        assertThat(checkOut.total()).isEqualTo(150);

        checkOut = getCheckOut("AABAB",rules);
        assertThat(checkOut.total()).isEqualTo(180);

        checkOut = getCheckOut("AABB",rules);
        assertThat(checkOut.total()).isEqualTo(160);

        checkOut = getCheckOut("ABBBBC",rules);
        assertThat(checkOut.total()).isEqualTo(225);

        checkOut = getCheckOut("AAAAAABBCD",rules);
        assertThat(checkOut.total()).isEqualTo(325);
    }
    
    @Test
    public void incrementalWithSpecialRule() {
    	ISpecialRules rules = getISpecialRules();
        CheckOut co = new CheckOut(rules);
        assertThat(co.total()).isEqualTo(0);
        co.scan("A");
        assertThat(co.total()).isEqualTo(40);
        co.scan("B");
        assertThat(co.total()).isEqualTo(90);
        co.scan("A");
        assertThat(co.total()).isEqualTo(130);
        co.scan("A");
        assertThat(co.total()).isEqualTo(150);
        co.scan("B");
        assertThat(co.total()).isEqualTo(180);
    }

    @Test
    public void totalPriceForNonExistingSku() throws Exception {
        ISpecialRules rules = getISpecialRules();
        CheckOut checkOut = getCheckOut("X",rules);
        assertThat(checkOut.total()).isEqualTo(0);
    }


    private CheckOut getCheckOut(String goods, ISpecialRules rules) {
        CheckOut checkOut = new CheckOut(rules);
        for(int i=0; i<goods.length(); i++) {
            checkOut.scan(String.valueOf(goods.charAt(i)));
        }
        return checkOut;
    }

    private ISpecialRules getISpecialRules() {
        ItemRule itemRuleForA = new ItemRule();
        itemRuleForA.setSku("A");
        itemRuleForA.setDiscountCount(3);
        itemRuleForA.setDiscountPrice(100);

        ItemRule itemRuleForB = new ItemRule();
        itemRuleForB.setSku("B");
        itemRuleForB.setDiscountCount(2);
        itemRuleForB.setDiscountPrice(80);

        ISpecialRules rules = new SpecialRules();
        rules.addRule(itemRuleForA);
        rules.addRule(itemRuleForB);
        return rules;
    }
}
