package campaignState;

public class FinishedCampaignState implements CampaignState {

    @Override
    public CampaignState pauseState() {
        return finishState();
    }

    @Override
    public CampaignState activeState() {
        return finishState();
    }

    @Override
    public CampaignState finishState(){
        return new FinishedCampaignState();
    }

}
