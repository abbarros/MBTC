package br.com.api.trading.mapper;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Balance {
	
	private Coin brl;
	private Coin btc;
	private Coin ltc;
	
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public class Coin {
		private BigDecimal available;
		private BigDecimal total;
	}

}