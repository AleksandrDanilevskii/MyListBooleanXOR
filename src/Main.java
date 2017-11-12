import java.util.function.Function;

public class Main {

    public static class Booleans {
        boolean[] booleans = new boolean[2];

        public Booleans(boolean val1, boolean val2) {
            booleans[0] = val1;
            booleans[1] = val2;
        }

        public void printf() {
            System.out.println(booleans[0] + " " + booleans[1]);
        }


        public boolean xor() {
            Function<boolean[], Boolean> xor = bool -> bool[0] ^ bool[1];
            return xor.apply(booleans);
        }
    }


    public static void main(String s[]) throws Exception {

        MyList<Booleans> list = new MyList<>();
        Booleans boolFF = new Booleans(false, false);
        Booleans boolTT = new Booleans(true, true);
        Booleans boolTF = new Booleans(true, false);
        Booleans boolFT = new Booleans(false, true);

        System.out.println("Add item:");
        list.add(boolTT);
        list.add(boolFF);
        list.add(boolFT);
        list.add(boolTF);
        list.add(boolFT);
        list.add(boolTT);
        list.add(boolTF);

        for (int i = 0; i < list.length(); i++) {
            list.get(i).printf();
        }
            System.out.println();

        int indexRemove = 4;
        System.out.println("Deleting an item "+indexRemove+":");
        list.remove(indexRemove);
        for (int i = 0; i < list.length(); i++) {
            list.get(i).printf();
        }
        System.out.println();

        System.out.println("Checking the inclusion [false true]:");
        list.contains(boolFT);
        System.out.println();

        for (int i = 0; i < list.length(); i++) {
            list.get(i).printf();
        }
        System.out.println();

        System.out.println("Sort:");
        list.sort(list);
        for (int i = 0; i < list.length(); i++) {
            list.get(i).printf();
        }


        list.forEach(Matrix2x2 -> Matrix2x2.print());
    }
}
