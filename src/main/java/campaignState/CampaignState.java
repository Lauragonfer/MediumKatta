package campaignState;

import CampaignApp.Campaign;

public interface CampaignState {

    void pauseState(Campaign campaign);
    void activeState(Campaign campaign);
    void finishState(Campaign campaign);

}
