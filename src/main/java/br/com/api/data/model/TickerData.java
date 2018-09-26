package br.com.api.data.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TickerData {
	
	private Ticker ticker;
	
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public class Ticker {
		
		private BigDecimal last;
		private BigDecimal high;
		private BigDecimal low;
		private LocalDateTime date;
		
		public void setDate(Long longValue) {
			this.date = LocalDateTime.now();
		}
	}

}
