import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

import static java.lang.System.out;

public class Task_2 {

    private static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        String line1 = in.nextLine();
        String line2 = in.nextLine();

        Map<Character,Integer> dictionary1 = new HashMap<>();
        Map<Character,Integer> dictionary2 = new HashMap<>();

        if(line1.length() != line2.length()){
            out.println("NO");
            return;
        }

        //считываем элементы строк
        for (int i = 0; i < line1.length(); i++) {
            if (dictionary1.containsKey(line1.charAt(i))){
                dictionary1.put(line1.charAt(i), dictionary1.get(line1.charAt(i)) + 1);
            }else {
                dictionary1.put(line1.charAt(i), 1);
            }

            if (dictionary2.containsKey(line2.charAt(i))){
                dictionary2.put(line2.charAt(i), dictionary2.get(line2.charAt(i)) + 1);
            }else {
                dictionary2.put(line2.charAt(i), 1);
            }
        }

        //сравниваем
        if(dictionary1.size() != dictionary2.size()){
            out.println("NO");
            return;
        }

        AtomicBoolean flag = new AtomicBoolean(true);
        dictionary1.forEach((key, value) -> {
            if (!dictionary2.containsKey(key)){
                flag.set(false);
            }else if (value != dictionary2.get(key)){
                flag.set(false);
            }
        });

        if(flag.get()){
            out.println("YES");
        }else {
            out.println("NO");
        }
    }
}

/*
aaab
baaa

abcd
cabd

aab
bba

aaa
aaaa
*/
