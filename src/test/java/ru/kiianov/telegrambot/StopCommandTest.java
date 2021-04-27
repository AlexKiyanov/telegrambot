package ru.kiianov.telegrambot;

import ru.kiianov.telegrambot.command.Command;
import ru.kiianov.telegrambot.command.StopCommand;

import static ru.kiianov.telegrambot.command.CommandName.STOP;
import static ru.kiianov.telegrambot.command.StopCommand.stopCommandMessage;

public class StopCommandTest extends AbstractCommandTest {

   @Override
   String getCommandName() {
      return STOP.getCommandName();
   }

   @Override
   String getCommandMessage() {
      return stopCommandMessage;
   }

   @Override
   Command getCommand() {
      return new StopCommand(sendBotMessageService);
   }
}
