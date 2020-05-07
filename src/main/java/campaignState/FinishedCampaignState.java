package campaignState;

import Campaign.CampaignStandard;
import CampaignApp.Message;
import exceptions.FinishedCampaignException;

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
