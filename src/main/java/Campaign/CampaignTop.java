package Campaign;

import Budgets.BudgetStandard;
import Budgets.BudgetTop;
import CampaignApp.*;
import campaignState.ActiveCampaignState;
import campaignState.CampaignState;
import campaignState.FinishedCampaignState;
import campaignState.PauseCampaignState;
import exceptions.CampaingIsNotActiveException;
import exceptions.FinishedCampaignException;

import java.util.Objects;

public class CampaignTop implements Campaign {

   public IdCampaign idCampaign;
   public BudgetTop budget;
   private CampaignState state;

   private Click lastClick;


   public CampaignTop(double budget, String idCampaign)  {
      this.idCampaign = new IdCampaign(idCampaign);
      this.budget = BudgetTop.createBudget(budget);
      this.state = new PauseCampaignState();
      this.lastClick = new Click(new IdUser(""),false,new IdAdvertisement("") );
   }

   public void chargeClick(Click click) {

      checkifTheCampaingStateAllowCharges();
      click.checkIfIsDuplicateClick(lastClick);
      lastClick = click;

      budget.chargeClick(click);

      if(budget.isBudgetZero()){
         finishedCampaign();
      }

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

   public CampaignState actualState() {
      return state;
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

