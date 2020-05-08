package Budgets;

public class BudgetOriginator {

    private Double budgetOriginator;

    public BudgetOriginator(Double budgetOriginator) {
        this.budgetOriginator = budgetOriginator;
    }

    public BudgetMemento saveBudgetToMemento(){
        return new BudgetMemento(budgetOriginator);
    }

    public Double retrieveBudgetTopFromOriginator(){
        return budgetOriginator;
    }

    public void retrieveBudgetTopFromMemento(BudgetMemento memento){
        budgetOriginator = memento.retrieveBudgetMm();
    }

}
