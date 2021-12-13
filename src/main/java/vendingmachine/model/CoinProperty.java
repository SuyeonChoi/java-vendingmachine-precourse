package vendingmachine.model;

import java.util.EnumMap;

import camp.nextstep.edu.missionutils.Randoms;
import vendingmachine.Coin;

public class CoinProperty {
	private EnumMap<Coin, Integer> coinMap;

	public CoinProperty() {
		this.coinMap = new EnumMap<>(Coin.class);
	}

	public void initialize(int amount) {
		while (amount > 0) {
			Coin coin = selectCoinType();
			int coinNumber = generateRandomValueInRange(coin.divideByCoinAmount(amount));
			coinMap.put(coin, coinMap.getOrDefault(coin, 0) + coinNumber);
			amount -= coin.multiplyByCoinNumber(coinNumber);
		}
	}

	private int generateRandomValueInRange(int endInclusive) {
		return Randoms.pickNumberInRange(0, endInclusive);
	}

	private Coin selectCoinType() {
		int coinIndex = Randoms.pickNumberInRange(0, Coin.values().length - 1);
		return Coin.values()[coinIndex];
	}
}