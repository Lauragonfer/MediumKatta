package CampaignApp;

import java.util.Objects;

public class IdCampaign {

    String id;

    public IdCampaign(String idCampaign) {

        this.id = idCampaign;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IdCampaign that = (IdCampaign) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
