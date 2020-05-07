package exceptions;

import CampaignApp.Message;

public class CampaingIsNotActiveException extends RuntimeException {
    public CampaingIsNotActiveException(Message messageException) {
        super(messageException.messageValue);
    }
}
