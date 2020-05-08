package Campaign;

import ClickAdvertisement.Click;
import ClickAdvertisement.IdUser;
import campaignState.CampaignState;

import java.util.Date;
import java.util.List;

public interface Campaign {

     void activatedCampaign();
     void pausedCampaign();
     void finishedCampaign() ;


     boolean isCampaignFinished();
     boolean isCampaignActive();

    CampaignState actualState();

    void chargeClick(Click click);

    void fakeClicksRepay(Date dateStartBotClicks, List<IdUser> idUserBotsList);
}

