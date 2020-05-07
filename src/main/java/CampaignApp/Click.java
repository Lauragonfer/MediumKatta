package CampaignApp;

import java.util.Date;

public class Click {

    IdClick idClick;
    IdUser idUser;
    Boolean premium;
    IdAdvertisement idAdvertisement;
    Date date;


    public Click(IdUser idUser, Boolean premium, IdAdvertisement idAdvertisement) {
        this.idClick = new IdClick("01");
        this.idUser = idUser;
        this.idAdvertisement = idAdvertisement;
        this.premium = premium;
        this.date = new Date();
    }


    public boolean isPremium() {
        if (premium){
            return  true;
        }
        return false;
    }

    public void isDuplicatedClick(Click lastClick) {

    }

}
