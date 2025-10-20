package LLD.LoggingSystem;

public class MainLogger {
    public static void main(String[] args) {
        Logger loggerChain = LoggerChainBuilder.getLoggerChain();

        loggerChain.logMessage(LogLevel.INFO, "System boot successful.");
        loggerChain.logMessage(LogLevel.DEBUG, "Loading user configurations...");
        loggerChain.logMessage(LogLevel.ERROR, "Failed to connect to database!");
    }
}
