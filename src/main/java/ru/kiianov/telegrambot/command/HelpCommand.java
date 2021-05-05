package ru.kiianov.telegrambot.command;

import org.telegram.telegrambots.meta.api.objects.Update;
import ru.kiianov.telegrambot.command.service.SendBotMessageService;

import static ru.kiianov.telegrambot.command.CommandName.*;

/**
 * Help {@link Command}.
 */
public class HelpCommand implements Command {

   private final SendBotMessageService sendBotMessageService;

   public static String helpCommandMessage = String.format(
           "✨<b>Доcтупные команды</b>✨\n\n"

                   + "<b>Начать\\закончить работу с ботом</b>\n"
                   + "%s - начать работу со мной\n"
                   + "%s - приостановить работу со мной\n"
                   + "%s - получить статистику по боту\n\n"

                   + "%s - получить помощь в работе со мной\n",
           START.getCommandName(), STOP.getCommandName(), STAT.getCommandName(), HELP.getCommandName());

   public HelpCommand(SendBotMessageService sendBotMessageService) {
      this.sendBotMessageService = sendBotMessageService;
   }

   @Override
   public void execute(Update update) {
      sendBotMessageService.sendMessage(update.getMessage().getChatId().toString(), helpCommandMessage);
   }
}
