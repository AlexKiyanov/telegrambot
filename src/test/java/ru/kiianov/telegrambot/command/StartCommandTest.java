package ru.kiianov.telegrambot.command;

import static ru.kiianov.telegrambot.command.CommandName.START;
import static ru.kiianov.telegrambot.command.StartCommand.startCommandMessage;

public class StartCommandTest extends AbstractCommandTest {

    @Override
    String getCommandName() {
        return START.getName();
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
