package ru.kiianov.telegrambot.command;

import static ru.kiianov.telegrambot.command.CommandName.NO;
import static ru.kiianov.telegrambot.command.NoCommand.noCommandMessage;

public class NoCommandTest extends AbstractCommandTest {

    @Override
    String getCommandName() {
        return NO.getName();
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
