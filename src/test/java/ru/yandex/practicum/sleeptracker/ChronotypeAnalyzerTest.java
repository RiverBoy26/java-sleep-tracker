package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ChronotypeAnalyzerTest {

    @Test
    void testOwlDominant() {
        ChronoTypeAnalyser a = new ChronoTypeAnalyser();

        List<SleepSession> sessions = List.of(
                SleepSession.fromLine("01.01.25 23:30;02.01.25 09:30;GOOD"),
                SleepSession.fromLine("02.01.25 23:40;03.01.25 10:00;NORMAL"),
                SleepSession.fromLine("03.01.25 22:00;04.01.25 08:00;NORMAL")
        );

        assertEquals(ChronoType.OWL, a.analyze(sessions));
    }

    @Test
    void testLarkDominant() {
        ChronoTypeAnalyser a = new ChronoTypeAnalyser();

        List<SleepSession> sessions = List.of(
                SleepSession.fromLine("01.01.25 21:30;02.01.25 06:30;GOOD"),
                SleepSession.fromLine("02.01.25 21:00;03.01.25 06:00;GOOD"),
                SleepSession.fromLine("03.01.25 23:30;04.01.25 08:00;NORMAL")
        );

        assertEquals(ChronoType.LARK, a.analyze(sessions));
    }

    @Test
    void testTieBecomesDove() {
        ChronoTypeAnalyser a = new ChronoTypeAnalyser();

        List<SleepSession> sessions = List.of(
                SleepSession.fromLine("01.01.25 23:30;02.01.25 09:30;GOOD"), // сова
                SleepSession.fromLine("02.01.25 21:00;03.01.25 06:00;GOOD")  // жаворонок
        );

        assertEquals(ChronoType.DOVE, a.analyze(sessions));
    }

    @Test
    void testTripleTieAlsoDove() {
        ChronoTypeAnalyser a = new ChronoTypeAnalyser();

        List<SleepSession> sessions = List.of(
                SleepSession.fromLine("01.01.25 23:30;02.01.25 09:30;GOOD"),
                SleepSession.fromLine("02.01.25 21:00;03.01.25 06:00;GOOD"),
                SleepSession.fromLine("03.01.25 22:30;04.01.25 08:00;NORMAL")
        );

        assertEquals(ChronoType.DOVE, a.analyze(sessions));
    }
}
