package kot.tret.service;

import kot.tret.dto.DataForStats;
import kot.tret.dto.Stats;

public interface StatsService {
    Stats get(String name);

    void save(DataForStats dataForStats);
}
