package ru.yandex.practicum.sleeptracker;

import java.time.Duration;
import java.util.List;

public class AvgSessionDurationAnalyzer implements SleepAnalyzer {

    @Override
    public String name() {
        return "Средняя продолжительность сессии (минуты)";
    }

    @Override
    public Double analyze(List<SleepSession> sessions) {
        return sessions.stream()
                .mapToLong(s -> Duration.between(s.start(), s.end()).toMinutes())
                .average()
                .orElse(0.0);
    }
}