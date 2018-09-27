package br.com.api.trading.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.trading.domain.Account;
import br.com.api.trading.service.TradingService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class TradingController {
	
	private final TradingService service;
	
	@GetMapping(value ="/ticker", produces = { MediaType.APPLICATION_JSON_VALUE})
	ResponseEntity<Account> ticker() throws Exception {
		try {
			Account account = service.findByAccount();
			return ResponseEntity.ok().body(account);
		} catch(final RuntimeException e) {
			e.printStackTrace();
			throw new Exception(e);
        }
	}

}
