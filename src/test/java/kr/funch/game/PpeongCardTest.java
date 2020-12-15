package kr.funch.game;

import org.junit.Assert;
import org.junit.Test;

public class PpeongCardTest {

    @Test
    public void getPpeongCards() {
        int ppeongCardSize = 48;
        PpeongCard card = new PpeongCard();
        Assert.assertEquals(card.getCards().size(), ppeongCardSize);
        System.out.println(card.getCards());
    }

}
