package acceptanceTest;

import Campaign.Campaign;
import Campaign.CampaignStandard;

import ClickAdvertisement.Click;
import ClickAdvertisement.IdAdvertisement;
import ClickAdvertisement.IdUser;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class StandardCampaignAcceptanceTest {

    @Test

    public void standardCampaignAcceptanceTest(){

        Campaign campaignStandard = new CampaignStandard(0.17,"0001");
        Campaign campaignExpected = new CampaignStandard(0.03, "0001");

        campaignStandard.activatedCampaign();

        campaignStandard.chargeClick(new Click(new IdUser("User05"),false, new IdAdvertisement("Adv01")));
        campaignStandard.chargeClick(new Click(new IdUser("User02"),false, new IdAdvertisement("Adv02")));
        campaignStandard.chargeClick(new Click(new IdUser("User03"),true, new IdAdvertisement("Adv03")));
        campaignStandard.chargeClick(new Click(new IdUser("User04"),false, new IdAdvertisement("Adv04")));
        campaignStandard.chargeClick(new Click(new IdUser("User04"),false, new IdAdvertisement("Adv05")));
        campaignStandard.chargeClick(new Click(new IdUser("User05"),true, new IdAdvertisement("Adv05")));

        Date dateStartBotClicks = new Date();
        List<IdUser> idUserBotsList = new ArrayList<>();
        idUserBotsList.add(new IdUser("User01"));

        campaignStandard.chargeClick(new Click(new IdUser("User01"),false, new IdAdvertisement("Adv06")));
        campaignStandard.chargeClick(new Click(new IdUser("User01"),false, new IdAdvertisement("Adv07")));
        campaignStandard.chargeClick(new Click(new IdUser("User01"),false, new IdAdvertisement("Adv08")));

        campaignStandard.fakeClicksRepay(dateStartBotClicks,idUserBotsList);

        assertEquals(campaignExpected, campaignStandard);

    }
}
