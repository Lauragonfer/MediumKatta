package campaignState;

import Campaign.CampaignStandard;
import CampaignApp.Click;

public interface CampaignState {

    CampaignState pauseState();
    CampaignState activeState();
    CampaignState finishState();

}
