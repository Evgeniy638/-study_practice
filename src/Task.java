import java.math.BigInteger;
import java.util.Scanner;

public class Task {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int m = in.nextInt();
        int n = in.nextInt();

        System.out.println(f(BigInteger.valueOf(m), BigInteger.valueOf(n)).toString());
    }

    public static BigInteger f(BigInteger m, BigInteger n){
        if(m.equals(BigInteger.valueOf(0))){
            return n.add(BigInteger.valueOf(1));
        }else if (n.equals(BigInteger.valueOf(0))){
            return f(m.add(BigInteger.valueOf(-1)), BigInteger.valueOf(1));
        }else {
            return f(m.add(BigInteger.valueOf(-1)), f(m, n.add(BigInteger.valueOf(-1))));
        }
    }
}
