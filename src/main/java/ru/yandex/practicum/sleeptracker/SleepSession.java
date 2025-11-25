package ru.yandex.practicum.sleeptracker;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public record SleepSession(LocalDateTime start, LocalDateTime end, SleepQuality quality) {

    private static final DateTimeFormatter FMT =
            DateTimeFormatter.ofPattern("dd.MM.yy HH:mm");

    public static SleepSession fromLine(String line) {
        String[] parts = line.split(";");
        return new SleepSession(
                LocalDateTime.parse(parts[0], FMT),
                LocalDateTime.parse(parts[1], FMT),
                SleepQuality.valueOf(parts[2])
        );
    }
}
