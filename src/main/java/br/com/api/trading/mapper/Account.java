package br.com.api.trading.mapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {
	
	private ResponseData response_data;
	private Long status_code;
	private String error_message;

}


