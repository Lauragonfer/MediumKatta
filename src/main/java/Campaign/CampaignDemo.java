package Campaign;

import Budgets.BudgetDemo;
import Budgets.BudgetStandard;
import CampaignApp.*;
import campaignState.ActiveCampaignState;
import campaignState.CampaignState;
import campaignState.FinishedCampaignState;
import campaignState.PauseCampaignState;
import exceptions.CampaingIsNotActiveException;
import exceptions.FinishedCampaignException;

import java.util.Objects;

public class CampaignDemo implements Campaign {

   public IdCampaign idCampaign;
   public BudgetDemo budget;
   private CampaignState state;

   public CampaignDemo(double budget, String idCampaign)  {
      this.idCampaign = new IdCampaign(idCampaign);
      this.budget = new BudgetDemo(budget);
      this.state = new PauseCampaignState();
   }

   public void chargeClick(Click click) {

      checkifTheCampaingStateAllowCharges();

   }

   private void checkifTheCampaingStateAllowCharges() {
      checkIfTheCampaingIsFinished();

      if (!isCampaignActive()){
         throw new CampaingIsNotActiveException(Message.CampaingIsNotActive);
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

   public void checkIfTheCampaingIsFinished(){
      if (isCampaignFinished()){
         throw new FinishedCampaignException(Message.FinishedCampaingWarning);
      }
   }

   public CampaignState actualState() {
      return state;
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
      CampaignDemo campaignStandard = (CampaignDemo) o;
      return Objects.equals(idCampaign, campaignStandard.idCampaign) &&
              Objects.equals(budget, campaignStandard.budget);
   }

   @Override
   public int hashCode() {
      return Objects.hash(idCampaign, budget);
   }

}

