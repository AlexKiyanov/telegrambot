package ru.kiianov.telegrambot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kiianov.telegrambot.javarushclient.dto.GroupDiscussionInfo;
import ru.kiianov.telegrambot.repository.GroupSubRepository;
import ru.kiianov.telegrambot.repository.entity.GroupSub;
import ru.kiianov.telegrambot.repository.entity.TelegramUser;

import javax.ws.rs.NotFoundException;
import java.util.Optional;

@Service
public class GroupSubServiceImpl implements GroupSubService {

    private final GroupSubRepository groupSubRepository;
    private final TelegramUserService telegramUserService;

    @Autowired
    public GroupSubServiceImpl(GroupSubRepository groupSubRepository, TelegramUserService telegramUserService) {
        this.groupSubRepository = groupSubRepository;
        this.telegramUserService = telegramUserService;
    }

    @Override
    public GroupSub save(String chatId, GroupDiscussionInfo groupDiscussionInfo) {

        TelegramUser telegramUser = telegramUserService.findByChatId(chatId).orElseThrow(NotFoundException::new);
        //TODO: Add exception handling
        GroupSub groupSub;
        Optional<GroupSub> groupSubFromDb = groupSubRepository.findById(groupDiscussionInfo.getId());
        if (groupSubFromDb.isPresent()) {
            groupSub = groupSubFromDb.get();
            Optional<TelegramUser> first = groupSub.getUsers().stream()
                    .filter(it -> it.getChatId().equalsIgnoreCase(chatId))
                    .findFirst();
            if (first.isEmpty()) {
                groupSub.addUser(telegramUser);
            }
        } else {
            groupSub = new GroupSub();
            groupSub.addUser(telegramUser);
            groupSub.setId(groupDiscussionInfo.getId());
            groupSub.setTitle(groupDiscussionInfo.getTitle());
        }

        return groupSubRepository.save(groupSub);
    }
}
