package ru.kiianov.telegrambot.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.kiianov.telegrambot.command.service.SendBotMessageService;
import ru.kiianov.telegrambot.service.TelegramUserService;

/**
 * Statistics {@link Command}.
 */
public class StatCommand implements Command {
    public static final String STAT_MESSAGE = "Telegram Bot использует %s человек.";

    private final SendBotMessageService sendBotMessageService;
    private final TelegramUserService telegramUserService;


    @Autowired
    public StatCommand(SendBotMessageService sendBotMessageService, TelegramUserService telegramUserService) {
        this.sendBotMessageService = sendBotMessageService;
        this.telegramUserService = telegramUserService;
    }

    @Override
    public void execute(Update update) {
        int activeUserCount = telegramUserService.findAllActiveUsers().size();
        sendBotMessageService
                .sendMessage(update.getMessage().getChatId().toString(), String.format(STAT_MESSAGE, activeUserCount));
    }
}
