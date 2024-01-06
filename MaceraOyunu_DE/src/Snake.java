import java.util.Random;

public class Snake extends Obstacle {
    public Snake() {
        super(4, randomSnakeDamage(), 12, 0, "Yılan");
    }

    public static int randomSnakeDamage() {
        Random r = new Random();
        return r.nextInt(3) + 3;
    }
}
