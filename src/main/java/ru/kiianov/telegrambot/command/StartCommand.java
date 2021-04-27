package ru.kiianov.telegrambot.command;

import org.telegram.telegrambots.meta.api.objects.Update;
import ru.kiianov.telegrambot.command.service.SendBotMessageService;

/**
 * Start {@link Command}.
 */
public class StartCommand implements Command {

   private final SendBotMessageService sendBotMessageService;

   public static String startCommandMessage = "Привет! Я тестовый бот.\n"
           + "Я хочу научиться присылать тебе статьи тех авторов, которые тебе интересны."
           + " Для примера будем работать с сайтом javarush.ru";

   public StartCommand(SendBotMessageService sendBotMessageService) {
      this.sendBotMessageService = sendBotMessageService;
   }

   @Override
   public void execute(Update update) {
      sendBotMessageService.sendMessage(update.getMessage().getChatId().toString(), startCommandMessage);
   }
}
