package Budgets;

import CampaignApp.Click;
import java.util.Objects;

public class BudgetDemo  implements Budget{

    private double budget;

    private final Double PREMIUMCHARGE = 0.00;
    private final Double NORMALCHARGE = 0.00;

    public BudgetDemo(double budget) {
        this.budget = budget;
    }

    public void chargeClick(Click click) {
        budget = budget;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BudgetDemo that = (BudgetDemo) o;
        return Double.compare(that.budget, budget) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(budget);
    }
}
