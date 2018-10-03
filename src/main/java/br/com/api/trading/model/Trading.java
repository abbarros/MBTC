package br.com.api.trading.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Trading {
	
	private String tapi_method;
	private String tapi_nonce;
	
}
