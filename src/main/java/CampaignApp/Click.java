package CampaignApp;

import java.util.Date;

public class Click {

    IdClick idClick;
    IdUser idUser;
    Boolean premium;
    Date date;


    public Click(IdUser idUser, Boolean premium) {
        this.idClick = new IdClick("01");
        this.idUser = idUser;
        this.premium = premium;
        this.date = new Date();
    }


    public boolean isPremium() {
        if (premium){
            return  true;
        }
        return false;
    }
}
