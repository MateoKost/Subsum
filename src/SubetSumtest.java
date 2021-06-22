import java.util.ArrayList;

public class SubetSumtest {

    public SubetSumtest(ArrayList<Integer> S, int t, double epsilon) {
        test(S, t, epsilon);
    }

    public SubetSumtest(int n, int delta, int min, int t, double epsilon) {
        ArrayList<Integer> S = generateSet(n, delta, min);
        test(S, t, epsilon);
    }

    public void test(ArrayList<Integer> S, int t, double epsilon) {
        System.out.println("-----------------------------------------------");

        ExactAlgorithm exactAlgorithm = new ExactAlgorithm(S, t);

        double[] measures = exactAlgorithm.measure();
        System.out.println("Czas wykonania [ms] :" + measures[0]);
        System.out.println("Zajętość pamięci [bytes] :" + measures[1]);

        if (exactAlgorithm.verify()) {
            System.out.println("Suma zgodna");
        } else {
            System.out.println("Suma nie jest zgodna");
        }

        System.out.println("-----------------------------------------------");

        FTPASAlgorithm ftpasAlgorithm = new FTPASAlgorithm(S, t, epsilon);
        measures = ftpasAlgorithm.measure();
        System.out.println("Czas wykonania [ms] :" + measures[0]);
        System.out.println("Zajętość pamięci [bytes] :" + measures[1]);
        if (ftpasAlgorithm.verify()) {
            System.out.println("Suma zgodna");
        } else {
            System.out.println("Suma nie jest zgodna");
        }
    }

    public ArrayList<Integer> generateSet(int n, int delta, int min) {
        ArrayList<Integer> S = new ArrayList<>();
        S.add(min);
        Uniform uniform = new Uniform(1, 10);
        for (int i = 1; i < n; i++) {
            S.add(S.get(i - 1) + uniform.sample());
        }
        return S;
    }

}
