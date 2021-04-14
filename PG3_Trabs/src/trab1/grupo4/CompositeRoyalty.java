package trab1.grupo4;

import trab1.grupo3.Artwork;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class CompositeRoyalty extends Royalty{

    private List<Royalty> royalties = new ArrayList<>();

    public CompositeRoyalty(Artwork aw) {
        super(aw);
    }

    public int getValue() {
        int sum = 0;
        for (Royalty royalty : this.royalties) {
            sum += royalty.getValue();
        }
        return sum;
    }

    public void pay() throws RoyaltyException {
        for (Royalty royalty : this.royalties) {
            royalty.pay();
        }
    }

    public boolean isPayed() {
        if( this.royalties.isEmpty() ) return false;
        for (Royalty royalty : this.royalties) {
            if (!royalty.isPayed()) return false;
        }
        return true;
    }

    public List<Royalty> getRoyalties(Predicate<Royalty> filter) {
        List<Royalty> tempList = new ArrayList<>(this.royalties);
        tempList.removeIf(filter.negate());
        return tempList;
    }

    public CompositeRoyalty append(Royalty r) throws RoyaltyException {
        if( super.getArtwork() != r.getArtwork()) {
            throw new RoyaltyException("Royalties artwork must match!");
        }
        this.royalties.add(r);

        return this;
    }

    public int removePayed() {

        int oldSize = this.royalties.size();
        this.royalties.removeIf(Royalty::isPayed);
        return oldSize - this.royalties.size();

    }

    @Override
    public String toString() {
        return "(MULTI)" + super.toString();
    }

}