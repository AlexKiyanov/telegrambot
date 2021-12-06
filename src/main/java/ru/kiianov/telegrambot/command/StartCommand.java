package ru.kiianov.telegrambot.command;

import org.telegram.telegrambots.meta.api.objects.Update;
import ru.kiianov.telegrambot.command.service.SendBotMessageService;
import ru.kiianov.telegrambot.repository.entity.TelegramUser;
import ru.kiianov.telegrambot.service.TelegramUserService;

/**
 * Start {@link Command}.
 */
public class StartCommand implements Command {

    public static final String startCommandMessage = "Привет! Я тестовый бот.\n"
            + "Я хочу научиться присылать тебе статьи тех авторов, которые тебе интересны."
            + " Для примера будем работать с сайтом javarush.ru";
    private final SendBotMessageService sendBotMessageService;
    private final TelegramUserService telegramUserService;

    public StartCommand(SendBotMessageService sendBotMessageService, TelegramUserService telegramUserService) {
        this.sendBotMessageService = sendBotMessageService;
        this.telegramUserService = telegramUserService;
    }

    @Override
    public void execute(Update update) {

        String chatId = update.getMessage().getChatId().toString();

        telegramUserService.findByChatId(chatId).ifPresentOrElse((TelegramUser user) -> {
                    user.setActive(true);
                    telegramUserService.save(user);
                },
                () -> {
                    TelegramUser telegramUser = new TelegramUser();
                    telegramUser.setActive(true);
                    telegramUser.setChatId(chatId);
                    telegramUserService.save(telegramUser);
                });

        sendBotMessageService.sendMessage(chatId, startCommandMessage);
    }
}
