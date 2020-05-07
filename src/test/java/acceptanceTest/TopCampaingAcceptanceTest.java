package acceptanceTest;

import Campaign.Campaign;
import Campaign.CampaignTop;
import CampaignApp.Click;
import CampaignApp.IdAdvertisement;
import CampaignApp.IdUser;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TopCampaingAcceptanceTest {

    @Test

    public void topCampaingAcceptanceTest(){

        Campaign campaignTop = new CampaignTop(0.85,"T001");
        Campaign campaignExpected = new CampaignTop(0.05, "T001");

        campaignTop.activatedCampaign();

        campaignTop.chargeClick(new Click(new IdUser("User01"),false, new IdAdvertisement("Adv01")));
        campaignTop.chargeClick(new Click(new IdUser("User02"),false, new IdAdvertisement("Adv02")));
        campaignTop.chargeClick(new Click(new IdUser("User03"),true, new IdAdvertisement("Adv03")));
        campaignTop.chargeClick(new Click(new IdUser("User04"),false, new IdAdvertisement("Adv04")));
        campaignTop.chargeClick(new Click(new IdUser("User04"),false, new IdAdvertisement("Adv05")));
        campaignTop.chargeClick(new Click(new IdUser("User05"),true, new IdAdvertisement("Adv05")));
        assertEquals(campaignExpected, campaignTop);

    }
}
