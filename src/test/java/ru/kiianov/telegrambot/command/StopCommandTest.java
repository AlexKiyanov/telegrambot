package ru.kiianov.telegrambot.command;

import static ru.kiianov.telegrambot.command.CommandName.STOP;
import static ru.kiianov.telegrambot.command.StopCommand.STOP_COMMAND_MESSAGE;

public class StopCommandTest extends AbstractCommandTest {

    @Override
    String getCommandName() {
        return STOP.getName();
    }

    @Override
    String getCommandMessage() {
        return STOP_COMMAND_MESSAGE;
    }

    @Override
    Command getCommand() {
        return new StopCommand(sendBotMessageService, telegramUserService);
    }
}
