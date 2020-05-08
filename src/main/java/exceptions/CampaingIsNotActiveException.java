package exceptions;

import ClickAdvertisement.Message;

public class CampaingIsNotActiveException extends RuntimeException {
    public CampaingIsNotActiveException(Message messageException) {
        super(messageException.messageValue);
    }
}
