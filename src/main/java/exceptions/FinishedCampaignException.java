package exceptions;

import CampaignApp.Message;

public class FinishedCampaignException extends RuntimeException{
    public FinishedCampaignException(Message messageException) {
        super(messageException.messageValue);
    }
}
