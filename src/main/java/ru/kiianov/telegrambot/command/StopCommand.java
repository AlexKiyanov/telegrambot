package ru.kiianov.telegrambot.command;

import org.telegram.telegrambots.meta.api.objects.Update;
import ru.kiianov.telegrambot.command.service.SendBotMessageService;
import ru.kiianov.telegrambot.service.TelegramUserService;

/**
 * Stop {@link Command}.
 */
public class StopCommand implements Command {
    public static final String STOP_COMMAND_MESSAGE = "Все подписки деактивированы. До свидания \uD83D\uDE1F!";

    private final SendBotMessageService sendBotMessageService;
    private final TelegramUserService telegramUserService;


    public StopCommand(SendBotMessageService sendBotMessageService, TelegramUserService telegramUserService) {
        this.sendBotMessageService = sendBotMessageService;
        this.telegramUserService = telegramUserService;
    }

    @Override
    public void execute(Update update) {

        String chatId = update.getMessage().getChatId().toString();

        sendBotMessageService.sendMessage(chatId, STOP_COMMAND_MESSAGE);
        telegramUserService.findByChatId(chatId).ifPresent(it -> {
            it.setActive(false);
            telegramUserService.save(it);
        });
    }
}
