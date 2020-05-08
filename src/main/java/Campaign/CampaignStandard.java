package Campaign;

import Budgets.BudgetStandard;
import ClickAdvertisement.*;
import campaignState.ActiveCampaignState;
import campaignState.CampaignState;
import campaignState.FinishedCampaignState;
import campaignState.PauseCampaignState;
import exceptions.BotUserListIsEmptyException;
import exceptions.CampaingIsNotActiveException;
import exceptions.DateStartBotNotCorrectException;
import exceptions.FinishedCampaignException;

import java.util.Date;
import java.util.List;
import java.util.Objects;

public class CampaignStandard implements Campaign {

   private IdCampaign idCampaign;
   private BudgetStandard budgetStandard;
   private CampaignState state;

   private Click lastClick;
   private ClickRegisterList clickRegisterList;

   public CampaignStandard(double budget, String idCampaign)  {
      this.idCampaign = new IdCampaign(idCampaign);
      this.budgetStandard = BudgetStandard.createBudget(budget);
      this.state = new PauseCampaignState();
      this.lastClick = new Click(new IdUser(""),false,new IdAdvertisement("") );
      this.clickRegisterList = new ClickRegisterList();
   }

   public void activatedCampaign() {
      checkIfTheCampaingIsFinished();
      state = state.activeState();
   }

   public void pausedCampaign() {
      checkIfTheCampaingIsFinished();
      state = state.pauseState();
   }

   public void finishedCampaign() {
      state = state.finishState();
   }

   public void chargeClick(Click click) {

      checkifTheCampaingStateAllowCharges();
      click.checkIfIsDuplicateClick(lastClick);

      lastClick = click;
      budgetStandard.chargeClick(click);
      clickRegisterList.addClickToRegister(click);

      if(budgetStandard.isBudgetZero()){
         finishedCampaign();
      }
   }

   public void fakeClicksRepay(Date dateStartBotClicks, List<IdUser> idUserBotsList) {
      checkIfDateToStarBotsClicksIsCorrect(dateStartBotClicks);
      checkIfTheUserBotsListIsEmpty(idUserBotsList);

      List <Click> clicksRepayList = clickRegisterList.retrieveBotsClickToRepay(dateStartBotClicks,idUserBotsList);
      budgetStandard.repayThisClicks(clicksRepayList);
   }

   private void checkIfTheUserBotsListIsEmpty(List<IdUser> idUserBotsList) {
      if (idUserBotsList.isEmpty())
      {
         throw new BotUserListIsEmptyException(Message.botUserListIsEmpty);
      }
   }

   private void checkIfDateToStarBotsClicksIsCorrect(Date dateStartBotClicks) {
      if (dateStartBotClicks.after(new Date()))
      {
         throw new DateStartBotNotCorrectException(Message.dateStartBotNotCorrect);
      }
   }

   public void checkifTheCampaingStateAllowCharges() {
      checkIfTheCampaingIsFinished();
      if (!isCampaignActive()){
         throw new CampaingIsNotActiveException(Message.CampaingIsNotActive);
      }
   }

   public void checkIfTheCampaingIsFinished(){
      if (isCampaignFinished()){
         throw new FinishedCampaignException(Message.FinishedCampaingWarning);
      }
   }

   public boolean isCampaignFinished() {
      if (state instanceof FinishedCampaignState){
         return true;
      }
      return false;
   }

   public boolean isCampaignActive(){
     if (state instanceof ActiveCampaignState){
         return true;
      }
      return false;
   }

   public CampaignState actualState() {
      return state;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      CampaignStandard campaignStandard = (CampaignStandard) o;
      return Objects.equals(idCampaign, campaignStandard.idCampaign) &&
              Objects.equals(budgetStandard, campaignStandard.budgetStandard);
   }

   @Override
   public int hashCode() {
      return Objects.hash(idCampaign, budgetStandard);
   }

}

