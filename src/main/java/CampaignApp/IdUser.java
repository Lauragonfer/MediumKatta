package CampaignApp;

import java.util.Objects;

public class IdUser {

    String idUser;

    public IdUser(String idUser) {
        this.idUser = idUser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IdUser idUser1 = (IdUser) o;
        return Objects.equals(idUser, idUser1.idUser);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUser);
    }
}
