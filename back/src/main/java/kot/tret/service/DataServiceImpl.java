package kot.tret.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import kot.tret.StatsClient;
import kot.tret.dto.DataForStats;
import kot.tret.dto.DataResponse;
import kot.tret.dto.DataResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import static kot.tret.service.ReaderFileServiceImpl.ages;


@Service
@RequiredArgsConstructor
@Slf4j
public class DataServiceImpl implements DataService {

    private final RestTemplate restTemplate;

    private final String api = "https://api.agify.io";
    private final StatsClient statsClient;

    @Override
    public DataResult findAge(DataResponse data) {

        Short age = ages.get(data.getName());

        if (age == null) {

            ResponseEntity<String> json = restTemplate.getForEntity(api + "?name=" + data.getName(), String.class);
            ObjectMapper objectMapper = new ObjectMapper();

            try {
                JsonNode jsonNode = objectMapper.readTree(json.getBody());
                age = Short.parseShort(jsonNode.get("age").toPrettyString());
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }

        statsClient.save(new DataForStats(data.getName(), age));

        return DataResult.builder()
                .age(age)
                .build();
    }

    @Override
    public ResponseEntity<Object> getStats(String name) {
        return statsClient.getStats(name);
    }
}
