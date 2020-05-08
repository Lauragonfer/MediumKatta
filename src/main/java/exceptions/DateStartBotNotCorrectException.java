package exceptions;

import ClickAdvertisement.Message;

public class DateStartBotNotCorrectException extends RuntimeException {
    public DateStartBotNotCorrectException(Message messageException) {
        super(messageException.messageValue);
    }
}
