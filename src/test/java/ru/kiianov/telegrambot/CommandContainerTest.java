package ru.kiianov.telegrambot;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.kiianov.telegrambot.command.Command;
import ru.kiianov.telegrambot.command.CommandContainer;
import ru.kiianov.telegrambot.command.CommandName;
import ru.kiianov.telegrambot.command.UnknownCommand;
import ru.kiianov.telegrambot.command.service.SendBotMessageService;

import java.util.Arrays;

@DisplayName("Unit-level testing for CommandContainer")
public class CommandContainerTest {

   private CommandContainer commandContainer;

   @BeforeEach
   public void init() {
      SendBotMessageService sendBotMessageService = Mockito.mock(SendBotMessageService.class);
      commandContainer = new CommandContainer(sendBotMessageService);
   }

   @Test
   public void shouldGetAllExistsCommands() {
      Arrays.stream(CommandName.values())
              .forEach(commandName -> {
                 Command command = commandContainer.retrieveCommand(commandName.getCommandName());
                 Assertions.assertNotEquals(UnknownCommand.class, command.getClass());
              });
   }

   @Test
   public void shouldReturnUnknownCommand() {
      String unknownCommand = "/ldkfdf";

      Command command = commandContainer.retrieveCommand(unknownCommand);

      Assertions.assertEquals(UnknownCommand.class, command.getClass());
   }
}
