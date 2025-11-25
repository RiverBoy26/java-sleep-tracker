package ru.yandex.practicum.sleeptracker;

import java.util.List;

public interface SleepAnalyzer {
    String name();

    Object analyze(List<SleepSession> sessions);
}
