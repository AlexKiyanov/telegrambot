package ru.kiianov.telegrambot;

import ru.kiianov.telegrambot.command.Command;
import ru.kiianov.telegrambot.command.StartCommand;

import static ru.kiianov.telegrambot.command.CommandName.START;
import static ru.kiianov.telegrambot.command.StartCommand.startCommandMessage;

public class StartCommandTest extends AbstractCommandTest {

   @Override
   String getCommandName() {
      return START.getCommandName();
   }

   @Override
   String getCommandMessage() {
      return startCommandMessage;
   }

   @Override
   Command getCommand() {
      return new StartCommand(sendBotMessageService, telegramUserService);
   }
}
