package domain;

public class Teller {

    private int waarde;

    public int getTeller(){
        return this.waarde;
    }

    public void add() {
        this.waarde++;
    }

    public void reset() {
        this.waarde = 0;
    }

}
