package kr.funch.game;

import java.util.ArrayList;
import java.util.List;

public class PpeongCard {

    private int maxDecSize = 48;
    private List<Integer> cards;

    {
        cards = new ArrayList<>(maxDecSize);
        for (int i = 0 ; i < maxDecSize ; i++) {
            cards.add(i, i);
        }
    }

    public List<Integer> getCards() {
        return this.cards;
    }

}
