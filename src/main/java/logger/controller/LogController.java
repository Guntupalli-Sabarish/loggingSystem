package logger.controller;

import logger.pojo.Log;
import logger.service.Logger;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/logs")
@CrossOrigin(origins = "*") // Allow frontend to access
public class LogController {

    private final Logger loggerService = Logger.getInstance();

    @GetMapping
    public Set<Log> getLogs() {
        return loggerService.getLogs();
    }

    @PostMapping
    public Log addLog(@RequestBody Log log) {
        // Set timestamp if null (simple fallback)
        if (log.getTimestamp() == null) {
            log.setTimestamp(new java.sql.Timestamp(System.currentTimeMillis()));
        }
        loggerService.addLog(log);
        return log;
    }

    @DeleteMapping
    public void clearLogs() {
        loggerService.clearLogs();
    }
}
