package exceptions;

import ClickAdvertisement.Message;

public class BotUserListIsEmptyException extends RuntimeException{

    public BotUserListIsEmptyException(Message messageException) {
        super(messageException.messageValue);
    }
}
