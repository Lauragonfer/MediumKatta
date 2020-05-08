package ClickAdvertisement;

import exceptions.DuplicateClickException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class Click {

    private IdClick idClick;
    private IdUser idUser;
    private Boolean premium;
    private IdAdvertisement idAdvertisement;
    private Date date;


    public Click(IdUser idUser, Boolean premium, IdAdvertisement idAdvertisement) {
        this.idClick = new IdClick("01");
        this.idUser = idUser;
        this.idAdvertisement = idAdvertisement;
        this.premium = premium;
        this.date = new Date();
    }

    public void checkIfIsDuplicateClick(Click lastClick) {
        if (this.equals(lastClick) && !areFifteenSecondsPassBetweenClicks(lastClick)){
            throw new DuplicateClickException(Message.DuplicateClick);
        }
    }

    public boolean areFifteenSecondsPassBetweenClicks(Click lastClick){
        Date lastDate = lastClick.date;
        lastDate.setTime(lastDate.getTime() + 16000);
        if (date.before(lastDate)){
            return false;
        }
        return true;
    }

    public boolean isPremium() {
        return premium ? true: false ;
    }

    public boolean isMadeByUser(IdUser idUserMade) {
        if (idUser.equals(idUserMade)){
            return true;
        }
        return false;
    }

    public boolean isBetweenDateAndToday(Date dateStartBotClicks) {
        if (date.before(dateStartBotClicks) || isTheSameDateAsToday(date) ){
            return true;
        }
        return false;
    }

    private String formatStringDateToCompare(Date date) {
        SimpleDateFormat simpleFormatYMD = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = simpleFormatYMD.format(date);
        return dateString;
    }

    private boolean isTheSameDateAsToday(Date dateCheck) {

        if(formatStringDateToCompare(dateCheck).equals(formatStringDateToCompare(new Date()))){
            return true;
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
