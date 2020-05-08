package campaignState;

public interface CampaignState {

    CampaignState pauseState();
    CampaignState activeState();
    CampaignState finishState();

}
