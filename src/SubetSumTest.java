import java.util.ArrayList;

public class SubetSumTest {

    public SubetSumTest(ArrayList<Integer> S, int t, double epsilon) {
        test(S, t, epsilon);
    }

    public SubetSumTest(int n, int delta, int min, int t, double epsilon) {
        ArrayList<Integer> S = generateSet(n, delta, min);
        test(S, t, epsilon);
    }

    public void test(ArrayList<Integer> S, int t, double epsilon) {
        System.out.println("-----------------------------------------------");
        ExactAlgorithm exactAlgorithm = new ExactAlgorithm(S, t);
        exactAlgorithm.showMeasures();

        System.out.println("-----------------------------------------------");
        FPTASAlgorithm FPTASAlgorithm = new FPTASAlgorithm(S, t, epsilon);
        FPTASAlgorithm.showMeasures();
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
