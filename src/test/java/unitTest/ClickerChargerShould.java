package unitTest;

import Campaign.Campaign;
import Campaign.CampaignStandard;
import CampaignApp.Click;
import CampaignApp.IdAdvertisement;
import CampaignApp.IdUser;
import campaignState.ActiveCampaignState;
import campaignState.FinishedCampaignState;
import campaignState.PauseCampaignState;

import exceptions.*;
import org.junit.Test;


import static org.hamcrest.core.IsInstanceOf.instanceOf;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class ClickerChargerShould {

    @Test
    public void change_the_state_of_the_campaing_to_Active_if_its_Pause(){

        Campaign campaignStandard = new CampaignStandard(2, "0001");
        assertThat(campaignStandard.actualState(), instanceOf (PauseCampaignState.class));
        campaignStandard.activatedCampaign();
        assertThat(campaignStandard.actualState(), instanceOf (ActiveCampaignState.class));
    }

    @Test
    public void change_the_state_of_the_campaing_to_Pause_if_its_Active(){

        Campaign campaignStandard = new CampaignStandard(2,"0001");
        campaignStandard.activatedCampaign();
        assertThat(campaignStandard.actualState(), instanceOf (ActiveCampaignState.class));
        campaignStandard.pausedCampaign();
        assertThat(campaignStandard.actualState(), instanceOf (PauseCampaignState.class));
    }

    @Test
    public void throw_a_exception_when_you_create_a_Campaign_With_ZERO_budget(){
        assertThrows(InvalidBudgetException.class, () -> new CampaignStandard(0,"0000"));
    }

    @Test
    public void make_a_premium_Click_in_a_campaign(){

        Campaign campaignStandard = new CampaignStandard(1,"0001");
        Campaign campaignExpected = new CampaignStandard(0.95, "0001");
        campaignStandard.activatedCampaign();
        campaignStandard.chargeClick(new Click(new IdUser("User01"),true, new IdAdvertisement("Adv01")) );
        assertEquals(campaignExpected, campaignStandard);

    }

    @Test
    public void make_a_normal_Click_in_a_campaign(){

        Campaign campaignStandard = new CampaignStandard(0.99,"0001");
        Campaign campaignExpected = new CampaignStandard(0.98, "0001");
        campaignStandard.activatedCampaign();
        campaignStandard.chargeClick(new Click(new IdUser("User01"),false, new IdAdvertisement("Adv01")));
        assertEquals(campaignExpected, campaignStandard);
    }

    @Test
    public void throw_a_exception_when_charge_a_click_in_a_NOT_Active_campaign(){
        Campaign campaignStandard = new CampaignStandard(1,"0001");
        assertThrows(CampaingIsNotActiveException.class, () -> campaignStandard.chargeClick(
                new Click(new IdUser("User01"),true, new IdAdvertisement("Adv01"))));
    }

    @Test
    public void change_the_state_of_the_campaing_to_Finished_when_the_budget_is_0(){

        Campaign campaignStandard = new CampaignStandard(0.05,"0001");
        campaignStandard.activatedCampaign();
        campaignStandard.chargeClick(new Click(new IdUser("User01"),true, new IdAdvertisement("Adv01")));
        assertThat(campaignStandard.actualState(), instanceOf (FinishedCampaignState.class));

    }

    @Test
    public void throw_a_exception_when_charge_a_click_in_a_Finished_campaign(){
        Campaign campaignStandard = new CampaignStandard(1,"0001");
        campaignStandard.finishedCampaign();
        assertThrows(FinishedCampaignException.class, () -> campaignStandard.chargeClick(
                new Click(new IdUser("User01"),true, new IdAdvertisement("Adv01"))));
    }

    @Test
    public void throw_a_exception_when_try_to_change_the_state_of_a_Finished_campaign(){
        Campaign campaignStandard = new CampaignStandard(1,"0001");
        campaignStandard.activatedCampaign();
        campaignStandard.finishedCampaign();
        assertThrows(FinishedCampaignException.class, () -> campaignStandard.activatedCampaign());

    }

    @Test
    public void throw_a_Exception_if_the_balance_for_PremiumCharge_is_not_enought(){
        Campaign campaignStandard = new CampaignStandard(0.03,"0001");
        campaignStandard.activatedCampaign();
        assertThrows(NotEnoughtBalanceToPremiumClickException.class, () -> campaignStandard.chargeClick(
                new Click(new IdUser("User01"),true, new IdAdvertisement("Adv01"))));
    }

    @Test
    public void check_if_the_click_is_duplicated(){
        CampaignStandard campaignStandard = new CampaignStandard(0.10,"0001");
        campaignStandard.activatedCampaign();
        campaignStandard.chargeClick(new Click(new IdUser("User01"),true, new IdAdvertisement("Adv01")));
        assertThrows(DuplicateClickException.class, () -> campaignStandard.chargeClick(
                new Click(new IdUser("User01"),true, new IdAdvertisement("Adv01"))));

    }

   @Test
    public void check_when_the_same_user_click_the_same_advert_but_more_than_Fifteen_seconds_between(){
        CampaignStandard campaignStandard = new CampaignStandard(0.30,"0001");
        CampaignStandard campaignExpected = new CampaignStandard(0.20, "0001");
        campaignStandard.activatedCampaign();
        campaignStandard.chargeClick(new Click(new IdUser("User01"),true, new IdAdvertisement("Adv01")));
        try {
            Thread.sleep(16 * 1000);
        } catch (Exception e) {
            System.out.println(e);
        }
        campaignStandard.chargeClick(new Click(new IdUser("User01"),true, new IdAdvertisement("Adv01")));
        assertEquals(campaignExpected, campaignStandard);

    }

}
