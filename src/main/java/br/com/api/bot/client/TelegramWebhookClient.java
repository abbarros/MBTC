package br.com.api.bot.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.api.bot.model.Webhook;

/* 
 * {https://api.telegram.org/bot621449462:AAE9hS_unYHW_ans4jhQ8tRkkFsDHpcJ4PA/setWebhook?url=https://integram.org/webhook/cfo-OyxwzwZ} 
 */
@FeignClient(name = "telegramWebhookClient", url = "https://integram.org/webhook/cfo-OyxwzwZ")
public interface TelegramWebhookClient {

	@PostMapping(value = "/")
	void sendMessage(@RequestBody Webhook webhook);
	
}
