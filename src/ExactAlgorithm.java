import java.util.ArrayList;
import java.util.Collections;

public class ExactAlgorithm {
    protected int n;
    protected int t;
    protected int max;
    protected ArrayList<Integer> L0;
    protected ArrayList<Integer> S;

    public ExactAlgorithm(ArrayList<Integer> S, int t) {
        this.n = S.size();
        this.S = S;
        this.t = t;
        L0 = new ArrayList<>();
        L0.add(0);
    }

    public Boolean verify() {
        return max == t;
    }

    public double[] measure() {
        double[] measures = new double[2];

        long startTime = System.currentTimeMillis();

        max = findMax();

        Runtime runtime = Runtime.getRuntime();
        runtime.gc();
        long memory = runtime.totalMemory() - runtime.freeMemory();
        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;

        measures[0] = elapsedTime;
        measures[1] = memory;
        return measures;
    }

    protected ArrayList<ArrayList<Integer>> calculate() {

        System.out.println("S=" + S);
        System.out.println("t=" + t);

        ArrayList<ArrayList<Integer>> LN = new ArrayList<>();
        LN.add(L0);

        for (int i = 1; i <= n; i++) {
            ArrayList<Integer> Li = new ArrayList<>(LN.get(i - 1));
            ArrayList<Integer> _Lix = new ArrayList<>(LN.get(i - 1));

            for (int j = 0; j < _Lix.size(); j++) {
                _Lix.set(j, _Lix.get(j) + S.get(i - 1));
            }

            _Lix.removeAll(LN.get(i - 1));
            Li.addAll(_Lix);

            ArrayList<Integer> indexesToRemove = new ArrayList<>();

            for (Integer integer : Li) {
                if (integer > t) {
                    indexesToRemove.add(integer);
                }
            }

            Li.removeAll(indexesToRemove);
            Collections.sort(Li);
            LN.add(Li);
        }

        System.out.println("steps: " + LN);
        System.out.println("max in LN :" + LN.get(LN.size()-1));
        return LN;
    }

    protected int findMax() {
        ArrayList<ArrayList<Integer>> calculatedSubsets = calculate();
        ArrayList<Integer> lastSubset = calculatedSubsets.get(calculatedSubsets.size() - 1);
        return lastSubset.get(lastSubset.size() - 1);
    }

    public void showMeasures(){
        double[] measures = measure();
        System.out.println("Czas wykonania [ms] :" + measures[0]);
        System.out.println("Zajętość pamięci [bytes] :" + measures[1]);

        if (verify()) {
            System.out.println("Suma zgodna");
        } else {
            System.out.println("Suma nie jest zgodna");
        }
    }
}
