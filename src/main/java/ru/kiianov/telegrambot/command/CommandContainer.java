package ru.kiianov.telegrambot.command;

import com.google.common.collect.ImmutableMap;
import ru.kiianov.telegrambot.command.service.SendBotMessageService;
import ru.kiianov.telegrambot.javarushclient.JavaRushGroupClient;
import ru.kiianov.telegrambot.service.GroupSubService;
import ru.kiianov.telegrambot.service.TelegramUserService;

import static ru.kiianov.telegrambot.command.CommandName.*;

public class CommandContainer {

    private final ImmutableMap<String, Command> commandMap;
    private final Command unknownCommand;

    public CommandContainer(SendBotMessageService sendBotMessageService,
                            TelegramUserService telegramUserService,
                            JavaRushGroupClient javaRushGroupClient,
                            GroupSubService groupSubService) {

        commandMap = ImmutableMap.<String, Command>builder()
                .put(START.getName(), new StartCommand(sendBotMessageService, telegramUserService))
                .put(STOP.getName(), new StopCommand(sendBotMessageService, telegramUserService))
                .put(HELP.getName(), new HelpCommand(sendBotMessageService))
                .put(NO.getName(), new NoCommand(sendBotMessageService))
                .put(STAT.getName(), new StatCommand(sendBotMessageService, telegramUserService))
                .put(ADD_GROUP_SUB.getName(),
                        new AddGroupSubCommand(sendBotMessageService, javaRushGroupClient, groupSubService))
                .put(LIST_GROUP_SUB.getName(), new ListGroupSubCommand(sendBotMessageService, telegramUserService))
                .build();

        unknownCommand = new UnknownCommand(sendBotMessageService);
    }

    public Command retrieveCommand(String commandIdentifier) {
        return commandMap.getOrDefault(commandIdentifier, unknownCommand);
    }
}
