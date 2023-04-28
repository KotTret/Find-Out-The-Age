package kot.tret.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

import static java.nio.charset.StandardCharsets.UTF_8;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReaderFileServiceImpl implements ReaderFileService{

    public static Map<String, Short> ages = new HashMap<>();

    @Value("classpath:name.txt")
    private Resource resource;

    @PostConstruct
    public void init() {
        try (Reader reader = new InputStreamReader(resource.getInputStream(), UTF_8)) {
            final String file = FileCopyUtils.copyToString(reader);
            String[] arrFile = file.split("\r\n");
            for (String s : arrFile) {
                String[] nameAge = s.split("_");
                ages.put(nameAge[0], Short.parseShort(nameAge[1]));
            }

            log.info("Файл прочитан");

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException("Ошибка при чтении файла", e);
        }

    }
}
