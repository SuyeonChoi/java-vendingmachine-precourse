package vendingmachine.model;

import java.util.List;

public class Products {
	private final List<Product> products;

	public Products(List<Product> productList) {
		this.products = productList;
	}
}
