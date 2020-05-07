package campaignState;

import CampaignApp.Campaign;

public class PauseCampaignState  implements CampaignState {

    @Override
    public void pauseState(Campaign campaign) {
        System.out.println("IS ALREADY PAUSE");
        campaign.pausedCampaign();
    }

    @Override
    public void activeState(Campaign campaign) {
        campaign.activatedCampaign();
    }

    @Override
    public void finishState(Campaign campaign) {
        campaign.finishedCampaign();
    }
}
