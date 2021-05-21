package ru.kiianov.telegrambot.service;

import ru.kiianov.telegrambot.javarushclient.dto.GroupDiscussionInfo;
import ru.kiianov.telegrambot.repository.entity.GroupSub;

/**
 * Service for manipulating with {@link GroupSub}.
 */
public interface GroupSubService {

    GroupSub save(String chatId, GroupDiscussionInfo groupDiscussionInfo);
}
