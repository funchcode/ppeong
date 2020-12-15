package kr.funch.game;

import java.util.ArrayList;
import java.util.List;

public class Card {

    private List<Integer> cards;

    {
        this.cards = new ArrayList<>(48);
        for (int i = 0 ; i < 48 ; i++) {
            cards.add(i, i);
        }
    }
}
