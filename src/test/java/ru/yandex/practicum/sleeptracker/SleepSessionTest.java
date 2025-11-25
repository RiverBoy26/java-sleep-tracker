package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class SleepSessionTest {

    @Test
    void testFromLine() {
        String line = "01.01.25 23:00;02.01.25 07:00;GOOD";

        SleepSession s = SleepSession.fromLine(line);

        assertEquals(LocalDateTime.of(2025,1,1,23,0), s.start());
        assertEquals(LocalDateTime.of(2025,1,2,7,0), s.end());
        assertEquals(SleepQuality.GOOD, s.quality());
    }
}