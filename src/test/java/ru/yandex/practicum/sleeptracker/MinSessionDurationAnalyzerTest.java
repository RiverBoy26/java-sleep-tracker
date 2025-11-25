package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MinSessionDurationAnalyzerTest {

    @Test
    void testMinDuration() {
        List<SleepSession> sessions = List.of(
                SleepSession.fromLine("01.01.25 23:00;02.01.25 01:00;GOOD"),
                SleepSession.fromLine("02.01.25 23:00;03.01.25 00:00;BAD")
        );

        MinSessionDurationAnalyzer a = new MinSessionDurationAnalyzer();
        assertEquals(60L, a.analyze(sessions));
    }
}
