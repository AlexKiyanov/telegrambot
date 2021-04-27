package ru.kiianov.telegrambot;

import ru.kiianov.telegrambot.command.Command;
import ru.kiianov.telegrambot.command.HelpCommand;

import static ru.kiianov.telegrambot.command.CommandName.HELP;
import static ru.kiianov.telegrambot.command.HelpCommand.helpCommandMessage;

public class HelpCommandTest extends AbstractCommandTest {

   @Override
   String getCommandName() {
      return HELP.getCommandName();
   }

   @Override
   String getCommandMessage() {
      return helpCommandMessage;
   }

   @Override
   Command getCommand() {
      return new HelpCommand(sendBotMessageService);
   }
}
