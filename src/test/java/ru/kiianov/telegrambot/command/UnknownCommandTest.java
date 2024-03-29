package ru.kiianov.telegrambot.command;

import static ru.kiianov.telegrambot.command.UnknownCommand.unknownCommandMessage;

public class UnknownCommandTest extends AbstractCommandTest {

    @Override
    String getCommandName() {
        return "/sgdgdth";
    }

    @Override
    String getCommandMessage() {
        return unknownCommandMessage;
    }

    @Override
    Command getCommand() {
        return new UnknownCommand(sendBotMessageService);
    }
}
