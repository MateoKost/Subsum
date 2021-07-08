import java.util.ArrayList;
import java.util.Collections;

public class FPTASAlgorithm extends ExactAlgorithm{

    double epsilon;

    public FPTASAlgorithm(ArrayList<Integer> S, int t, double epsilon) {
        super(S,t);
        this.epsilon = epsilon;
    }

    public ArrayList<ArrayList<Integer>> calculate() {

        System.out.println("S=" + S);
        System.out.println("t=" + t);
        System.out.println("epsilon=" + epsilon);

        ArrayList<ArrayList<Integer>> LN = new ArrayList<>();
        LN.add(L0);

        complexity = 5;
        complexity += 2;

        for (int i = 1; i <= n; i++) {
            ArrayList<Integer> Li = new ArrayList<>(LN.get(i - 1));
            ArrayList<Integer> _Lix = new ArrayList<>(LN.get(i - 1));

            complexity += 2;

            for (int j = 0; j < _Lix.size(); j++) {
                _Lix.set(j, _Lix.get(j) + S.get(i - 1));
            }

            complexity += _Lix.size()*2;

            _Lix.removeAll(LN.get(i - 1));
            Li.addAll(_Lix);

            complexity += _Lix.size()*2;

            Li = Trim(Li, epsilon / n);
            ArrayList<Integer> indexesToRemove = new ArrayList<>();

            complexity += 1;

            for (Integer integer : Li) {
                if (integer > t) {
                    indexesToRemove.add(integer);
                }
            }

            complexity += Li.size();

            Li.removeAll(indexesToRemove);
//            Collections.sort(Li);
            LN.add(Li);

            complexity += indexesToRemove.size();
            complexity += Li.size()*2;

//            if(indexesToRemove.size()>0) break;


        }
        System.out.println("steps: " + LN);
        System.out.println("max in LN :" + LN.get(LN.size()-1));
        return LN;
    }

    public ArrayList<Integer> Trim(ArrayList<Integer> L, double gamma) {

        int m = L.size();
        ArrayList<Integer> LPrim = new ArrayList<>();
        LPrim.add(L.get(0));
        int last = L.get(0);

        complexity += 4;

        for (int i = 1; i < m; i++) {
            int yi = L.get(i);
            if (last < (1 - gamma) * yi) {
                LPrim.add(yi);
                last = yi;
            }

            complexity += 6;

        }
        return LPrim;
    }

}
