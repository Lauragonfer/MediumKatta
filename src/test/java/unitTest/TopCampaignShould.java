package unitTest;

import Campaign.Campaign;
import Campaign.CampaignTop;
import ClickAdvertisement.Click;
import ClickAdvertisement.IdAdvertisement;
import ClickAdvertisement.IdUser;
import campaignState.ActiveCampaignState;
import campaignState.FinishedCampaignState;
import campaignState.PauseCampaignState;
import exceptions.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TopCampaignShould {

    @Test
    public void change_the_state_of_the_Top_campaing_to_Active_if_its_Pause(){

        Campaign campaingTop = new CampaignTop(0.40,"T001");
        assertThat(campaingTop.actualState(), instanceOf (PauseCampaignState.class));
        campaingTop.activatedCampaign();
        assertThat(campaingTop.actualState(), instanceOf (ActiveCampaignState.class));
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
    public void change_the_state_of_the_Top_campaing_to_Finished_when_the_budget_is_0(){

        Campaign campaignTop = new CampaignTop(0.10,"T001");
        campaignTop.activatedCampaign();
        campaignTop.chargeClick(new Click(new IdUser("User01"),false, new IdAdvertisement("Adv01")));
        assertThat(campaignTop.actualState(), instanceOf (FinishedCampaignState.class));

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
    public void check_when_the_same_user_click_the_same_advert_but_more_than_Fifteen_seconds_between_in_Top_Campaign(){
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

    @Test
    public void return_the_charges_Make_byBots_in_Top_Campaing_When_Date_Is_Today_and_All_Clicks_are_MORE_than_Five_percent_of_inicial_Budget(){

        Campaign campaignTop = new CampaignTop(10,"T001");
        Campaign campaignExpected = new CampaignTop(9.40,"T001");

        campaignTop.activatedCampaign();

        Date dateStartBotClicks = new Date();
        List<IdUser> idUserBotsList = new ArrayList<>();
        idUserBotsList.add(new IdUser("User01"));

        campaignTop.chargeClick(new Click(new IdUser("User01"),true, new IdAdvertisement("Adv01")));
        campaignTop.chargeClick(new Click(new IdUser("User01"),true, new IdAdvertisement("Adv02")));
        campaignTop.chargeClick(new Click(new IdUser("User03"),true, new IdAdvertisement("Adv01")));
        campaignTop.chargeClick(new Click(new IdUser("User04"),true, new IdAdvertisement("Adv01")));
        campaignTop.chargeClick(new Click(new IdUser("User05"),true, new IdAdvertisement("Adv01")));

        campaignTop.fakeClicksRepay(dateStartBotClicks,idUserBotsList);

        assertEquals(campaignExpected, campaignTop);
    }

    @Test
    public void return_the_charges_Make_byBots_in_Top_Campaing_When_Date_Is_Today_and_All_Clicks_are_LESS_than_Five_percent_of_inicial_Budget(){

        Campaign campaignTop = new CampaignTop(100,"T001");
        Campaign campaignExpected = new CampaignTop(100,"T001");

        campaignTop.activatedCampaign();

        Date dateStartBotClicks = new Date();
        List<IdUser> idUserBotsList = new ArrayList<>();
        idUserBotsList.add(new IdUser("User01"));

        campaignTop.chargeClick(new Click(new IdUser("User01"),false, new IdAdvertisement("Adv01")));
        campaignTop.chargeClick(new Click(new IdUser("User02"),false, new IdAdvertisement("Adv01")));
        campaignTop.chargeClick(new Click(new IdUser("User03"),false, new IdAdvertisement("Adv01")));

        campaignTop.fakeClicksRepay(dateStartBotClicks,idUserBotsList);

        assertEquals(campaignExpected, campaignTop);
    }

    @Test
    public void return_The_charges_Make_byBots_in_Top_Campaing_When_Date_Is_in_the_past_Month_and_All_Clicks_are_MORE_than_Five_percent_of_inicial_Budge(){
        Campaign campaignTop = new CampaignTop(0.50,"T001");
        Campaign campaignExpected = new CampaignTop(0.30,"T001");

        campaignTop.activatedCampaign();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MONTH, -1);
        Date dateStartBotClicks = calendar.getTime() ;

        List<IdUser> idUserBotsList = new ArrayList<>();
        idUserBotsList.add(new IdUser("User01"));

        campaignTop.chargeClick(new Click(new IdUser("User01"),true, new IdAdvertisement("Adv01")));
        campaignTop.chargeClick(new Click(new IdUser("User02"),true, new IdAdvertisement("Adv01")));

        campaignTop.fakeClicksRepay(dateStartBotClicks,idUserBotsList);

        assertEquals(campaignExpected, campaignTop);
    }

    @Test
    public void throw_a_exception_when_you_create_a_TopCampaign_With_ZERO_budget(){
        assertThrows(InvalidBudgetException.class, () -> new CampaignTop(0,"T000"));
    }

    @Test
    public void throw_a_exception_when_charge_a_click_in_a_NOT_Active_Top_campaign(){
        Campaign campaignTop = new CampaignTop(1,"0001");
        assertThrows(CampaingIsNotActiveException.class, () -> campaignTop.chargeClick(
                new Click(new IdUser("User01"),true, new IdAdvertisement("Adv01"))));
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



}
