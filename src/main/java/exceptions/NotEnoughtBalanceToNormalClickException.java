package exceptions;

import CampaignApp.Message;

public class NotEnoughtBalanceToNormalClickException extends RuntimeException {

    public NotEnoughtBalanceToNormalClickException(Message messageException) {
        super(messageException.messageValue);
    }
}
