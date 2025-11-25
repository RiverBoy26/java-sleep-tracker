package ru.yandex.practicum.sleeptracker;

import java.util.List;

public class TotalSessionsAnalyzer implements SleepAnalyzer {

    @Override
    public String name() {
        return "Общее количество сессий сна";
    }

    @Override
    public Integer analyze(List<SleepSession> sessions) {
        return sessions.size();
    }
}
