package acceptanceTest;

import Campaign.CampaignStandard;

import CampaignApp.Click;
import CampaignApp.IdAdvertisement;
import CampaignApp.IdUser;
import org.junit.Test;

import static org.junit.Assert.assertEquals;



public class ClickerChargerAcceptance {

    @Test

    public void clickerChargerAcceptanceTest(){

        CampaignStandard campaignStandard = new CampaignStandard(0.17,"0001");
        CampaignStandard campaignExpected = new CampaignStandard(0.03, "0001");

        campaignStandard.activatedCampaign();

        campaignStandard.chargeClick(new Click(new IdUser("User01"),false, new IdAdvertisement("Adv01")));
        campaignStandard.chargeClick(new Click(new IdUser("User02"),false, new IdAdvertisement("Adv02")));
        campaignStandard.chargeClick(new Click(new IdUser("User03"),true, new IdAdvertisement("Adv03")));
        campaignStandard.chargeClick(new Click(new IdUser("User04"),false, new IdAdvertisement("Adv04")));
        campaignStandard.chargeClick(new Click(new IdUser("User04"),false, new IdAdvertisement("Adv05")));
        campaignStandard.chargeClick(new Click(new IdUser("User05"),true, new IdAdvertisement("Adv05")));
        assertEquals(campaignExpected, campaignStandard);

    }
}
