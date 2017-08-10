package cn.edu.swpu.cins.exception;

public class NoSeckillListException extends SeckillException {
    public NoSeckillListException(String message) {
        super(message);
    }

    public NoSeckillListException(String message, Throwable cause) {
        super(message, cause);
    }
}
