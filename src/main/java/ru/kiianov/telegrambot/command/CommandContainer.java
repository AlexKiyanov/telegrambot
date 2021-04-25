package ru.kiianov.telegrambot.command;

import com.google.common.collect.ImmutableMap;
import ru.kiianov.telegrambot.command.service.SendBotMessageService;

import static ru.kiianov.telegrambot.command.CommandName.*;

public class CommandContainer {

   private final ImmutableMap<String, Command> commandMap;
   private final Command UNKNOWN_COMMAND;

   public CommandContainer(SendBotMessageService sendBotMessageService) {

      commandMap = ImmutableMap.<String, Command>builder()
              .put(START.getCommandName(), new StartCommand(sendBotMessageService))
              .put(STOP.getCommandName(), new StopCommand(sendBotMessageService))
              .put(HELP.getCommandName(), new HelpCommand(sendBotMessageService))
              .put(NO.getCommandName(), new NoCommand(sendBotMessageService))
              .build();

      UNKNOWN_COMMAND = new UnknownCommand(sendBotMessageService);
   }

   public Command retrieveCommand(String commandIdentifier) {
      return commandMap.getOrDefault(commandIdentifier, UNKNOWN_COMMAND);
   }
}
