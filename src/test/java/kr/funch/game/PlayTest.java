package kr.funch.game;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class PlayTest {

    public void testCase() {
        // 뻥 카드 초기화
        // 패 돌리기
        // 진행자, 카드 뽑기
        // (전략선택) 버리기, 세우기, 털기, 또이또이, 100마이, 200마이
        // (나머지 인원) 뻥 -> 버리기
            // 뻥한 다음 사람부터 진행 계속
    }

    @Test
    public void initMainCard() {
        Play play = new Play();
        List<Integer> mainCard = play.getMainCard();
        Assert.assertEquals(48, mainCard.size());
        System.out.println(mainCard);
    }

    @Test
    public void shuffleMainCard() {
        Play play = new Play();
        List<Integer> mainCard = play.getMainCard();
        mainCard = play.shuffleMainCard();
        System.out.println(mainCard);
    }

    @Test
    public void pullCard() {
        int count = 5;
        Play play = new Play();
        List<Integer> pullCard = play.pullCard(count);
        Assert.assertEquals(count, pullCard.size());
    }

    @Test
    public void popMainCard() {
        Play play = new Play();
        int target = play.popMainCard();
        Assert.assertEquals(target, 47);
        Assert.assertEquals(play.getMainCard().size(), 47);
    }
}
