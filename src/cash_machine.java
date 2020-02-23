import java.util.Scanner;

import static java.lang.System.out;

public class cash_machine {

    private static Scanner in = new Scanner(System.in);

    private static final int[] face_value_banknotes = {
            1000,
            500,
            100,
            30
    };

    public static void main(String[] args) {
        int[] count_banknotes = enter_count_banknotes();
        int amount = enter_amount();
        int[] count_result_banknotes = get_count_result_banknotes(count_banknotes, amount);
        show_result(count_result_banknotes);
    }

    private static void start_test(){
        int[][] counts_banknotes = {
                {100, 100, 100, 100},
                {0, 0, 1, 4},
                {99, 1, 4, 3},
                {0, 0, 5, 0},
                {100, 100, 100, 0},
                {100, 100, 0, 0},
                {100, 0, 0, 0},
                {0, 0, 0, 0},
        };

        int[] amounts = {
                4620,
                220,
                99990,
                500,
                90,
                100,
                500,
                1000
        };

        for (int i = 0; i < amounts.length; i++){
            out.print("\n\n");

            for (int count: counts_banknotes[i]) {
                out.print(count + " ");
            }

            out.print("\n" + amounts[i]);

            int[] count_result_banknotes = get_count_result_banknotes(counts_banknotes[i], amounts[i]);
            show_result(count_result_banknotes);
        }
    }

    private static int[] enter_count_banknotes(){
        int[] count_banknotes = new int[face_value_banknotes.length];

        for (int i = 0; i < face_value_banknotes.length; i++){
            out.print("Введите количество банкнот с номиналом в " + face_value_banknotes[i] + ": ");
            count_banknotes[i] = in.nextInt();
        }

        return count_banknotes;
    }

    private static int enter_amount(){
        out.print("Введите сумму, которую нужно выдать: ");
        int amount = in.nextInt();
        out.println();

        return  amount;
    }

    private static int[] get_count_result_banknotes(int[] count_banknotes, int amount){
        int[] count_result_banknotes = new int[face_value_banknotes.length];

        for (int i = face_value_banknotes.length - 1; i >= 0 ; i--) {
            while ((i == 0 || amount % face_value_banknotes[i - 1] != 0)
                    && amount >= face_value_banknotes[i] && count_banknotes[i] > 0){
                amount -= face_value_banknotes[i];
                count_banknotes[i]--;
                count_result_banknotes[i]++;
            }
        }

        for (int i = 0; i < face_value_banknotes.length; i++) {
            while (amount >= face_value_banknotes[i] && count_banknotes[i] > 0){
                amount -= face_value_banknotes[i];
                count_banknotes[i]--;
                count_result_banknotes[i]++;
            }
        }

        if(amount > 0) return null;

        return count_result_banknotes;
    }

    private static void show_result(int[] count_result_banknotes){

        out.println("\nРезультат:");

        try {
            for (int i = 0; i < face_value_banknotes.length; i++){
                out.print((i + 1) + ") количество банкнот с номиналом в " +
                        face_value_banknotes[i] + ": " + count_result_banknotes[i]);
                out.println();
            }
        }catch (NullPointerException e){
            out.print("Такую сумму нельзя выдать текущими банкнотами");
        }

    }
}
