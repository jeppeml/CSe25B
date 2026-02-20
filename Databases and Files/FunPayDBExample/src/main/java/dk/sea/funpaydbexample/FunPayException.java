package dk.sea.funpaydbexample;

public class FunPayException extends Exception{
    public FunPayException(String message){
        super(message);
    }
    public FunPayException(String message, Throwable cause){
        super(message,cause);
    }
}
