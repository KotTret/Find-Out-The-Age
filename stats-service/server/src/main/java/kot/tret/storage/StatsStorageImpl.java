package kot.tret.storage;

import kot.tret.dto.DataForStats;
import kot.tret.dto.Stats;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class StatsStorageImpl implements StatsStorage {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public Stats get(String name) {
        String sqlQuery = "SELECT views FROM stats s  WHERE s.name = ?";
        Integer views = jdbcTemplate.queryForObject(sqlQuery, Integer.class, name);

        sqlQuery = "SELECT name FROM stats order by views desc LIMIT 1";
        String popularName = jdbcTemplate.queryForObject(sqlQuery, String.class);

        sqlQuery = "SELECT name, age FROM stats order by age desc LIMIT 1";
        Map<String, Object> old =  jdbcTemplate.queryForMap(sqlQuery);

        return Stats.builder()
                .count(views)
                .popularName(popularName)
                .oldName((String) old.get("name"))
                .oldAge((Integer) old.get("age"))
                .build();
    }

    @Override
    public void save(DataForStats dataForStats) {

        String sqlQuery = "SELECT  count(*) FROM stats WHERE name = ?";

        int count = jdbcTemplate.queryForObject(sqlQuery, Integer.class, dataForStats.getName());

        if (count == 0) {
            sqlQuery = "INSERT INTO stats(name, views, age) VALUES(?,?,?)";
            jdbcTemplate.update(sqlQuery, dataForStats.getName(), 1, dataForStats.getAge());
        } else {
            sqlQuery = "UPDATE stats set views=views+1, age = (age+?)/2  WHERE name = ?";
            jdbcTemplate.update(sqlQuery, dataForStats.getAge(), dataForStats.getName());
        }
    }
}

