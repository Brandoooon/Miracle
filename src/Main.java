import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    static final String[] SUITS = {String.valueOf('\u2660'),String.valueOf('\u2764'),String.valueOf('\u2663'),String.valueOf('\u2666')};
    static final String[] CARDS = {"A","2","3","4","5","6","7","8","9","10","J","Q","K"};
    static List<String> deck = new LinkedList<>();

    public static void main(String[] args) {
        //初始化4张牌的牌组
        initialDeck();
        System.out.println("初始牌组为："+deck);
        System.out.println("_________________________________________________________");

        //撕开卡组并按顺序摆放
        deck.addAll(deck);
        System.out.println("撕开后牌组为："+deck);
        System.out.println("_________________________________________________________");

        //按名字字数搬运牌顶卡牌到牌底
        System.out.println("请输入你的名字的字数：");
        int name_length = new Scanner(System.in).nextInt();
        for (int i = 0; i < name_length; i++) {
            String top_card = deck.remove(0);
            deck.add(top_card);
        }
        System.out.println("搬运后的牌组为："+deck);
        System.out.println("_________________________________________________________");

        //将牌顶的3张牌插入到中间
        //只能插在剩下5张牌的中间，所以插入位置只能是4/5/6/7
        int insert_index = new Random().nextInt(4, 7);
        insertCards(3, insert_index);
        System.out.println("插入3张牌后卡组为："+deck);
        System.out.println("_________________________________________________________");

        //将最上面的一张牌放到屁股底下
        String under_hip = deck.remove(0);
        System.out.println("放到屁股下之后的牌组为："+deck);
        System.out.println("_________________________________________________________");

        //选择你是南方人/北方人/不知道人
        System.out.println("1.南方人---2.北方人---3.不知道");
        int location = new Scanner(System.in).nextInt();
        insertCards(location,new Random().nextInt(deck.size()-location,deck.size()));

        System.out.println("选择南北方人之后的牌组为："+deck);
        System.out.println("_________________________________________________________");

        //丢牌
        System.out.println("1.男生丢1张---2.女生丢2张");
        int gender = new Scanner(System.in).nextInt();
        deck.remove(0);
        if (gender == 2){
            deck.remove(0);
        }
        System.out.println("选择男女生之后的牌组为："+deck);
        System.out.println("_________________________________________________________");

        //见证奇迹的时刻
        String tmp;
        for (int i = 0; i < 7; i++) {
            tmp = deck.remove(0);
            deck.add(tmp);
        }
        System.out.println("见证奇迹后的牌组为："+deck);
        System.out.println("_________________________________________________________");

        //好运留下来,烦恼丢出去。
        while (deck.size() > 1){
            tmp = deck.remove(0);
            deck.add(tmp);
            deck.remove(0);
            System.out.println(deck);
        }

        //最后一张卡牌
        System.out.println("_________________________________________________________");
        System.out.println("最后一张卡牌是"+deck.get(0));
        System.out.println("屁股下面卡牌是"+under_hip);
    }

    //初始化4张牌的牌组
    static void initialDeck() {
        while (deck.size() < 4) {
            String suit = SUITS[new Random().nextInt(SUITS.length)];
            String value = CARDS[new Random().nextInt(CARDS.length)];
            String card = suit + value;
            if (!deck.contains(card)) {
                deck.add(card);
            }
        }
    }

    static void insertCards(int card_count, int insert_index){
        int deck_size = deck.size();
        List<String> slice1 = deck.subList(0, card_count);
        List<String> slice2 = deck.subList(card_count, insert_index);
        List<String> slice3 = deck.subList(insert_index, deck_size);
        List<String> new_deck = Stream.of(slice2, slice1, slice3).flatMap(Collection::stream).collect(Collectors.toList());
        deck.clear();
        deck.addAll(new_deck);
    }
}
