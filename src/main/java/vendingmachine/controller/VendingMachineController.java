package vendingmachine.controller;

import java.util.ArrayList;
import java.util.List;

import vendingmachine.model.Coins;
import vendingmachine.model.Product;
import vendingmachine.model.Products;
import vendingmachine.util.StringUtils;
import vendingmachine.util.validator.MoneyValidator;
import vendingmachine.view.InputView;

public class VendingMachineController {
	private Coins coins;
	private Products products;

	public VendingMachineController() {
		initCoins();
		initProducts();
	}

	private void initCoins() {
		String inputAmountMoney = InputView.readAmountMoney();
		try {
			MoneyValidator.validate(inputAmountMoney);
		} catch (IllegalArgumentException exception) {
			initCoins();
		}
		coins = new Coins(Integer.parseInt(inputAmountMoney));
	}

	public void initProducts() {
		String inputProducts = InputView.readProducts();
		List<String> separatedProducts = StringUtils.splitProduct(inputProducts);
		separatedProducts = StringUtils.removeProductBrackets(separatedProducts);
		List<Product> productList = new ArrayList<>();
		separatedProducts.forEach(product -> productList.add(new Product(StringUtils.parseProductDetail(product))));
		products = new Products(productList);
	}
}
