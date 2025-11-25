package ru.yandex.practicum.sleeptracker;

import java.util.List;

public class BadSleepSessionsAnalyzer implements SleepAnalyzer {

    @Override
    public String name() {
        return "Количество сессий с плохим качеством сна";
    }

    @Override
    public Long analyze(List<SleepSession> sessions) {
        return sessions.stream()
                .filter(s -> s.quality() == SleepQuality.BAD)
                .count();
    }
}