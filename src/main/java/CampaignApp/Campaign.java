package CampaignApp;

import campaignState.ActiveCampaignState;
import campaignState.CampaignState;
import campaignState.FinishedCampaignState;
import campaignState.PauseCampaignState;
import exceptions.CampaingIsNotActiveException;
import exceptions.FinishedCampaignException;

import java.util.Objects;

public class Campaign {

   public IdCampaign idCampaign;
   public Budget budget;
   private CampaignState state;


   public Campaign(double budget, String idCampaign)  {
      this.idCampaign = new IdCampaign(idCampaign);
      this.budget = Budget.createBudget(budget);
      this.state = new PauseCampaignState();
   }


   public void activatedCampaign() {
      if(isCampaignFinished()){
         throw new FinishedCampaignException(Message.FinishedCampaingWarning);
      }
      this.state = new ActiveCampaignState();
   }

   public void pausedCampaign() {
      if(isCampaignFinished()){
         throw new FinishedCampaignException(Message.FinishedCampaingWarning);
      }
      this.state = new PauseCampaignState();
   }

   public void finishedCampaign() {
      if(isCampaignFinished()){
         throw new FinishedCampaignException(Message.FinishedCampaingWarning);
      }
      this.state = new FinishedCampaignState();
   }

   public CampaignState actualState() {
      return state;
   }


   public void chargeClick(String idUser, Boolean isPremium) {

      if (isCampaignFinished()){
         throw new FinishedCampaignException(Message.FinishedCampaingWarning);
      }

      if (!isCampaignActive()){
         throw new CampaingIsNotActiveException(Message.CampaingIsNotActive);
      }

      Click click = new Click(new IdUser(idUser),isPremium);

      budget.chargeClick(click);

      if(budget.isBudgetZero()){
         finishedCampaign();
      }

   }

   private boolean isCampaignFinished() {
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
      Campaign campaign = (Campaign) o;
      return Objects.equals(idCampaign, campaign.idCampaign) &&
              Objects.equals(budget, campaign.budget);
   }

   @Override
   public int hashCode() {
      return Objects.hash(idCampaign, budget);
   }

}

