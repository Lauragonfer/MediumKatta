package Budgets;

import ClickAdvertisement.Click;
import ClickAdvertisement.Message;
import exceptions.InvalidBudgetException;
import exceptions.NotEnoughtBalanceToNormalClickException;
import exceptions.NotEnoughtBalanceToPremiumClickException;

import java.util.List;
import java.util.Objects;

public class BudgetTop implements Budget{

    private double budget;

    private final Double PREMIUMCHARGE = 0.20;
    private final Double NORMALCHARGE = 0.10;

    private BudgetOriginator budgetOriginator;
    private BudgetCareTaker budgetCareTaker;

    private BudgetTop(double budget) {
        this.budget = budget;

        this.budgetOriginator = new BudgetOriginator(this.budget);
        this.budgetCareTaker = new BudgetCareTaker();
        saveBudgetMemento();
    }

    private void saveBudgetMemento() {
        budgetCareTaker.add(budgetOriginator.saveBudgetToMemento());
    }

    public static BudgetTop createBudget(double budget)  {
            if(budget <= 0 ){
                throw new InvalidBudgetException(Message.InvalidBudget);
            }

        return new BudgetTop(budget);
    }

    public void chargeClick(Click click) {
        if (click.isPremium()){
            chargePremiumClick(click);
        }
        if(!click.isPremium()){
            chargeNormalClick(click);
        }
        saveBudgetMemento();
    }

    @Override
    public void repayThisClicks(List<Click> clicksRepayList) {

        if (!clicksRepayList.isEmpty()){
            budget += calculateTheAmountOfClicks(clicksRepayList);
            budget = roundDouble(budget);
        }
        saveBudgetMemento();
    }

    private double roundDouble(double doubleNumber) {
        return  Math.round( doubleNumber * 100.0 ) / 100.0;
    }

    public Double calculateTheAmountOfClicks(List<Click> clicksRepayList) {
        Double amountRepay= 0.0;
        for (Click click :clicksRepayList) {
            amountRepay += retrieveAmountClick(click);
        }
        return amountRepay;
    }

    private Double retrieveAmountClick(Click click) {
        if (click.isPremium()){
            return  PREMIUMCHARGE;
        }
        return NORMALCHARGE;
    }

    private void chargeNormalClick(Click click) {
        if (!haveEnoughBalanceToNormal()){
            throw new NotEnoughtBalanceToNormalClickException(Message.NotEnoughtBalanceToNormalClick);
        }
        budget -= NORMALCHARGE;
        budget = roundDouble(budget);
    }

    private void chargePremiumClick(Click click) {
        if (!haveEnoughBalanceToPremium()){
            throw new NotEnoughtBalanceToPremiumClickException(Message.NotEnoughtBalanceToPremiumClick);
        }

        budget -= PREMIUMCHARGE;
        budget = roundDouble(budget);
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

    public boolean isMoreThanFivePercentOfBudget(Double totalAllClicks) {

        budgetOriginator.retrieveBudgetTopFromMemento(budgetCareTaker.retrieveBudgetMemento(0));

        Double porcentaje = (totalAllClicks * 100 )/ budgetOriginator.retrieveBudgetTopFromOriginator();

        if(porcentaje > 5){
            return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BudgetTop budgetTop = (BudgetTop) o;
        return Double.compare(budgetTop.budget, budget) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(budget);
    }

}
