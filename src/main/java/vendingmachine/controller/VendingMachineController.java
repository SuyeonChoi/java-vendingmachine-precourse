package vendingmachine.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import vendingmachine.model.Coin;
import vendingmachine.model.Coins;
import vendingmachine.model.Product;
import vendingmachine.model.Products;
import vendingmachine.util.ErrorMessageConstants;
import vendingmachine.util.StringUtils;
import vendingmachine.util.validator.MoneyValidator;
import vendingmachine.util.validator.ProductValidator;
import vendingmachine.view.InputView;
import vendingmachine.view.OutputView;

public class VendingMachineController {
	private Coins coins;
	private Products products;
	private int insertMoney;

	public VendingMachineController() {
		initCoins();
		OutputView.printCoins(coins);
		initProducts();
		initInsertMoney();
	}

	private void initCoins() {
		String inputAmountMoney = InputView.readAmountMoney();
		try {
			MoneyValidator.validate(inputAmountMoney);
		} catch (IllegalArgumentException exception) {
			OutputView.printExceptionMessage(exception.getMessage());
			initCoins();
		}
		coins = new Coins(Integer.parseInt(inputAmountMoney));
	}

	private void initProducts() {
		String inputProducts = InputView.readProducts();
		List<Product> productList = parseProducts(inputProducts);
		try {
			ProductValidator.validate(productList);
		} catch (IllegalArgumentException exception) {
			OutputView.printExceptionMessage(exception.getMessage());
			initProducts();
		}
		products = new Products(productList);
	}

	private List<Product> parseProducts(String inputProducts) {
		List<Product> productList = new ArrayList<>();
		try {
			List<String> separatedProducts = StringUtils.splitProduct(inputProducts);
			separatedProducts = StringUtils.removeProductBrackets(separatedProducts);
			separatedProducts.forEach(product -> productList.add(new Product(StringUtils.parseProductDetail(product))));
		} catch (Exception exception) {
			OutputView.printExceptionMessage(ErrorMessageConstants.PRODUCT_INPUT_FORM_EXCEPTION_MESSAGE);
			initProducts();
		}
		return productList;
	}

	private void initInsertMoney() {
		String inputInsertMoney = InputView.readInsertMoney();
		try {
			MoneyValidator.validateInsertMoney(inputInsertMoney);
		} catch (IllegalArgumentException exception) {
			OutputView.printExceptionMessage(exception.getMessage());
			initInsertMoney();
		}
		insertMoney = Integer.parseInt(inputInsertMoney);
	}

	public void run() {
		while (products.selectMinimumPrice() <= insertMoney && products.exists()) {
			OutputView.printInsertMoney(insertMoney);
			try {
				purchaseProduct();
			} catch (IllegalArgumentException exception) {
				OutputView.printExceptionMessage(exception.getMessage());
			}
		}
		OutputView.printInsertMoney(insertMoney);
		Map<Coin, Integer> change = coins.calculateChange(insertMoney);
		OutputView.printChange(coins.coinsToString(change));
	}

	private void purchaseProduct() {
		String inputPurchaseProduct = InputView.readPurchaseProduct();
		Product product = products.findProduct(inputPurchaseProduct);
		product.purchase();
		insertMoney = product.subtractProductPrice(insertMoney);
	}
}
