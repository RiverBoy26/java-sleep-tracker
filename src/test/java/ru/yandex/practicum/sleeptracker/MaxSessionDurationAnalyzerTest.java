package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MaxSessionDurationAnalyzerTest {

    @Test
    void testMaxDuration() {
        List<SleepSession> sessions = List.of(
                SleepSession.fromLine("01.01.25 22:00;02.01.25 02:00;GOOD"),
                SleepSession.fromLine("02.01.25 23:00;03.01.25 04:00;BAD")
        );

        MaxSessionDurationAnalyzer a = new MaxSessionDurationAnalyzer();
        assertEquals(300L, a.analyze(sessions));
    }
}
