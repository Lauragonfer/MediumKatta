package ClickAdvertisement;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ClickRegisterList {

    private List <Click> clicksRegisterList;

    public ClickRegisterList() {
        this.clicksRegisterList = new ArrayList<>();
    }

    public void addClickToRegister(Click click){
        clicksRegisterList.add(click);
    }

    public List<Click> retrieveBotsClickToRepay(Date dateStartBotClicks, List<IdUser> idUserBotsList) {

        List<Click> repayList = new ArrayList<>();
        for (IdUser idUser:idUserBotsList) {
            repayList.addAll(clickMakeByUserSinceDateToToday(idUser,dateStartBotClicks));
        }
        return repayList;
    }

    private List<Click> clickMakeByUserSinceDateToToday(IdUser idUser, Date dateStartBotClicks) {
        List <Click> clickDateList = new ArrayList<>();

        for ( Click click : retrieveClicksByThisUser(idUser)) {
            if(click.isBetweenDateAndToday(dateStartBotClicks)){
                clickDateList.add(click);
            }
        }
        return clickDateList;
    }

    public List<Click> retrieveClicksSinceThisDate(Date dateStartBotClicks) {

        List <Click> clickDateList = new ArrayList<>();

        for ( Click click:clicksRegisterList) {
            if(click.isBetweenDateAndToday(dateStartBotClicks)){
                click.toString();
                clickDateList.add(click);
            }
        }
        return clickDateList;
    }

    public List<Click> retrieveClicksByThisUser(IdUser idUser) {
        List<Click> clickIdUserList = new ArrayList<>();

        for (Click click : clicksRegisterList) {
            if (click.isMadeByUser(idUser)) {
                clickIdUserList.add(click);
            }
        }
        return clickIdUserList;
    }

}
