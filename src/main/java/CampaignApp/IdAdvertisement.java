package CampaignApp;

import java.util.Objects;

public class IdAdvertisement {

        String idAdvertisement;

        public IdAdvertisement(String idAdvertisement) {
            this.idAdvertisement = idAdvertisement;
        }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IdAdvertisement that = (IdAdvertisement) o;
        return Objects.equals(idAdvertisement, that.idAdvertisement);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idAdvertisement);
    }
}
