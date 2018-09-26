package br.com.api.bot;

import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import com.vdurmont.emoji.EmojiParser;

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
			String text = message.getText().substring(message.getText().indexOf("/"), message.getText().length());
			
			switch(text) {
				case "/start" :
					ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
					List<KeyboardRow> keyboard = new ArrayList<>();
					
					// primeira linha
					KeyboardRow row = new KeyboardRow();
					row.add(EmojiParser.parseToUnicode(":moneybag: /preco"));
					row.add(EmojiParser.parseToUnicode(":dollar: /carteira"));
				    keyboard.add(row);
				    
				    // segunda linha
				    row = new KeyboardRow();
				    row.add(EmojiParser.parseToUnicode(":heavy_plus_sign: /compra"));
				    row.add(EmojiParser.parseToUnicode(":heavy_multiplication_x: /venda"));
				    keyboard.add(row);
				    
				    // terceira linha
				    row = new KeyboardRow();
				    row.add(EmojiParser.parseToUnicode(":gem: /lucro"));
				    keyboard.add(row);
				    
				    keyboardMarkup.setKeyboard(keyboard);
					response.setReplyMarkup(keyboardMarkup);
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
