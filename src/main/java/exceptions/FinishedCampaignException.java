package exceptions;

import ClickAdvertisement.Message;

public class FinishedCampaignException extends RuntimeException{
    public FinishedCampaignException(Message messageException) {
        super(messageException.messageValue);
    }
}
