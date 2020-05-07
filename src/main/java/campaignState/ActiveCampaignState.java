package campaignState;

import CampaignApp.Campaign;

public class ActiveCampaignState implements CampaignState {

    @Override
    public void pauseState(Campaign campaign) {
        campaign.pausedCampaign();
    }

    @Override
    public void activeState(Campaign campaign) {
        System.out.println("IS ALREADY ACTIVE");
        campaign.activatedCampaign();
    }

    @Override
    public void finishState(Campaign campaign) {
        campaign.finishedCampaign();
    }

}
