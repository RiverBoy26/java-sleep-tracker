package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TotalSessionsAnalyzerTest {

    @Test
    void testAnalyze() {
        List<SleepSession> sessions = List.of(
                SleepSession.fromLine("01.01.25 23:00;02.01.25 07:00;GOOD"),
                SleepSession.fromLine("02.01.25 23:00;03.01.25 08:00;BAD")
        );

        TotalSessionsAnalyzer analyzer = new TotalSessionsAnalyzer();
        assertEquals(2, analyzer.analyze(sessions));
    }
}
