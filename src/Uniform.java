import java.util.Random;

public class Uniform {

    double MIN, MAX;

    public Uniform(double MIN, double MAX) {
        this.MIN = MIN;
        this.MAX = MAX;
    }

    public double runif() {
        return new Random().nextDouble() * (MAX - MIN) + MIN;
    }

    public int sample() {
        return (int) (new Random().nextDouble() * (MAX - MIN) + MIN);
    }

}
