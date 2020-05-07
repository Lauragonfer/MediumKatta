package Budgets;

import CampaignApp.Click;
import CampaignApp.Message;
import exceptions.InvalidBudgetException;
import exceptions.NotEnoughtBalanceToNormalClickException;
import exceptions.NotEnoughtBalanceToPremiumClickException;

import java.util.Objects;

public class BudgetStandard implements Budget{

    private double budget;

    private final Double PREMIUMCHARGE = 0.05;
    private final Double NORMALCHARGE = 0.01;

    private BudgetStandard(double budget) {
        this.budget = budget;
    }

    public static BudgetStandard createBudget(double budget)  {

            if(budget <= 0 ){
                throw new InvalidBudgetException(Message.InvalidBudget);
            }

        return new BudgetStandard(budget);
    }

    public void chargeClick(Click click) {

        if (click.isPremium()){
            chargePremiumClick(click);
        }
        if(!click.isPremium()){
            chargeNormalClick(click);
        }
    }

    private void chargeNormalClick(Click click) {
        if (!haveEnoughBalanceToNormal()){
            throw new NotEnoughtBalanceToNormalClickException(Message.NotEnoughtBalanceToNormalClick);
        }
        budget -= NORMALCHARGE;
        budget = Math.round( budget * 100.0 ) / 100.0;

    }

    private void chargePremiumClick(Click click) {
        if (!haveEnoughBalanceToPremium()){
            throw new NotEnoughtBalanceToPremiumClickException(Message.NotEnoughtBalanceToPremiumClick);
        }
        budget = budget - PREMIUMCHARGE;
        budget = Math.round( budget * 100.0 ) / 100.0;
    }

    public boolean isBudgetZero() {
        return budget == 0 ? true :false;
    }

    public boolean haveEnoughBalanceToPremium(){
        if (budget >= PREMIUMCHARGE){
            return true;
        }
        return false;
    }

    public boolean haveEnoughBalanceToNormal(){
        if (budget >= NORMALCHARGE){
            return true;
        }
        return false;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BudgetStandard that = (BudgetStandard) o;
        return Double.compare(that.budget, budget) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(budget);
    }
}
