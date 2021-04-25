package ru.kiianov.telegrambot.command;

import org.telegram.telegrambots.meta.api.objects.Update;
import ru.kiianov.telegrambot.command.service.SendBotMessageService;

public class StopCommand implements Command {

   private final SendBotMessageService sendBotMessageService;

   public static String stopMessage = "Все подписки деактивированы. До свидания \uD83D\uDE1F!";

   public StopCommand(SendBotMessageService sendBotMessageService) {
      this.sendBotMessageService = sendBotMessageService;
   }

   @Override
   public void execute(Update update) {
      sendBotMessageService.sendMessage(update.getMessage().getChatId().toString(), stopMessage);
   }
}
