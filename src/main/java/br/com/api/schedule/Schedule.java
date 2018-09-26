package br.com.api.schedule;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import br.com.api.bot.client.TelegramWebhookClient;
import br.com.api.bot.model.Webhook;
import br.com.api.data.model.TickerData;
import br.com.api.data.service.DataService;
import br.com.api.history.domain.Ticker;
import br.com.api.history.service.HistoryService;
import lombok.RequiredArgsConstructor;

@Component
@RefreshScope
@RequiredArgsConstructor
public class Schedule {
	
	private final DataService service;
	private final HistoryService history;
	private final TelegramWebhookClient webhook;
	
	@Scheduled(fixedDelay = 3600000)
	public void run() {
		Ticker lastTicker = history.lastTicker();
		TickerData ticker = service.ticker();
		
		execute(lastTicker, ticker);
		this.save(ticker);
	}
	
	private void save(TickerData ticker) {
		Ticker entity = Ticker.builder()
			.last(ticker.getTicker().getLast())
			.high(ticker.getTicker().getHigh())
			.low(ticker.getTicker().getLow())
			.date(ticker.getTicker().getDate())
			.build();
		
		history.save(entity);
	}
	
	private void execute(Ticker lastTicker, TickerData ticker) {
		if(Objects.nonNull(lastTicker) && Objects.nonNull(ticker)) {
			BigDecimal subtract = lastTicker.getLast().subtract(ticker.getTicker().getLast()).setScale(2, RoundingMode.HALF_EVEN);
			if(subtract.intValue() >= 5) {
				webhook.sendMessage(new Webhook("AVISO: LTC variou 5 ou mais moedas, R$ " + ticker.getTicker().getLast()));
			}
		}
	}

}
