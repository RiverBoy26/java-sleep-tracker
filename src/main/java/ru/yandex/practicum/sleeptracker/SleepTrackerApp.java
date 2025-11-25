package ru.yandex.practicum.sleeptracker;

import java.nio.file.Path;
import java.util.List;

public class SleepTrackerApp {

    public static void main(String[] args) {
        if (args.length == 0) {
            System.err.println("Укажите путь к файлу лога сна.");
            return;
        }

        Path path = Path.of(args[0]);

        SleepLogReader reader = new SleepLogReader();
        List<SleepSession> sessions = reader.read(path);

        List<SleepAnalyzer> analyzers = List.of(
                new TotalSessionsAnalyzer(),
                new MinSessionDurationAnalyzer(),
                new MaxSessionDurationAnalyzer(),
                new AvgSessionDurationAnalyzer(),
                new BadSleepSessionsAnalyzer(),
                new SleeplessNightsAnalyzer(),
                new ChronoTypeAnalyser()
        );

        analyzers.stream()
                .map(a -> a.name() + ": " + a.analyze(sessions))
                .forEach(System.out::println);
    }
}