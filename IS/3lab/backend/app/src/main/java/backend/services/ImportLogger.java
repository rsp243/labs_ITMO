package backend.services;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.springframework.stereotype.Service;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@Service
public class ImportLogger {

    private File logFile;

    public void openImportLogsFile() {
        logFile = new File("import_logs.txt");
    }

    public void writeLog(String logContent) {
        try (FileWriter writer = new FileWriter(logFile, true)) {
            writer.write(logContent);
            writer.write(System.lineSeparator());
            writer.close();
        } catch (IOException e) {
            log.error("Error on writing logs to file: ", e);
        }
    }
}
