package Budgets;

import java.util.ArrayList;
import java.util.List;

public class BudgetCareTaker {

    private List<BudgetMemento> mementoList = new ArrayList<>();

    public void add(BudgetMemento budgetMemento){
        mementoList.add(budgetMemento);
    }

    public BudgetMemento retrieveBudgetMemento (int index){
        return mementoList.get(index);
    }
}
