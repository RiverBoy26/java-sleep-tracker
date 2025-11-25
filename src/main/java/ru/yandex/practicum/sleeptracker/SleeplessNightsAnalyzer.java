package ru.yandex.practicum.sleeptracker;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class SleeplessNightsAnalyzer implements SleepAnalyzer {

    private static final LocalTime NIGHT_START = LocalTime.of(20, 0);
    private static final LocalTime NIGHT_END   = LocalTime.of(9, 0);

    @Override
    public String name() {
        return "Количество бессонных ночей";
    }

    @Override
    public Long analyze(List<SleepSession> sessions) {
        if (sessions.isEmpty()) return 0L;
        Set<LocalDate> nightDates = sessions.stream()
                .filter(this::touchesNight)
                .map(s -> {
                    LocalDateTime start = s.start();
                    if (start.toLocalTime().isBefore(LocalTime.NOON)) {
                        return start.toLocalDate().minusDays(1);
                    } else {
                        return start.toLocalDate();
                    }
                })
                .collect(Collectors.toSet());

        if (nightDates.isEmpty()) return 0L;

        LocalDate min = nightDates.stream().min(LocalDate::compareTo).get();
        LocalDate max = nightDates.stream().max(LocalDate::compareTo).get();

        long totalDays = max.toEpochDay() - min.toEpochDay() + 1;

        long present = nightDates.size();

        return totalDays - present;
    }

    private boolean touchesNight(SleepSession s) {
        LocalTime st = s.start().toLocalTime();
        LocalTime en = s.end().toLocalTime();

        return st.isAfter(NIGHT_START) || st.isBefore(NIGHT_END)
                || en.isAfter(NIGHT_START) || en.isBefore(NIGHT_END)
                || (s.start().toLocalDate().isBefore(s.end().toLocalDate()));
    }
}