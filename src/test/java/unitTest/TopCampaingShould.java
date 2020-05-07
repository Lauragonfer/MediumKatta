package unitTest;

import Campaign.Campaign;
import Campaign.CampaignTop;
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

public class TopCampaingShould {

    @Test
    public void change_the_state_of_the_Top_campaing_to_Active_if_its_Pause(){

        Campaign campaingTop = new CampaignTop(0.40,"T001");
        assertThat(campaingTop.actualState(), instanceOf (PauseCampaignState.class));
        campaingTop.activatedCampaign();
        assertThat(campaingTop.actualState(), instanceOf (ActiveCampaignState.class));
    }

    @Test
    public void throw_a_exception_when_you_create_a_TopCampaign_With_ZERO_budget(){
        assertThrows(InvalidBudgetException.class, () -> new CampaignTop(0,"T000"));
    }

    @Test
    public void make_a_premium_Click_in_a_Top_Campaign(){

        Campaign campaignTop = new CampaignTop(1,"T001");
        Campaign campaignExpected = new CampaignTop(0.80, "T001");
        campaignTop.activatedCampaign();
        campaignTop.chargeClick(new Click(new IdUser("User01"),true, new IdAdvertisement("Adv01")) );
        assertEquals(campaignExpected, campaignTop);

    }

    @Test
    public void make_a_normal_Click_in_a_Top_campaign(){

        Campaign campaignTop = new CampaignTop(0.99,"T001");
        Campaign campaignExpected = new CampaignTop(0.89, "T001");
        campaignTop.activatedCampaign();
        campaignTop.chargeClick(new Click(new IdUser("User01"),false, new IdAdvertisement("Adv01")));
        assertEquals(campaignExpected, campaignTop);
    }

    @Test
    public void throw_a_exception_when_charge_a_click_in_a_NOT_Active_Top_campaign(){
        Campaign campaignTop = new CampaignTop(1,"0001");
        assertThrows(CampaingIsNotActiveException.class, () -> campaignTop.chargeClick(
                new Click(new IdUser("User01"),true, new IdAdvertisement("Adv01"))));
    }

    @Test
    public void change_the_state_of_the_Top_campaing_to_Finished_when_the_budget_is_0(){

        Campaign campaignTop = new CampaignTop(0.10,"T001");
        campaignTop.activatedCampaign();
        campaignTop.chargeClick(new Click(new IdUser("User01"),false, new IdAdvertisement("Adv01")));
        assertThat(campaignTop.actualState(), instanceOf (FinishedCampaignState.class));

    }

    @Test
    public void throw_a_exception_when_charge_a_click_in_a_Finished_Top_campaign(){
        Campaign campaignTop = new CampaignTop(1,"T001");
        campaignTop.finishedCampaign();
        assertThrows(FinishedCampaignException.class, () -> campaignTop.chargeClick(
                new Click(new IdUser("User01"),true, new IdAdvertisement("Adv01"))));
    }

    @Test
    public void throw_a_exception_when_try_to_change_the_state_of_a_Finished_Top_campaign(){
        Campaign campaignTop = new CampaignTop(1,"T001");
        campaignTop.activatedCampaign();
        campaignTop.finishedCampaign();
        assertThrows(FinishedCampaignException.class, () -> campaignTop.activatedCampaign());

    }

    @Test
    public void throw_a_Exception_if_the_balance_for_PremiumCharge_is_not_enought_inTop_campaign(){
        Campaign campaignTop = new CampaignTop(0.03,"T001");
        campaignTop.activatedCampaign();
        assertThrows(NotEnoughtBalanceToPremiumClickException.class, () -> campaignTop.chargeClick(
                new Click(new IdUser("User01"),true, new IdAdvertisement("Adv01"))));
    }

    @Test
    public void check_if_the_click_is_duplicated_in_Top_Campaign(){
        Campaign campaignTop = new CampaignTop(0.80,"T001");
        campaignTop.activatedCampaign();
        campaignTop.chargeClick(new Click(new IdUser("User01"),true, new IdAdvertisement("Adv01")));
        assertThrows(DuplicateClickException.class, () -> campaignTop.chargeClick(
                new Click(new IdUser("User01"),true, new IdAdvertisement("Adv01"))));

    }

    @Test
    public void check_when_the_same_user_click_the_same_advert_but_more_than_Fifteen_seconds_between(){
        Campaign campaignTop = new CampaignTop(0.60,"T001");
        Campaign campaignExpected = new CampaignTop(0.20, "T001");
        campaignTop.activatedCampaign();
        campaignTop.chargeClick(new Click(new IdUser("User01"),true, new IdAdvertisement("Adv01")));
        try {
            Thread.sleep(16 * 1000);
        } catch (Exception e) {
            System.out.println(e);
        }
        campaignTop.chargeClick(new Click(new IdUser("User01"),true, new IdAdvertisement("Adv01")));
        assertEquals(campaignExpected, campaignTop);
    }



}
