package ru.kiianov.telegrambot;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.kiianov.telegrambot.bot.TelegramBot;
import ru.kiianov.telegrambot.command.Command;
import ru.kiianov.telegrambot.command.service.SendBotMessageService;
import ru.kiianov.telegrambot.command.service.SendBotMessageServiceImpl;

/**
 * Abstract class for testing {@link Command}s.
 */
abstract class AbstractCommandTest {

   protected TelegramBot telegramBot = Mockito.mock(TelegramBot.class);
   protected SendBotMessageService sendBotMessageService = new SendBotMessageServiceImpl(telegramBot);

   abstract String getCommandName();

   abstract String getCommandMessage();

   abstract Command getCommand();

   @Test
   public void shouldProperlyExexuteCommand() throws TelegramApiException {

      Long chatId = 1234567824356L;

      Update update = new Update();
      Message message = Mockito.mock(Message.class);
      Mockito.when(message.getChatId()).thenReturn(chatId);
      Mockito.when(message.getText()).thenReturn(getCommandMessage());
      update.setMessage(message);

      SendMessage sendMessage = new SendMessage();
      sendMessage.setChatId(chatId.toString());
      sendMessage.setText(getCommandMessage());
      sendMessage.enableHtml(true);

      getCommand().execute(update);

      Mockito.verify(telegramBot).execute(sendMessage);
   }

}
