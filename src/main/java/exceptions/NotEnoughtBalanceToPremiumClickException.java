package exceptions;

import CampaignApp.Message;

public class NotEnoughtBalanceToPremiumClickException extends RuntimeException {

    public NotEnoughtBalanceToPremiumClickException(Message messageException) {
        super(messageException.messageValue);
    }
}
