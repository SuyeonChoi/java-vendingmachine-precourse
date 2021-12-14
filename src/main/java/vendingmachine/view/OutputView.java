package vendingmachine.view;

import java.util.EnumMap;

import vendingmachine.model.Coin;
import vendingmachine.model.Coins;

public class OutputView {
	private static final String INPUT_HOLDING_AMOUNT = "자판기가 보유하고 있는 금액을 입력해 주세요.";
	private static final String INPUT_PRODUCT = "상품명과 가격, 수량을 입력해 주세요.";
	private static final String INPUT_INSERT_MONEY = "투입 금액을 입력해 주세요.";
	private static final String INPUT_PURCHASE_PRODUCT = "구매할 상품명을 입력해 주세요.";
	private static final String OUTPUT_HOLDING_COINS = "자판기가 보유한 동전";
	private static final String OUTPUT_CHANGE = "잔돈";
	private static final String OUTPUT_INSERT_MONEY = "투입 금액";
	private static final String COLON = ":";
	private static final String LINE_WRAP = "\n";
	private static final String WHITESPACE = " ";

	private OutputView() {
	}

	public static void printInputHoldingAmountMessage() {
		System.out.println(INPUT_HOLDING_AMOUNT);
	}

	public static void printInputProductMessage() {
		System.out.println(INPUT_PRODUCT);
	}

	public static void printCoins(Coins coins) {
		System.out.println(LINE_WRAP + OUTPUT_HOLDING_COINS);
		System.out.println(coins.toString());
	}

	public static void printExceptionMessage(String message) {
		System.out.println(message);
	}

	public static void printInputInsertMoneyMessage() {
		System.out.println(LINE_WRAP + INPUT_INSERT_MONEY);
	}

	public static void printInputPurchaseProductMessage() {
		System.out.println(INPUT_PURCHASE_PRODUCT);
	}

	public static void printInsertMoney(int insertMoney) {
		System.out.println(LINE_WRAP + OUTPUT_INSERT_MONEY + COLON + WHITESPACE + insertMoney);
	}

	public static void printChange(String coins) {
		System.out.println(OUTPUT_CHANGE);
		System.out.println(coins);
	}
}
