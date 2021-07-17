package ru.kiianov.telegrambot.command;

import org.telegram.telegrambots.meta.api.objects.Update;
import ru.kiianov.telegrambot.command.service.SendBotMessageService;

/**
 * Unknown {@link Command}.
 */
public class UnknownCommand implements Command {

    public static String unknownCommandMessage = "Не понимаю вас \uD83D\uDE1F, напишите /help чтобы узнать что я понимаю.";
    private final SendBotMessageService sendBotMessageService;

    public UnknownCommand(SendBotMessageService sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public void execute(Update update) {
        sendBotMessageService.sendMessage(update.getMessage().getChatId().toString(), unknownCommandMessage);
    }
}
