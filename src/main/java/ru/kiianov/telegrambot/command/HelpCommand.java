package ru.kiianov.telegrambot.command;

import org.telegram.telegrambots.meta.api.objects.Update;
import ru.kiianov.telegrambot.command.service.SendBotMessageService;

import static ru.kiianov.telegrambot.command.CommandName.*;

/**
 * Help {@link Command}.
 */
public class HelpCommand implements Command {
    public static final String HELP_COMMAND_MESSAGE = String.format(
            "✨<b>Доcтупные команды</b>✨\n\n"

                    + "<b>Начать\\закончить работу с ботом</b>\n"
                    + "%s - начать работу со мной\n"
                    + "%s - приостановить работу со мной\n\n"

                    + "<b>Работа с подписками на группы:</b>\n"
                    + "%s - подписаться на группу статей\n"
                    + "%s - получить список групп, на которые подписан\n\n"

                    + "%s - получить статистику по боту\n"
                    + "%s - получить помощь в работе со мной\n",
            START.getName(),
            STOP.getName(),

            ADD_GROUP_SUB.getName(),
            LIST_GROUP_SUB.getName(),

            STAT.getName(),
            HELP.getName());

    private final SendBotMessageService sendBotMessageService;


    public HelpCommand(SendBotMessageService sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public void execute(Update update) {
        sendBotMessageService.sendMessage(update.getMessage().getChatId().toString(), HELP_COMMAND_MESSAGE);
    }
}
