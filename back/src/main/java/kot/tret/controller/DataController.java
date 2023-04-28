package kot.tret.controller;


import kot.tret.dto.DataResponse;
import kot.tret.dto.DataResult;
import kot.tret.dto.Stats;
import kot.tret.service.DataService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "${client.url}")
public class DataController {

    private final DataService dataService;


    @PostMapping("/data")
    public ResponseEntity<DataResult> data(@RequestBody @Valid DataResponse data) {
        log.info("Получен запрос POST /data для имени: {}", data.getName());
        return ResponseEntity.ok(dataService.findAge(data));
    }

    @GetMapping("/stats")
    public ResponseEntity<Object> getStats(@RequestParam String name) {
        return dataService.getStats(name);
    }
}
