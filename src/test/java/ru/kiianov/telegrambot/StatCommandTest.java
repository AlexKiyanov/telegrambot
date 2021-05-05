package ru.kiianov.telegrambot;

import ru.kiianov.telegrambot.command.Command;
import ru.kiianov.telegrambot.command.StatCommand;

import static ru.kiianov.telegrambot.command.CommandName.STAT;
import static ru.kiianov.telegrambot.command.StatCommand.STAT_MESSAGE;

public class StatCommandTest extends AbstractCommandTest{

    @Override
    String getCommandName() {
        return STAT.getCommandName();
    }

    @Override
    String getCommandMessage() {
        return String.format(STAT_MESSAGE, 0);
    }

    @Override
    Command getCommand() {
        return new StatCommand(sendBotMessageService, telegramUserService);
    }
}
