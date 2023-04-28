package kot.tret.controller;

import kot.tret.dto.DataForStats;
import kot.tret.dto.Stats;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import kot.tret.service.StatsService;


@RestController
@RequiredArgsConstructor
@Slf4j
public class StatsController {

    private final StatsService service;

    @GetMapping("/stats")
    public ResponseEntity<Stats> get(@RequestParam String name) {

        log.info("Получен запрос GET /stats");
        return new ResponseEntity<>(service.get(name), HttpStatus.OK);
    }

    @PostMapping("/stats")
    public ResponseEntity save(@RequestBody DataForStats dataForStats) {
        log.info("Получен запрос POST /stats");
        service.save(dataForStats);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
