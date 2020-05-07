package unitTest;

import CampaignApp.Campaign;
import CampaignApp.Click;
import CampaignApp.IdAdvertisement;
import CampaignApp.IdUser;
import campaignState.ActiveCampaignState;
import campaignState.FinishedCampaignState;
import campaignState.PauseCampaignState;

import exceptions.CampaingIsNotActiveException;
import exceptions.FinishedCampaignException;
import exceptions.InvalidBudgetException;
import exceptions.NotEnoughtBalanceToPremiumClickException;
import org.junit.Test;


import static org.hamcrest.core.IsInstanceOf.instanceOf;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class ClickerChargerShould {

    @Test
    public void change_the_state_of_the_campaing_to_Active_if_its_Pause(){

        Campaign campaign = new Campaign(2, "0001");
        assertThat(campaign.actualState(), instanceOf (PauseCampaignState.class));
        campaign.activatedCampaign();
        assertThat(campaign.actualState(), instanceOf (ActiveCampaignState.class));
    }

    @Test
    public void change_the_state_of_the_campaing_to_Pause_if_its_Active(){

        Campaign campaign = new Campaign(2,"0001");
        campaign.activatedCampaign();
        assertThat(campaign.actualState(), instanceOf (ActiveCampaignState.class));
        campaign.pausedCampaign();
        assertThat(campaign.actualState(), instanceOf (PauseCampaignState.class));
    }

    @Test
    public void throw_a_exception_when_you_create_a_Campaign_With_ZERO_budget(){
        assertThrows(InvalidBudgetException.class, () -> new Campaign(0,"0000"));
    }

    @Test
    public void make_a_premium_Click_in_a_campaign(){

        Campaign campaign = new Campaign(1,"0001");
        Campaign campaignExpected = new Campaign(0.95, "0001");
        campaign.activatedCampaign();
        campaign.chargeClick(new Click(new IdUser("User01"),true, new IdAdvertisement("Adv01")) );
        assertEquals(campaignExpected,campaign);

    }

    @Test
    public void make_a_normal_Click_in_a_campaign(){

        Campaign campaign = new Campaign(0.99,"0001");
        Campaign campaignExpected = new Campaign(0.98, "0001");
        campaign.activatedCampaign();
        campaign.chargeClick(new Click(new IdUser("User01"),false, new IdAdvertisement("Adv01")));
        assertEquals(campaignExpected,campaign);
    }

    @Test
    public void throw_a_exception_when_charge_a_click_in_a_NOT_Active_campaign(){
        Campaign campaign = new Campaign(1,"0001");
        assertThrows(CampaingIsNotActiveException.class, () -> campaign.chargeClick(
                new Click(new IdUser("User01"),true, new IdAdvertisement("Adv01"))));
    }

    @Test
    public void change_the_state_of_the_campaing_to_Finished_when_the_budget_is_0(){

        Campaign campaign = new Campaign(0.05,"0001");
        campaign.activatedCampaign();
        campaign.chargeClick(new Click(new IdUser("User01"),true, new IdAdvertisement("Adv01")));
        assertThat(campaign.actualState(), instanceOf (FinishedCampaignState.class));

    }

    @Test
    public void throw_a_exception_when_charge_a_click_in_a_Finished_campaign(){
        Campaign campaign = new Campaign(1,"0001");
        campaign.finishedCampaign();
        assertThrows(FinishedCampaignException.class, () -> campaign.chargeClick(
                new Click(new IdUser("User01"),true, new IdAdvertisement("Adv01"))));
    }

    @Test
    public void throw_a_exception_when_try_to_change_the_state_of_a_Finished_campaign(){
        Campaign campaign = new Campaign(1,"0001");
        campaign.activatedCampaign();
        campaign.finishedCampaign();
        assertThrows(FinishedCampaignException.class, () -> campaign.activatedCampaign());

    }

    @Test
    public void show_a_Exception_if_the_balance_for_PremiumCharge_is_not_enought(){
        Campaign campaign = new Campaign(0.03,"0001");
        campaign.activatedCampaign();
        assertThrows(NotEnoughtBalanceToPremiumClickException.class, () -> campaign.chargeClick(
                new Click(new IdUser("User01"),true, new IdAdvertisement("Adv01"))));
    }
}
