package ru.yandex.practicum.sleeptracker;

import java.util.List;

public class SleepCountAnalyzer implements SleepAnalyzer {
    @Override
    public String name() {
        return "Количество записей";
    }

    @Override
    public String analyze(List<SleepSession> sessions) {
        long count = sessions.size();
        return "Всего сессий сна: " + count;
    }
}
