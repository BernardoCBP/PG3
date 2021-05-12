package trab1.grupo4;

import trab1.grupo3.Artwork;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class CompositeRoyalty extends Royalty {

    private List<Royalty> royalties = new ArrayList<>();

    public CompositeRoyalty( Artwork aw ) {
        super(aw);
    }

    public int getValue() {
        int value = 0;
        for( Royalty royalty : this.royalties ) {
            value += royalty.getValue();
        }
        return value;
    }

    public void pay() {
        try {
            for( Royalty royalty : this.royalties ) {
                if( !(royalty.isPayed()) ) royalty.pay();
            }
        }
        catch(RoyaltyException e) {
            throw new RuntimeException("Unexpected Exception!");
        }
    }

    public boolean isPayed() {
        if( this.royalties.isEmpty() ) return false;
        for( Royalty royalty : this.royalties ) {
            if( !(royalty.isPayed()) ) return false;
        }
        return true;
    }

    public List<Royalty> getRoyalties( Predicate<Royalty> filter ) {
        List<Royalty> tempList = new ArrayList<>();

        for (Royalty royalty : this.royalties) {
            if (filter.test(royalty)) {
                tempList.add(royalty);
            }
        }
        return tempList;
    }

    public CompositeRoyalty append( Royalty r ) throws RoyaltyException {
        if( super.getArtwork() != r.getArtwork() ) throw new RoyaltyException("Royalties artwork must match!");
        this.royalties.add(r);
        return this;
    }

    public int removePayed() {
        int nRoyalties = this.royalties.size();
        this.royalties.removeIf(Royalty::isPayed);
        return nRoyalties - this.royalties.size();
    }

    @Override
    public String toString() {
        return "(MULTI)" + super.toString();
    }

}