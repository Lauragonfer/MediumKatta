package campaignState;

public class ActiveCampaignState implements CampaignState {

    @Override
    public CampaignState pauseState() {
        return new PauseCampaignState();
    }

    @Override
    public CampaignState activeState() {
       return new ActiveCampaignState();
    }

    @Override
    public CampaignState finishState(){
        return new FinishedCampaignState();
    }

}
