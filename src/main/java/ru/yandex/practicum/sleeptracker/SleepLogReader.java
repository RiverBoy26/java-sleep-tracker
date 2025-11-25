package ru.yandex.practicum.sleeptracker;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

public class SleepLogReader {
    public List<SleepSession> read(Path file) {
        try (Stream<String> lines = Files.lines(file)){
            return lines.filter(line -> !line.isBlank())
                    .map(SleepSession::fromLine)
                    .toList();
        } catch (IOException e) {
            throw new RuntimeException("Ошибка чтения файла сна", e);
        }
    }
}
