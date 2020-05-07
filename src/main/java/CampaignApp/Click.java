package CampaignApp;

import exceptions.DuplicateClickException;

import java.util.Date;
import java.util.Objects;

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

    public boolean areFifteenSecondsPassBetweenClicks(Click lastClick){
        Date lastDate = lastClick.date;
        lastDate.setTime(lastDate.getTime() + 16000);
        if (date.before(lastDate)){
            return false;
        }
        return true;
    }

    public void checkIfIsDuplicateClick(Click lastClick) {
        if (this.equals(lastClick) && !areFifteenSecondsPassBetweenClicks(lastClick)){
            throw new DuplicateClickException(Message.DuplicateClick);
        }
    }

    public boolean isPremium() {
        if (premium){
            return  true;
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Click click = (Click) o;
        return Objects.equals(idUser, click.idUser) &&
                Objects.equals(idAdvertisement, click.idAdvertisement) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUser, idAdvertisement, date);
    }

}
