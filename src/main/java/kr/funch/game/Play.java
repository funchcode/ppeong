package kr.funch.game;

import java.util.*;

public class Play {

    List<Integer> mainCard;
    List<Integer> floorCard = new ArrayList<>();
    {
        this.mainCard = new ArrayList<>(48);
        for (int i = 0 ; i < 48 ; i++) {
            mainCard.add(i, i);
        }
    }

    public static void main(String[] args) {
        Play play = new Play();
        play.play(4);
    }

    public void play(int playerCount) {
        List<Player> players = new ArrayList<>(playerCount);

        // todo 선 뽑기

        // 패 섞기
        mainCard = shuffleMainCard();

        for (int i = 0 ; i < playerCount ; i++) {
            Player player = new Player();
            player.setCard(pullCard(5));
            player.setName(String.valueOf(i));
            players.add(player);
            System.out.println(i + " : player, Card = " + players.get(i).getCard());
        }

        int target = 0;
        // 게임시작
        while (true) {
            if (mainCard.size() == 0) {
                mainCard.addAll(floorCard);
                floorCard = new ArrayList<>();
                shuffleMainCard();
            }
            int currentIdx = target % playerCount;
            Player currentPlayer = players.get(currentIdx);
            System.out.println(currentPlayer.getName() + " 플레이어 차례입니다.");

            Scanner sc = new Scanner(System.in);
            System.out.println("전체 카드 패 수 : " + mainCard.size());
            System.out.println("바닥 패 정보 : " + floorCard);

            // 로그용 로직
            for (int i = 0 ; i < players.size() ; i++) {
                System.out.println(players.get(i).getName() + " 플레이어 카드 정보 : " + players.get(i).getCard());
            }

            // 세우기


            // 패뽑기
            Integer pop = popMainCard();
            players.get(currentIdx).addCard(pop);
            System.out.println(currentPlayer.getName() +
                    " 플레이어 카드 패 : " + players.get(currentIdx).getCard().toString());

            // 전략 (자연털기, 또이또이1, 100마이, 200마이)
            Map<Integer, Integer> analysis = new HashMap<>();
            for(int i = 0 ; i < currentPlayer.getCard().size() ; i++) {
                Integer card = currentPlayer.getCard().get(i);
                if (!analysis.containsKey(card)) {
                    analysis.put(card, 1);
                } else {
                    Integer count = analysis.get(card);
                    analysis.put(card, ++count);
                }
            }
            Set<Integer> keys = analysis.keySet();
            if (currentPlayer.getCard().size() == 3 && keys.size() == 1) {
                /*
                    패가 3장인데 모두 같은 경우
                 */
                // 또이또이
            } else if (currentPlayer.getCard().size() == 6 && keys.size() == 3) {
                /*
                    패가 6장이고 패가 3쌍으로 짝지어지는 경우
                 */
                boolean triple = true;
                for(Integer key : keys) {
                    if (analysis.get(key) != 2) {
                        triple = false;
                    }
                }
                if (triple) {
                    // 또이또이
                }
            } else if (currentPlayer.getCard().size() == 6 && keys.size() == 2) {
                /*
                    두 쌍에 같은 패가 2장 4장인 경우
                 */
                boolean checkMate = false;
                int total = 0;
                for(Integer key : keys) {
                    total += (key + 1) * analysis.get(key);
                    if (analysis.get(key) == 4) {
                        checkMate = true;
                    }
                }
                if (checkMate) {
                    if (total <= 10) {
                        // 200 마이
                    } else {
                        // 100 마
                    }
                }
            } else if (currentPlayer.getCard().size() == 6 && keys.size() == 6) {
                /*
                    카드가 연속으로 나올 경
                 */
                boolean continuous = true;
                List<Integer> keyList = new ArrayList<>(keys);
                keyList.sort((s1, s2) -> s1.compareTo(s2));
                int compare = 0;
                for (int i = 0 ; i < keyList.size() ; i++) {
                    if (i == 0) {
                        compare = keyList.get(i);
                        continue;
                    }
                    if (keyList.get(i) - compare != 1) {
                        continuous = false;
                        break;
                    }
                }
                if (continuous) {
                    // 또이또
                }
            }


            String a = sc.next();
            System.out.println(a);
            Integer select = players.get(currentIdx).removeCard(Integer.valueOf(a));
            floorCard.add(select);

            for (int i = 0 ; i < players.size() ; i++) {
                if (currentIdx == i) {
                    continue;
                }
                int selectValue = select / 4;

                int matchCount = 0;
                List<Integer> matchIdx = new ArrayList<>();
                for (int j = 0 ; j < players.get(i).getCard().size() ; j ++) {
                    if (players.get(i).getCard().get(j) / 4 == selectValue) {
                        matchCount ++;
                        matchIdx.add(j);
                    }
                }

                if (matchCount >= 2) {
                    // 해당 유저의 이벤트 있을 시
                    for (int k = matchIdx.size() - 1 ; k >= 0 ; k--) {
                        System.out.println(i + " 플레이어 : " + matchIdx.get(k));
                        floorCard.add(players.get(i).removeCard(matchIdx.get(k)));
                        // 해당 유저 패 버리기
                    }
                    if (players.get(i).getCard().size() == 0) {
                        System.out.println(i + " 플레이어의 승리입니다!!!!!");
                        System.out.println(currentIdx + " 플레이어는 -30 점 씌였습니다.");
                        // 계산
                        return;
                    }
                    Scanner sc1 = new Scanner(System.in);
                    System.out.println("전체 카드 패 수 : " + mainCard.size());
                    System.out.println("바닥 패 정보 : " + floorCard);
                    System.out.println(currentPlayer.getName() +
                            " 플레이어 카드 패 : " + players.get(i).getCard().toString());
                    String a1 = sc1.next();
                    System.out.println(a1);
                    Integer select1 = players.get(i).removeCard(Integer.valueOf(a1));
                    floorCard.add(select1);
                    target = i;
                    break;
                }
            }
            target ++;
        }
    }

    public List<Integer> getMainCard() {
        return mainCard;
    }

    public List<Integer> shuffleMainCard() {
        Collections.shuffle(mainCard);
        return mainCard;
    }

    public List<Integer> pullCard(int count) {
        List<Integer> pullCard = new ArrayList<>(count);
        int mainCardSize = mainCard.size();
        for (int i = 0 ; i < count ; i++) {
            pullCard.add(mainCard.remove((mainCardSize - i) - 1));
        }
        return pullCard;
    }

    public Integer popMainCard() {
        return mainCard.remove(mainCard.size() - 1);
    }
}
