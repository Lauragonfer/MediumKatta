package Budgets;

public class BudgetMemento {

    private Double budgetMemento;

    public BudgetMemento(Double budgetMemento) {
        this.budgetMemento = budgetMemento;
    }

    public Double retrieveBudgetMm (){
        return budgetMemento;
    }
}
