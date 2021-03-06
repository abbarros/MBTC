package br.com.api.data.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.data.model.TickerData;
import br.com.api.data.service.DataService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class DataController {
	
	private final DataService service;
	
	@GetMapping(value ="/ticker", produces = { MediaType.APPLICATION_JSON_VALUE})
	ResponseEntity<TickerData> ticker() throws Exception {
		try {
			TickerData ticker = service.ticker();
			return ResponseEntity.ok().body(ticker);
		} catch(final RuntimeException e) {
			e.printStackTrace();
			throw new Exception(e);
        }
	}

}
