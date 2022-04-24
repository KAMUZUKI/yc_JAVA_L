package src.com.mu.project16_exception;

import javax.management.remote.SubjectDelegationPermission;

public class AgeNotRightException extends Exception{
    public AgeNotRightException(){
        super("Exception : 给的年龄不合法，必须在 18到50之间！");
    }

    public AgeNotRightException(String message){
        super(message);
    }
}
