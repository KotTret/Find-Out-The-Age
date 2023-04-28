package kot.tret.service;

import kot.tret.dto.DataResponse;
import kot.tret.dto.DataResult;
import org.springframework.http.ResponseEntity;

public interface DataService {
    DataResult findAge(DataResponse data);

    ResponseEntity<Object> getStats(String name);
}
