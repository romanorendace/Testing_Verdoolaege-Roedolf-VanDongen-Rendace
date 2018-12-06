package domain;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TellerTest {

    private Teller teller, andereTeller;

    @Before
    public void setUp() throws Exception {
        teller = new Teller();
        andereTeller = new Teller();
        andereTeller.add();
        andereTeller.add();

    }

    @Test
    public void add() {
        teller.add();
        teller.add();
        assertEquals(2, teller.getTeller());
    }

    @Test
    public void reset() {
        andereTeller.reset();
        assertEquals(0, andereTeller.getTeller());
    }
}