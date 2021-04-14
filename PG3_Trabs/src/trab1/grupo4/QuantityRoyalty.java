package trab1.grupo4;

import trab1.grupo3.Artwork;

public class QuantityRoyalty extends Royalty {

    private final int value;
    private final int nUtil;
    private boolean payment = false;

    public QuantityRoyalty(Artwork aw, int v, int n) {
        super(aw);
        this.value = v;
        this.nUtil = n;
    }

    public int getValue() {
        return this.value * this.nUtil;
    }

    public void pay() throws RoyaltyException {
        if(this.payment) {
            throw new RoyaltyException("One royalty cannot be payed twice");
        }
        this.payment = true;
    }

    public boolean isPayed() {
        return this.payment;
    }

}



