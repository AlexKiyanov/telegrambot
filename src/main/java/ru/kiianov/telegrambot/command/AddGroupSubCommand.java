package ru.kiianov.telegrambot.command;

import org.telegram.telegrambots.meta.api.objects.Update;
import ru.kiianov.telegrambot.command.service.SendBotMessageService;
import ru.kiianov.telegrambot.javarushclient.JavaRushGroupClient;
import ru.kiianov.telegrambot.javarushclient.dto.GroupDiscussionInfo;
import ru.kiianov.telegrambot.javarushclient.dto.GroupRequestArgs;
import ru.kiianov.telegrambot.repository.entity.GroupSub;
import ru.kiianov.telegrambot.service.GroupSubService;

import java.util.stream.Collectors;

import static java.util.Objects.isNull;
import static org.apache.commons.lang3.StringUtils.*;
import static ru.kiianov.telegrambot.command.CommandName.ADD_GROUP_SUB;
import static ru.kiianov.telegrambot.command.CommandUtils.getChatId;
import static ru.kiianov.telegrambot.command.CommandUtils.getMessage;

/**
 * Add Group subscription {@link Command}.
 */
public class AddGroupSubCommand implements Command {

    private final SendBotMessageService sendBotMessageService;
    private final JavaRushGroupClient javaRushGroupClient;
    private final GroupSubService groupSubService;

    public AddGroupSubCommand
            (SendBotMessageService sendBotMessageService,
             JavaRushGroupClient javaRushGroupClient,
             GroupSubService groupSubService) {
        this.sendBotMessageService = sendBotMessageService;
        this.javaRushGroupClient = javaRushGroupClient;
        this.groupSubService = groupSubService;
    }

    @Override
    public void execute(Update update) {
        if (getMessage(update).equalsIgnoreCase(ADD_GROUP_SUB.getCommandName())) {
            sendGroupIdList(getChatId(update));
            return;
        }

        String groupId = getMessage(update).split(SPACE)[1];
        String chatId = getChatId(update);

        if (isNumeric(groupId)) {
            GroupDiscussionInfo groupById = javaRushGroupClient.getGroupById(Integer.parseInt(groupId));
            if (isNull(groupById.getId())) {
                sendGroupNotFound(chatId, groupId);
            }
            GroupSub savedGroupSub = groupSubService.save(chatId, groupById);
            sendBotMessageService.sendMessage(chatId, "Подписал на группу: " + savedGroupSub.getTitle());
        } else {
            sendGroupNotFound(chatId, groupId);
        }
    }

    private void sendGroupNotFound(String chatId, String groupId) {
        String groupNotFoundMessage = "Нет группы с ID = \"%s\"";
        sendBotMessageService.sendMessage(chatId, String.format(groupNotFoundMessage, groupId));
    }

    private void sendGroupIdList(String chatId) {
        String groupIds = javaRushGroupClient.getGroupList(GroupRequestArgs.builder().build()).stream()
                .map(group -> String.format("%s - %s \n", group.getTitle(), group.getId()))
                .collect(Collectors.joining());

        String message = "Чтобы подписаться на группу - передай комадну вместе с ID группы. \n" +
                "Например: /addGroupSub 16. \n\n" +
                "я подготовил список всех групп - выберай какую хочешь :) \n\n" +
                "имя группы - ID группы \n\n" +
                "%s";

        sendBotMessageService.sendMessage(chatId, String.format(message, groupIds));
    }
}
