package ru.yandex.practicum.sleeptracker;

import java.time.Duration;
import java.util.List;

public class MaxSessionDurationAnalyzer implements SleepAnalyzer {

    @Override
    public String name() {
        return "Максимальная продолжительность сессии (минуты)";
    }

    @Override
    public Long analyze(List<SleepSession> sessions) {
        return sessions.stream()
                .mapToLong(s -> Duration.between(s.start(), s.end()).toMinutes())
                .max()
                .orElse(0);
    }
}
