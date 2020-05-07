package exceptions;

import CampaignApp.Message;

public class InvalidBudgetException extends RuntimeException {

    public InvalidBudgetException(Message messageException) {
        super(messageException.messageValue);
    }


}
