package exceptions;

import ClickAdvertisement.Message;

public class DuplicateClickException extends RuntimeException{


    public DuplicateClickException(Message messageException) {
        super(messageException.messageValue);
    }
}
