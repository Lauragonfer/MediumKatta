package CampaignApp;

import exceptions.InvalidBudgetException;
import exceptions.NotEnoughtBalanceToNormalClickException;
import exceptions.NotEnoughtBalanceToPremiumClickException;

import java.util.Objects;

public class Budget {

    private double budget;

    private final Double PREMIUMCHARGE = 0.05;
    private final Double NORMALCHARGE = 0.01;

    private Budget(double budget) {
        this.budget = budget;
    }

    public static Budget createBudget(double budget)  {

            if(budget <= 0){
                throw new InvalidBudgetException(Message.InvalidBudget);
            }

        return new Budget(budget);
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

    }

    private void chargePremiumClick(Click click) {
        if (!haveEnoughBalanceToPremium()){
            throw new NotEnoughtBalanceToPremiumClickException(Message.NotEnoughtBalanceToPremiumClick);
        }
        budget = budget - PREMIUMCHARGE;
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
        Budget budget1 = (Budget) o;
        return Double.compare(budget1.budget, budget) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(budget);
    }

}
