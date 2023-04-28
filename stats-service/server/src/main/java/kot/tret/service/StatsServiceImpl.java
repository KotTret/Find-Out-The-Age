package kot.tret.service;

import kot.tret.dto.DataForStats;
import kot.tret.dto.Stats;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import kot.tret.storage.StatsStorage;

@Service
@RequiredArgsConstructor
public class StatsServiceImpl implements StatsService {


    private final StatsStorage storage;

    @Override
    public Stats get(String name) {
        return storage.get(name);
    }

    @Override
    public void save(DataForStats dataForStats) {
        storage.save(dataForStats);
    }
}
