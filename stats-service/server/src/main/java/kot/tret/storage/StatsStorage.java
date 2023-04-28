package kot.tret.storage;

import kot.tret.dto.DataForStats;
import kot.tret.dto.Stats;

public interface StatsStorage {

    Stats get(String name);

    void save(DataForStats dataForStats);
}
