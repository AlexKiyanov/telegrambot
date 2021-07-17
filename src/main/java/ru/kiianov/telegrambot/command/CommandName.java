package ru.kiianov.telegrambot.command;

public enum CommandName {
    /**
     * Enumeration for {@link Command}'s.
     */
    START("/start"),
    STOP("/stop"),
    HELP("/help"),
    NO("nocommand"),
    STAT("/stat"),
    ADD_GROUP_SUB("/addgroupsub"),
    LIST_GROUP_SUB("/listgroupsub");

    private final String name;

    CommandName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
