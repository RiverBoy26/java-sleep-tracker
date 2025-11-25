package ru.yandex.practicum.sleeptracker;

import java.time.LocalTime;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class ChronoTypeAnalyser implements SleepAnalyzer{
    private static final LocalTime NIGHT_START = LocalTime.of(20, 0); // 20:00
    private static final LocalTime NIGHT_END   = LocalTime.of(9, 0);  // 09:00

    @Override
    public String name() {
        return "Хронотип пользователя";
    }

    @Override
    public ChronoType analyze(List<SleepSession> sessions) {

        Map<ChronoType, Integer> counters = new EnumMap<>(ChronoType.class);
        counters.put(ChronoType.OWL, 0);
        counters.put(ChronoType.LARK, 0);
        counters.put(ChronoType.DOVE, 0);

        for (SleepSession s : sessions) {

            LocalTime start = s.start().toLocalTime();
            LocalTime end = s.end().toLocalTime();

            // Игнорируем дневные сессии: сон не пересекает "ночной" период
            boolean touchesNight =
                    start.isAfter(NIGHT_START) || start.isBefore(NIGHT_END) ||
                            end.isAfter(NIGHT_START) || end.isBefore(NIGHT_END);

            if (!touchesNight) continue;

            ChronoType type;

            if (start.isAfter(LocalTime.of(23, 0)) &&
                    end.isAfter(LocalTime.of(9, 0))) {

                type = ChronoType.OWL;

            } else if (start.isBefore(LocalTime.of(22, 0)) &&
                    end.isBefore(LocalTime.of(7, 0))) {

                type = ChronoType.LARK;

            } else {
                type = ChronoType.DOVE;
            }

            counters.put(type, counters.get(type) + 1);
        }

        // Определяем, какой тип встречается чаще всего
        int owls = counters.get(ChronoType.OWL);
        int larks = counters.get(ChronoType.LARK);
        int doves = counters.get(ChronoType.DOVE);

        // Если есть единственный лидер — возвращаем его
        if (owls > larks && owls > doves) return ChronoType.OWL;
        if (larks > owls && larks > doves) return ChronoType.LARK;

        // Иначе голубь
        return ChronoType.DOVE;
    }
}

