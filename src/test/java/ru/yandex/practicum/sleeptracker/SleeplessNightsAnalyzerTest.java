package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SleeplessNightsAnalyzerTest {

    @Test
    void testZeroSleeplessNights() {
        List<SleepSession> sessions = List.of(
                SleepSession.fromLine("01.01.25 23:00;02.01.25 07:00;GOOD"),
                SleepSession.fromLine("02.01.25 23:30;03.01.25 08:00;NORMAL")
        );

        SleeplessNightsAnalyzer a = new SleeplessNightsAnalyzer();
        assertEquals(0L, a.analyze(sessions));
    }

    @Test
    void testOneSleeplessNight() {
        List<SleepSession> sessions = List.of(
                SleepSession.fromLine("01.01.25 23:00;02.01.25 07:00;GOOD"),
                // пропущена ночь 2→3 января
                SleepSession.fromLine("03.01.25 23:00;04.01.25 07:00;GOOD")
        );

        SleeplessNightsAnalyzer a = new SleeplessNightsAnalyzer();
        assertEquals(1L, a.analyze(sessions));
    }
}
