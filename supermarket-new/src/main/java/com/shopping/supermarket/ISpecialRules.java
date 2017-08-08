package com.shopping.supermarket;

import com.shopping.supermarket.model.ItemRule;
import java.util.Map;

/**
 * Open for implementation of different rules.
 * Using Chain of responsibility can be extended the existing implementation to provide additional discount.
 * Using Decorator can provide free bee for certain item
 * Using Strategy implement either one of many discount strategies
 * 
 * @author Santosh
 *
 */
public interface ISpecialRules {

	int calculateTotal(Map<String,Integer> itemMap, int total);

	void addRule(ItemRule rule);
}
