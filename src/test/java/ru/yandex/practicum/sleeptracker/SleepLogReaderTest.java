package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SleepLogReaderTest {

    @Test
    void testRead() throws Exception {
        Path tmp = Files.createTempFile("sleep", ".txt");
        Files.writeString(tmp, """
                01.01.25 23:00;02.01.25 07:00;GOOD
                02.01.25 23:30;03.01.25 08:30;BAD
                """);

        SleepLogReader reader = new SleepLogReader();
        List<SleepSession> sessions = reader.read(tmp);

        assertEquals(2, sessions.size());
        assertEquals(SleepQuality.GOOD, sessions.get(0).quality());
    }
}