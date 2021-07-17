package ru.kiianov.telegrambot.bot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.kiianov.telegrambot.command.CommandContainer;
import ru.kiianov.telegrambot.command.service.SendBotMessageServiceImpl;
import ru.kiianov.telegrambot.javarushclient.JavaRushGroupClient;
import ru.kiianov.telegrambot.service.GroupSubService;
import ru.kiianov.telegrambot.service.TelegramUserService;

import static ru.kiianov.telegrambot.command.CommandName.NO;

@Component
public class TelegramBot extends TelegramLongPollingBot {

    public static final String COMMAND_PREFIX = "/";
    private final CommandContainer commandContainer;
    @Value("${bot.username}")
    private String username;
    @Value("${bot.token}")
    private String token;

    @Autowired
    public TelegramBot(TelegramUserService telegramUserService,
                       JavaRushGroupClient javaRushGroupClient,
                       GroupSubService groupSubService) {
        this.commandContainer = new CommandContainer(
                new SendBotMessageServiceImpl(this),
                telegramUserService,
                javaRushGroupClient,
                groupSubService);
    }

    @Override
    public String getBotUsername() {
        return username;
    }

    @Override
    public String getBotToken() {
        return token;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String message = update.getMessage().getText().trim();
            if (message.startsWith(COMMAND_PREFIX)) {
                String commandIdentifier = message.split(" ")[0].toLowerCase();

                commandContainer.retrieveCommand(commandIdentifier).execute(update);
            } else {
                commandContainer.retrieveCommand(NO.getName()).execute(update);
            }
        }
    }
}
