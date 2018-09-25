package br.com.api.history.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Document
@NoArgsConstructor
@AllArgsConstructor
public class Ticker {
	
	@Id
	private ObjectId _id;
	private BigDecimal last;
	private BigDecimal high;
	private BigDecimal low;
	private LocalDateTime date;

}
