package acceptanceTest;

import Campaign.Campaign;
import Campaign.CampaignDemo;

import ClickAdvertisement.Click;
import ClickAdvertisement.IdAdvertisement;
import ClickAdvertisement.IdUser;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DemoCampaingAcceptanceTest {

    @Test

    public void DemoCampaingAcceptanceTest(){

        Campaign campaignDemo = new CampaignDemo(0,"D001");
        Campaign campaignExpected = new CampaignDemo(0, "D001");

        campaignDemo.activatedCampaign();

        campaignDemo.chargeClick(new Click(new IdUser("User01"),false, new IdAdvertisement("Adv01")));
        campaignDemo.chargeClick(new Click(new IdUser("User02"),false, new IdAdvertisement("Adv02")));
        campaignDemo.chargeClick(new Click(new IdUser("User03"),true, new IdAdvertisement("Adv03")));
        campaignDemo.chargeClick(new Click(new IdUser("User04"),false, new IdAdvertisement("Adv04")));
        campaignDemo.chargeClick(new Click(new IdUser("User04"),false, new IdAdvertisement("Adv05")));
        campaignDemo.chargeClick(new Click(new IdUser("User05"),true, new IdAdvertisement("Adv05")));
        assertEquals(campaignExpected, campaignDemo);

    }
}
