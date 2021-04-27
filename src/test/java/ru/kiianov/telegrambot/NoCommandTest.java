package ru.kiianov.telegrambot;

import ru.kiianov.telegrambot.command.Command;
import ru.kiianov.telegrambot.command.NoCommand;

import static ru.kiianov.telegrambot.command.CommandName.NO;
import static ru.kiianov.telegrambot.command.NoCommand.noCommandMessage;

public class NoCommandTest extends AbstractCommandTest {

   @Override
   String getCommandName() {
      return NO.getCommandName();
   }

   @Override
   String getCommandMessage() {
      return noCommandMessage;
   }

   @Override
   Command getCommand() {
      return new NoCommand(sendBotMessageService);
   }
}
