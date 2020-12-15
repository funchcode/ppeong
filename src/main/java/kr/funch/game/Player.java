package kr.funch.game;

import java.util.ArrayList;
import java.util.List;

public class Player {
    String name;
    List<Integer> card = new ArrayList<>(6);

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Integer> getCard() {
        return this.card;
    }

    public void setCard(List<Integer> card) {
        this.card = card;
    }

    public Integer removeCard(int index) {
        return this.card.remove(index);
    }

    public List<Integer> addCard(Integer card) {
        this.card.add(card);
        return this.card;
    }
}
