package acceptanceTest;

import CampaignApp.Campaign;

import CampaignApp.Click;
import CampaignApp.IdAdvertisement;
import CampaignApp.IdUser;
import org.junit.Test;

import static org.junit.Assert.assertEquals;



public class ClickerChargerAcceptance {

    @Test

    public void clickerChargerAcceptanceTest(){

        Campaign campaign = new Campaign(0.17,"0001");
        Campaign campaignExpected = new Campaign(0.03, "0001");

        campaign.activatedCampaign();

        campaign.chargeClick(new Click(new IdUser("User01"),false, new IdAdvertisement("Adv01")));
        campaign.chargeClick(new Click(new IdUser("User02"),false, new IdAdvertisement("Adv02")));
        campaign.chargeClick(new Click(new IdUser("User03"),true, new IdAdvertisement("Adv03")));
        campaign.chargeClick(new Click(new IdUser("User04"),false, new IdAdvertisement("Adv04")));
        campaign.chargeClick(new Click(new IdUser("User04"),false, new IdAdvertisement("Adv05")));
        campaign.chargeClick(new Click(new IdUser("User05"),true, new IdAdvertisement("Adv05")));
        assertEquals(campaignExpected,campaign);

    }
}
