import java.util.function.Consumer;

public class MyList<Type> {

    private Item currentEl;         //для добавления
    private Item firstEl;

    public void add(Type v) {
        if (firstEl == null)
            firstEl = new Item(v);
        else if (firstEl.pointer == null) {
            currentEl = new Item(v);
            firstEl.pointer = currentEl;
            currentEl.pointer = null;
        } else {
            Item item = new Item(v);
            currentEl.pointer = item;
            currentEl = item;
        }
    }

    public int length() {
        int i = 0;
        Item item;
        if (firstEl != null) {
            item = firstEl;
            while (item.pointer != null) {
                item = item.pointer;
                i++;
            }
            return i + 1;
        } else return 0;
    }

    public void remove(int number) throws Exception {
        if (firstEl != null) {          //если есть элементы
            if (number > 0) {           //если удаляем не нулевой
                Item before = getEl(number - 1);
                if (before != null)
                    if (before.pointer != null) {         //если удаляемый элемент существует
                        if (before.pointer.pointer != null) before.pointer = before.pointer.pointer;
                        else {
                            currentEl = before;
                            currentEl.pointer = null;
                        }
                    }
            } else {
                if (firstEl.pointer == null) firstEl = null;
                else firstEl = firstEl.pointer;
            }
        }
    }

    public Item getEl(int number) throws Exception {
        Item item;
        if (firstEl != null) {   //если есть элементы
            item = firstEl;
            for (int j = 0; j < number; j++) {
                if (item.pointer != null)
                    item = item.pointer;
                else throw new Exception();
            }
            return item;
        } else throw new Exception();
    }

    public Type get(int number) throws Exception {
        Item item;
        if (firstEl != null) {   //если есть элементы
            item = firstEl;
            for (int j = 0; j < number; j++) {
                if (item.pointer != null)
                    item = item.pointer;
                else throw new Exception();
            }
            return item.value;
        } else throw new Exception();
    }

    public void sort(MyList<Main.Booleans> list) throws Exception {
        int l = list.length();
        System.out.println("XOR with lambda:");
        for(int i=0;i<list.length();i++){System.out.println(list.get(i).xor());}
        System.out.println();
        for (int i = 0; i < l - 1; i++) {
            for (int j = i + 1; j < l; j++) {
                if ((list.get(i).xor() == false) && (list.get(j).xor())) {
                    if (i == 0) {
                        if (l == 2) {                         //если 2 элемента
                            list.getEl(1).pointer = list.getEl(0);
                            firstEl = firstEl.pointer;
                            list.getEl(1).pointer = null;
                            currentEl = firstEl.pointer;
                            break;
                        } else if (j == l - 1) {           //если менять первый с последним
                            list.getEl(l - 1).pointer = list.getEl(1);
                            Item e;
                            e = (Item) list.getEl(j);
                            list.getEl(j - 1).pointer = list.getEl(i);
                            e.pointer = firstEl.pointer;
                            firstEl = e;
                            list.getEl(j).pointer = null;
                            currentEl = (Item) list.getEl(j);
                            break;
                        } else {                             //меняю первый с не последним
                            Item e;
                            e = (Item) list.getEl(j);
                            list.getEl(j - 1).pointer = list.getEl(i);
                            Item e2;
                            e2 = e.pointer;
                            e.pointer = firstEl.pointer;
                            ((Item) list.getEl(i)).pointer = e2;
                            firstEl = e;
                            break;
                        }
                    } else if (j == l - 1) {                  //если меняю последний не с первым
                        if ((i + 1) != j) {
                            Item e;
                            e = (Item) list.getEl(j);
                            e.pointer = (Item) list.getEl(i + 1);
                            list.getEl(j - 1).pointer = list.getEl(i);
                            list.getEl(i).pointer = null;
                            ((Item) list.getEl(i - 1)).pointer = e;
                            currentEl = (Item) list.getEl(l - 1);
                            break;
                        } else {                                //меняю последний и предпоследний
                            Item e;
                            e = (Item) list.getEl(j - 1);
                            list.getEl(j - 2).pointer = list.getEl(j);
                            ((Item) list.getEl(j - 1)).pointer = e;
                            e.pointer = null;
                            currentEl = (Item) list.getEl(l - 1);
                            break;
                        }
                    } else {           //меняю не первый и не последний
                        if ((j - i) != 1) {
                            Item e;
                            e = (Item) list.getEl(i);
                            Item e1;
                            e1 = (Item) list.getEl(j - 1);
                            Item e2;
                            e2 = e1.pointer.pointer;
                            list.getEl(i - 1).pointer = list.getEl(j);
                            ((Item) list.getEl(i)).pointer = e.pointer;
                            e1.pointer = e;
                            e.pointer = e2;
                            break;
                        } else {
                            Item e;
                            e = (Item) list.getEl(i);
                            Item e2;
                            e2 = (Item) list.getEl(i + 2);
                            ((Item) list.getEl(i - 1)).pointer = e.pointer;
                            ((Item) list.getEl(i)).pointer = e;
                            e.pointer = e2;
                            break;
                        }
                    }
                }
            }
        }
    }

    public void contains(Type v) {
        boolean flag = true;
        Item current = firstEl;
        while (current != null) {
            if (current.value.equals(v)) {
                System.out.println("Item in");
                flag = false;
                break;
            }
            current = current.pointer;
        }
        if (flag){
            System.out.println("Item not in");
        }
    }

    public class Item {
        Type value;
        Item pointer;

        public Item(Type a) {
            this.pointer = null;
            this.value = a;
        }

        public void print() {
            //for (int i = 0; i < 1; i++) {
                System.out.print(value + " ");
                //if (i == 1) {
                //    System.out.println();
                //}
            //}
            System.out.println();
            System.out.println();
        }
    }

    public void forEach(Consumer<Item> action) {
        Item current = firstEl;
        while (current != null) {
            action.accept (current);
            current = current.pointer;
        }
    }

}
