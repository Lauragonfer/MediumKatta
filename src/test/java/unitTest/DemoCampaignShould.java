package unitTest;

import Campaign.Campaign;
import Campaign.CampaignDemo;
import ClickAdvertisement.Click;
import ClickAdvertisement.IdAdvertisement;
import ClickAdvertisement.IdUser;
import campaignState.ActiveCampaignState;
import campaignState.PauseCampaignState;
import exceptions.*;
import org.junit.Test;

import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DemoCampaignShould {

    @Test
    public void change_the_state_of_the_Demo_campaing_to_Active_if_its_Pause(){

        Campaign campaingDemo = new CampaignDemo(0,"D001");
        assertThat(campaingDemo.actualState(), instanceOf (PauseCampaignState.class));
        campaingDemo.activatedCampaign();
        assertThat(campaingDemo.actualState(), instanceOf (ActiveCampaignState.class));
    }


    @Test
    public void make_a_premium_Click_in_a_Demo_Campaign(){

        Campaign campaignDemo = new CampaignDemo(0,"D001");
        Campaign campaignExpected = new CampaignDemo(0, "D001");
        campaignDemo.activatedCampaign();
        campaignDemo.chargeClick(new Click(new IdUser("User01"),true, new IdAdvertisement("Adv01")) );
        assertEquals(campaignExpected, campaignDemo);

    }

    @Test
    public void make_a_normal_Click_in_a_Demo_campaign(){

        Campaign campaignDemo = new CampaignDemo(0,"D001");
        Campaign campaignExpected = new CampaignDemo(0, "D001");
        campaignDemo.activatedCampaign();
        campaignDemo.chargeClick(new Click(new IdUser("User01"),false, new IdAdvertisement("Adv01")));
        assertEquals(campaignExpected, campaignDemo);
    }

    @Test
    public void throw_a_exception_when_charge_a_click_in_a_NOT_Active_Demo_campaign(){
        Campaign campaignDemo = new CampaignDemo(0,"D001");
        assertThrows(CampaingIsNotActiveException.class, () -> campaignDemo.chargeClick(
                new Click(new IdUser("User01"),true, new IdAdvertisement("Adv01"))));
    }


    @Test
    public void throw_a_exception_when_charge_a_click_in_a_Finished_Demo_campaign(){
        Campaign campaignDemo = new CampaignDemo(0,"D001");
        campaignDemo.finishedCampaign();
        assertThrows(FinishedCampaignException.class, () -> campaignDemo.chargeClick(
                new Click(new IdUser("User01"),true, new IdAdvertisement("Adv01"))));
    }

    @Test
    public void throw_a_exception_when_try_to_change_the_state_of_a_Finished_Demo_campaign(){
        Campaign campaignDemo = new CampaignDemo(0,"D001");
        campaignDemo.activatedCampaign();
        campaignDemo.finishedCampaign();
        assertThrows(FinishedCampaignException.class, () -> campaignDemo.activatedCampaign());

    }

}
