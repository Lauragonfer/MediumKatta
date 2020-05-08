package ClickAdvertisement;

public enum Message {

    CampaingIsNotActive("The Campaign is not Active You can not charge click"),
    FinishedCampaingWarning("The Campaign is FINISHED you can not charge clicks or change the status"),
    InvalidBudget("The amount of the Budgets.Budget is invalid can be more than 0"),
    NotEnoughtBalanceToNormalClick("The Campaign not have enought balance for Normal charge"),
    NotEnoughtBalanceToPremiumClick("The Campaign not have enought balance for Premium charge"),
    DuplicateClick("This is a duplicate Click , not will be charge to the campaing"),
    dateStartBotNotCorrect("The date to check the bots is in the future, need to be today or before today to be valid"),
    botUserListIsEmpty("The list is empty. Need at least one Bot User in the list ");

    public final String messageValue;

    Message (String message){
        this.messageValue = message;
    }
}
