package ru.kiianov.telegrambot.command;

import org.springframework.beans.factory.annotation.Value;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.kiianov.telegrambot.command.service.SendBotMessageService;

/**
 * No {@link Command}.
 */
public class NoCommand implements Command {

   private final SendBotMessageService sendBotMessageService;

   @Value("${bot.command.noCommandMessage}")
   public static String noCommandMessage = "Я поддерживаю команды, начинающиеся со слеша(/).\n"
           + "Чтобы посмотреть список команд введите /help";

   public NoCommand(SendBotMessageService sendBotMessageService) {
      this.sendBotMessageService = sendBotMessageService;
   }

   @Override
   public void execute(Update update) {
      sendBotMessageService.sendMessage(update.getMessage().getChatId().toString(), noCommandMessage);
   }
}
