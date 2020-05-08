package acceptanceTest;

import Campaign.Campaign;
import Campaign.CampaignTop;
import ClickAdvertisement.Click;
import ClickAdvertisement.IdAdvertisement;
import ClickAdvertisement.IdUser;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TopCampaingAcceptanceTest {

    @Test

    public void topCampaingAcceptanceTest(){

        Campaign campaignTop = new CampaignTop(20,"T001");
        Campaign campaignExpected = new CampaignTop(19.20, "T001");

        campaignTop.activatedCampaign();

        campaignTop.chargeClick(new Click(new IdUser("User01"),false, new IdAdvertisement("Adv01")));
        campaignTop.chargeClick(new Click(new IdUser("User02"),false, new IdAdvertisement("Adv02")));
        campaignTop.chargeClick(new Click(new IdUser("User03"),true, new IdAdvertisement("Adv03")));
        campaignTop.chargeClick(new Click(new IdUser("User04"),false, new IdAdvertisement("Adv04")));
        campaignTop.chargeClick(new Click(new IdUser("User04"),false, new IdAdvertisement("Adv05")));
        campaignTop.chargeClick(new Click(new IdUser("User05"),true, new IdAdvertisement("Adv05")));

        Date dateStartBotClicks = new Date();
        List<IdUser> idUserBotsList = new ArrayList<>();
        idUserBotsList.add(new IdUser("User06"));

        campaignTop.chargeClick(new Click(new IdUser("User06"),true, new IdAdvertisement("Adv02")));
        campaignTop.chargeClick(new Click(new IdUser("User06"),true, new IdAdvertisement("Adv03")));
        campaignTop.chargeClick(new Click(new IdUser("User06"),true, new IdAdvertisement("Adv04")));
        campaignTop.chargeClick(new Click(new IdUser("User06"),true, new IdAdvertisement("Adv05")));
        campaignTop.chargeClick(new Click(new IdUser("User06"),true, new IdAdvertisement("Adv02")));
        campaignTop.chargeClick(new Click(new IdUser("User06"),true, new IdAdvertisement("Adv03")));
        campaignTop.chargeClick(new Click(new IdUser("User06"),true, new IdAdvertisement("Adv04")));
        campaignTop.chargeClick(new Click(new IdUser("User06"),true, new IdAdvertisement("Adv05")));

        campaignTop.fakeClicksRepay(dateStartBotClicks,idUserBotsList);

        assertEquals(campaignExpected, campaignTop);

    }
}
