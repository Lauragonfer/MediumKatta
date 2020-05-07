package Campaign;

import Budgets.BudgetStandard;
import CampaignApp.*;
import campaignState.ActiveCampaignState;
import campaignState.CampaignState;
import campaignState.FinishedCampaignState;
import campaignState.PauseCampaignState;
import exceptions.CampaingIsNotActiveException;
import exceptions.FinishedCampaignException;

import java.util.Objects;

public class CampaignStandard implements Campaign {

   public IdCampaign idCampaign;
   public BudgetStandard budgetStandard;
   private CampaignState state;

   private Click lastClick;


   public CampaignStandard(double budget, String idCampaign)  {
      this.idCampaign = new IdCampaign(idCampaign);
      this.budgetStandard = BudgetStandard.createBudget(budget);
      this.state = new PauseCampaignState();
      this.lastClick = new Click(new IdUser(""),false,new IdAdvertisement("") );
   }

   public void chargeClick(Click click) {

      checkifTheCampaingStateAllowCharges();
      click.checkIfIsDuplicateClick(lastClick);
      lastClick = click;

      budgetStandard.chargeClick(click);

      if(budgetStandard.isBudgetZero()){
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
      CampaignStandard campaignStandard = (CampaignStandard) o;
      return Objects.equals(idCampaign, campaignStandard.idCampaign) &&
              Objects.equals(budgetStandard, campaignStandard.budgetStandard);
   }

   @Override
   public int hashCode() {
      return Objects.hash(idCampaign, budgetStandard);
   }

}

