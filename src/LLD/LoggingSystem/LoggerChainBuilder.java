package LLD.LoggingSystem;

class LoggerChainBuilder {
    public static Logger getLoggerChain() {
        Logger errorLogger = new ErrorLogger();
        Logger debugLogger = new DebugLogger();
        Logger infoLogger = new InfoLogger();

        errorLogger.setNextLogger(debugLogger);
        debugLogger.setNextLogger(infoLogger);

        return errorLogger; // head of the chain
    }
}

