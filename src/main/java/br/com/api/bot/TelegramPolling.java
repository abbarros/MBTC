package br.com.api.bot;

import java.math.RoundingMode;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import br.com.api.data.model.TickerData;
import br.com.api.data.service.DataService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class TelegramPolling extends TelegramLongPollingBot {

    @Value("${bot.token}")
	private String token;
	
	@Value("${bot.username}")
	private String username;
	
	private final DataService service;
	
	@Override
	public String getBotToken() {
		return token;
	}
	
	@Override
	public String getBotUsername() {
		return username;
	}
	
	@Override
	public void onUpdateReceived(Update update) {
		if (update.hasMessage()) {
			Message message = update.getMessage();
			SendMessage response = new SendMessage();
			Long chatId = message.getChatId();
			response.setChatId(chatId);
			String text = message.getText();
			
			switch(text) {
				case "/preco" :
					TickerData ticker = service.ticker();
					response.setText("R$ " + ticker.getTicker().getLast().setScale(2,  RoundingMode.CEILING.HALF_EVEN).toString());
					break;
				default: 
					response.setText("Comando inválido");
			}
			
			try {
				execute(response);
				log.info("Sent message \"{}\" to {}", text, chatId);
			} catch (TelegramApiException e) {
				log.error("Failed to send message \"{}\" to {} due to error: {}", text, chatId, e.getMessage());
			}
		}
	}

	@PostConstruct
	public void start() {
		log.info("username: {}, token: {}", username, token);
	}

}
