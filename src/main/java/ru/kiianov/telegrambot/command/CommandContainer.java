package ru.kiianov.telegrambot.command;

import com.google.common.collect.ImmutableMap;
import ru.kiianov.telegrambot.command.service.SendBotMessageService;
import ru.kiianov.telegrambot.javarushclient.JavaRushGroupClient;
import ru.kiianov.telegrambot.service.GroupSubService;
import ru.kiianov.telegrambot.service.TelegramUserService;

import static ru.kiianov.telegrambot.command.CommandName.*;

public class CommandContainer {

   private final ImmutableMap<String, Command> commandMap;
   private final Command UNKNOWN_COMMAND;

   public CommandContainer(SendBotMessageService sendBotMessageService,
                           TelegramUserService telegramUserService,
                           JavaRushGroupClient javaRushGroupClient,
                           GroupSubService groupSubService) {

      commandMap = ImmutableMap.<String, Command>builder()
              .put(START.getCommandName(), new StartCommand(sendBotMessageService, telegramUserService))
              .put(STOP.getCommandName(), new StopCommand(sendBotMessageService, telegramUserService))
              .put(HELP.getCommandName(), new HelpCommand(sendBotMessageService))
              .put(NO.getCommandName(), new NoCommand(sendBotMessageService))
              .put(STAT.getCommandName(), new StatCommand(sendBotMessageService, telegramUserService))
              .put(ADD_GROUP_SUB.getCommandName(), new AddGroupSubCommand(sendBotMessageService, javaRushGroupClient, groupSubService))
              .build();

      UNKNOWN_COMMAND = new UnknownCommand(sendBotMessageService);
   }

   public Command retrieveCommand(String commandIdentifier) {
      return commandMap.getOrDefault(commandIdentifier, UNKNOWN_COMMAND);
   }
}
