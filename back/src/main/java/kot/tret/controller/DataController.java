package kot.tret.controller;


import kot.tret.dto.DataResponse;
import kot.tret.dto.DataResult;
import kot.tret.dto.Stats;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins = "${client.url}")
public class DataController {

    @PostMapping("/data")
    public ResponseEntity<DataResult> data(@RequestBody DataResponse data) {
        return ResponseEntity.ok(new DataResult(12));
    }

    @GetMapping
    public ResponseEntity<Stats> getStats() {
        return ResponseEntity.ok(new Stats(12, "Qwerty"));
    }
}
