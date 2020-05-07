package CampaignApp;

public enum Message {

    CampaingIsNotActive("The Campaign is not Active You can not charge click"),
    FinishedCampaingWarning("The Campaign is FINISHED you can not charge clicks or change the status"),
    InvalidBudget("The amount of the Budget is invalid can be more than 0"),
    NotEnoughtBalanceToNormalClick("The Campaign not have enought balance for Normal charge"),
    NotEnoughtBalanceToPremiumClick("The Campaign not have enought balance for Premium charge"),
    DuplicateClick("This is a duplicate Click , not will be charge to the campaing");

    public final String messageValue;

    Message (String message){
        this.messageValue = message;
    }
}
