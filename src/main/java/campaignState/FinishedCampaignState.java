package campaignState;

import CampaignApp.Campaign;

public class FinishedCampaignState implements CampaignState {

    @Override
    public void pauseState(Campaign campaign) {

        System.out.println("No puede ser pausada");
    }

    @Override
    public void activeState(Campaign campaign) {
        System.out.println("INo pude ser activada");
        campaign.activatedCampaign();
    }

    @Override
    public void finishState(Campaign campaign) {
        campaign.finishedCampaign();
    }
}
