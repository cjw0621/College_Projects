package ClassNotes;

public class practice1 {

    public static int mystery(int x, int y) {
        if (x < 0) {
            return -mystery(-x, y);
        } else if (y < 0) {
            return -mystery(x, -y);
        } else if (y < x) {
            return 0;
        } else {
            return 1 + mystery(x, y - x);
        }

    }

    public static void main(String[] args) {
        System.out.println(mystery(4,-15));
    }
}
