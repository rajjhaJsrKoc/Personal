package LLD.LoggingSystem;

public class ErrorLogger extends Logger{
    public ErrorLogger() {
        this.level=LogLevel.ERROR;
    }

    @Override
    protected void write(String message) {
        System.out.println("ERROR: " + message);
    }
}
