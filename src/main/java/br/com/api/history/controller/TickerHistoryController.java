package br.com.api.history.controller;

import java.util.Collection;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.history.domain.Ticker;
import br.com.api.history.service.TickerHistoryService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class TickerHistoryController {
	
	private final TickerHistoryService historyService;
	
	@GetMapping(value ="/history/ticker", produces = { MediaType.APPLICATION_JSON_VALUE})
	ResponseEntity<Collection<Ticker>> ticker() throws Exception {
		try {
			Collection<Ticker> ticker = historyService.historyTicker();
			return ResponseEntity.ok().body(ticker);
		} catch(final RuntimeException e) {
			e.printStackTrace();
			throw new Exception(e);
        }
	}

}
