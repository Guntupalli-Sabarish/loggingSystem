package logger.data;

import logger.pojo.Log;

import java.util.concurrent.TimeoutException;

public class FileStore implements Datastore {

    private static final String FILE_PATH = "system.log";

    @Override
    public void addLog(Log log) {
        try (java.io.FileWriter fw = new java.io.FileWriter(FILE_PATH, true);
                java.io.BufferedWriter bw = new java.io.BufferedWriter(fw);
                java.io.PrintWriter out = new java.io.PrintWriter(bw)) {

            String timestamp = log.getTimestamp() != null ? log.getTimestamp().toString() : "N/A";
            String severity = log.getSeverity() != null ? log.getSeverity().toString() : "LOW";

            out.println(String.format("[%s] [%s] %s", timestamp, severity, log.getData()));
        } catch (java.io.IOException e) {
            System.err.println("Failed to write log down to file: " + e.getMessage());
        }
    }

    @Override
    public void appendLog() throws TimeoutException {
        // intended for batch processing, but for Level 1 we write immediately in addLog
    }

}
