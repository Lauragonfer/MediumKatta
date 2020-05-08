package Budgets;

import ClickAdvertisement.Click;

import java.util.List;

public interface Budget {

    void chargeClick(Click click);

    void repayThisClicks(List<Click> clicksRepayList);

}
