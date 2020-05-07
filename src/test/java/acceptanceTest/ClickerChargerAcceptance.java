package acceptanceTest;

import CampaignApp.Campaign;

import org.junit.Test;

import static org.junit.Assert.assertEquals;



public class ClickerChargerAcceptance {

    @Test

    public void clickerChargerAcceptanceTest(){

        Campaign campaign = new Campaign(0.17,"0001");
        Campaign campaignExpected = new Campaign(0.03, "0001");

        campaign.activatedCampaign();

        campaign.chargeClick("user01",false);
        campaign.chargeClick("user01",false);
        campaign.chargeClick("user01",true);
        campaign.chargeClick("user01",false);
        campaign.chargeClick("user01",false);
        campaign.chargeClick("user01",true);
        assertEquals(campaignExpected,campaign);

    }
}
