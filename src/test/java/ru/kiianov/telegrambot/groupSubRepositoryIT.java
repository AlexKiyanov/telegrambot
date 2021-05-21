package ru.kiianov.telegrambot;

import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import ru.kiianov.telegrambot.repository.GroupSubRepository;
import ru.kiianov.telegrambot.repository.entity.GroupSub;
import ru.kiianov.telegrambot.repository.entity.TelegramUser;

import java.util.List;
import java.util.Optional;

/**
 * Integration-level testing for {@link GroupSubRepository}.
 */
@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class groupSubRepositoryIT {

    @Autowired
    private GroupSubRepository groupSubRepository;

    public void shouldProperlyGetAllUsersForGroupSub() {
        //when
        Optional<GroupSub> groupSubFromDb = groupSubRepository.findById(1);

        //then
        Assertions.assertTrue(groupSubFromDb.isPresent());
        Assertions.assertEquals(1, groupSubFromDb.get().getId());
        List<TelegramUser> users = groupSubFromDb.get().getUsers();
        for (int i = 0; i < users.size(); i++) {
            Assertions.assertEquals(String.valueOf(i + 1), users.get(i).getChatId());
            Assertions.assertTrue(users.get(i).isActive());
        }
    }
}
