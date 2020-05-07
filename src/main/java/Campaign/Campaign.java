package Campaign;

import CampaignApp.Click;
import campaignState.CampaignState;

public interface Campaign {

     void activatedCampaign();
     void pausedCampaign();
     void finishedCampaign() ;


     boolean isCampaignFinished();
     boolean isCampaignActive();

    CampaignState actualState();

    void chargeClick(Click click);
}

