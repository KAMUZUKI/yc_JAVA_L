package src.com.mu.project16_exception;

public class AgeNotRightRuntimeException extends RuntimeException{
    public AgeNotRightRuntimeException(){
        super("RuntimeException : 给的年龄不合法，必须在 18到50之间！");
    }

    public AgeNotRightRuntimeException(String message){
        super(message);
    }
}
