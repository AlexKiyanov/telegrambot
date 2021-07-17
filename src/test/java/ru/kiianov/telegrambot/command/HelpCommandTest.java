package ru.kiianov.telegrambot.command;

import static ru.kiianov.telegrambot.command.CommandName.HELP;
import static ru.kiianov.telegrambot.command.HelpCommand.HELP_COMMAND_MESSAGE;

public class HelpCommandTest extends AbstractCommandTest {

    @Override
    String getCommandName() {
        return HELP.getName();
    }

    @Override
    String getCommandMessage() {
        return HELP_COMMAND_MESSAGE;
    }

    @Override
    Command getCommand() {
        return new HelpCommand(sendBotMessageService);
    }
}
