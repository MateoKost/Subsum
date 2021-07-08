import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        for( int t : new int[]{100,1000,10000,100000} ) {
            new SubetSumTest(new ArrayList<>(Arrays.asList(10, 11, 12, 15, 20, 21, 22, 23, 24, 29)), t, 0.1);
        }

        for( int n : new int[]{10,50,100,1000} ) {
            new SubetSumTest(n, 10, 10, 100, 0.25);
        }

    }
}
