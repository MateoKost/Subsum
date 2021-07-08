import java.util.ArrayList;
import java.util.Collections;

public class ExactAlgorithm {
    protected int n;
    protected int t;
    protected int max;
    protected ArrayList<Integer> L0;
    protected ArrayList<Integer> S;
    protected int complexity;

    public ExactAlgorithm(ArrayList<Integer> S, int t) {
        this.n = S.size();
        this.S = S;
        this.t = t;
        L0 = new ArrayList<>();
        L0.add(0);
//        complexity = 5;
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

        complexity = 5;
        complexity += 2;

        for (int i = 1; i <= n; i++) {
            ArrayList<Integer> Li = new ArrayList<>(LN.get(i - 1));
            ArrayList<Integer> _Lix = new ArrayList<>();

            complexity += 2;

            for (int j = 0; j < Li.size(); j++) {
                _Lix.add(Li.get(j) + S.get(i - 1));
            }

            complexity += _Lix.size()*2;

            _Lix.removeAll(Li);
            Li.addAll(_Lix);

            complexity += _Lix.size()*2;

            ArrayList<Integer> indexesToRemove = new ArrayList<>();

            complexity += 1;

            for (Integer integer : Li) {
                if (integer > t) {
                    indexesToRemove.add(integer);
                }
            }

            complexity += Li.size();

            Li.removeAll(indexesToRemove);
            Collections.sort(Li);
            LN.add(Li);

            complexity += indexesToRemove.size();
            complexity += Li.size()*2;

//            if(indexesToRemove.size()>0) break;

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
        System.out.println("Złożoność obliczeniowa :" + complexity);
        System.out.println("Czas wykonania [ms] :" + measures[0]);
        System.out.println("Zajętość pamięci [Bytes] :" + measures[1]);

        if (verify()) {
            System.out.println("Suma zgodna");
        } else {
            System.out.println("Suma nie jest zgodna");
        }

    }
}
