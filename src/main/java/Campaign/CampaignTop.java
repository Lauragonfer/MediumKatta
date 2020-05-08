package Campaign;

import Budgets.BudgetTop;
import ClickAdvertisement.*;
import campaignState.ActiveCampaignState;
import campaignState.CampaignState;
import campaignState.FinishedCampaignState;
import campaignState.PauseCampaignState;
import exceptions.BotUserListIsEmptyException;
import exceptions.CampaingIsNotActiveException;
import exceptions.DateStartBotNotCorrectException;
import exceptions.FinishedCampaignException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class CampaignTop implements Campaign {

   private IdCampaign idCampaign;
   private BudgetTop budget;
   private CampaignState state;

   private Click lastClick;
   private ClickRegisterList clickRegisterList;



   public CampaignTop(double budget, String idCampaign)  {
      this.idCampaign = new IdCampaign(idCampaign);
      this.budget = BudgetTop.createBudget(budget);
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
      budget.chargeClick(click);
      clickRegisterList.addClickToRegister(click);

      if(budget.isBudgetZero()){
         finishedCampaign();
      }
   }

   public void fakeClicksRepay(Date dateStartBotClicks, List<IdUser> idUserBotsList) {
      checkIfDateToStarBotsClicksIsCorrect(dateStartBotClicks);
      checkIfTheUserBotsListIsEmpty(idUserBotsList);

      List <Click> clicksRepayList = new ArrayList<>();

      if(!areAllClickMoreThat5PercentOfTheBudget(dateStartBotClicks)){
         clicksRepayList = clickRegisterList.retrieveClicksSinceThisDate(dateStartBotClicks);
      }
      if(areAllClickMoreThat5PercentOfTheBudget(dateStartBotClicks)){
         clicksRepayList.addAll(clickRegisterList.retrieveBotsClickToRepay(dateStartBotClicks,idUserBotsList));
      }

      budget.repayThisClicks(clicksRepayList);
   }

   private boolean areAllClickMoreThat5PercentOfTheBudget(Date dateStartBotClicks) {
      List <Click> clicksSinceBotsAtack = clickRegisterList.retrieveClicksSinceThisDate(dateStartBotClicks);
      Double totalAllClicks = budget.calculateTheAmountOfClicks(clicksSinceBotsAtack);
      return budget.isMoreThanFivePercentOfBudget(totalAllClicks);
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

   private void checkifTheCampaingStateAllowCharges() {
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
      CampaignTop campaignStandard = (CampaignTop) o;
      return Objects.equals(idCampaign, campaignStandard.idCampaign) &&
              Objects.equals(budget, campaignStandard.budget);
   }

   @Override
   public int hashCode() {
      return Objects.hash(idCampaign, budget);
   }

}

