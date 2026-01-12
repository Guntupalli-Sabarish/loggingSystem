package logger.service;

import logger.data.FileStore;
import logger.pojo.Log;

import java.util.Queue;
import java.util.Set;

public class Logger {

    private FileStore fileStore;

    private Set<Log> logTrackSet; // capacity

    private Queue<Set<Log>> logsProcessingQueue; // capacity

    private Integer timeout;

    private static Logger logger = null;

    private Logger() {
        logTrackSet = new java.util.concurrent.CopyOnWriteArraySet<>();
        fileStore = new FileStore();
    }

    public static Logger getInstance() {
        if (logger == null) {
            logger = new Logger();
        }
        return logger;
    }

    public void addLog(Log log) {
        logTrackSet.add(log);
        fileStore.addLog(log);
        // Todo: add timestamp and thread and stacktrace
    }

    public Set<Log> getLogs() {
        return logTrackSet;
    }

    public void appendLog() {
        // Todo: Handle exception of append Log from datastore
    }

    private void flushLogProcessingQueue() {

    }

    private void put() {

    }

    public void clearLogs() {
        logTrackSet.clear();
        // Option: we could also clear the fileStore, but typically file logs are kept
        // for audit.
        // For this user request, clearing the UI/Memory is likely what they mean by
        // "Delete".
    }

}
