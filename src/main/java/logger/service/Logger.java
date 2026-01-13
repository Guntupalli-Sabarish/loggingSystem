package logger.service;

import logger.data.FileStore;
import logger.pojo.Log;

import java.util.Set;

public class Logger {

    private FileStore fileStore;

    private Set<Log> logTrackSet; // capacity

    private java.util.concurrent.BlockingQueue<Log> logsProcessingQueue;

    private static volatile Logger logger = null;

    private Logger() {
        logTrackSet = new java.util.concurrent.CopyOnWriteArraySet<>();
        fileStore = new FileStore();
        logsProcessingQueue = new java.util.concurrent.LinkedBlockingQueue<>();

        // Start the Consumer (Background Worker)
        Thread consumerThread = new Thread(this::processQueue);
        consumerThread.setDaemon(true); // Ensure it dies when the app dies
        consumerThread.start();
    }

    public static Logger getInstance() {
        if (logger == null) {
            synchronized (Logger.class) {
                if (logger == null) {
                    logger = new Logger();
                }
            }
        }
        return logger;
    }

    // PRODUCER: Just drops the log in the queue and returns instantly
    // (Non-Blocking)
    public void addLog(Log log) {
        logTrackSet.add(log); // Keep in memory for UI
        logsProcessingQueue.offer(log); // Add to queue for async persistence
    }

    // CONSUMER: Runs in background, takes from queue, writes to disk
    private void processQueue() {
        while (true) {
            try {
                // .take() blocks (waits) until a log is available
                Log log = logsProcessingQueue.take();
                fileStore.addLog(log);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

    public Set<Log> getLogs() {
        return logTrackSet;
    }

    public void clearLogs() {
        logTrackSet.clear();
    }

}
