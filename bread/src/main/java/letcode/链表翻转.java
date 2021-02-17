package letcode;

/**
 * 链表反转
 * <p>Description: </p>
 *
 * @author heyman
 * @date 2021/2/11
 */
public class 链表翻转 {

    public static void main(String[] args) {
        LinkedListSupport<String> support = new LinkedListSupport<>();
        Node<String> lkList = support.initLinkedList("h e l l o java", " ");
        lkList.print();
        Node<String> afList = support.reverse(lkList);
        afList.print();

        LinkedListSupport<String> supportInt = new LinkedListSupport<>();
        Node<String> lkListInt = supportInt.initLinkedList("1 2 3 4 5", " ");
        lkListInt.print();
        Node<String> afListInt = supportInt.reverse(lkListInt);
        afListInt.print();
    }

    private static Node<String> reverse(Node<String> lkList, Node<String> tmp) {
        if (lkList == null){
            return tmp;
        }
        Node<String> next =lkList.getNext();
        lkList.setNext(tmp);
        tmp = lkList;
        return reverse(next,tmp);
    }


    static class LinkedListSupport<T> {
        private  Node<T> initLinkedList(String hello, String splitStr) {
            if(hello == null || hello.equals("")) {
                throw new IllegalArgumentException();
            }

            String[] atrArr = null;
            if (splitStr.equals(" ")) {
               atrArr = hello.split(" ");
            }

            return init(null, atrArr, atrArr.length -1);
        }

        private Node<T> init(Node<T> head, String[] atrArr, int idx) {
            T tarStr = (T)atrArr[idx];
            Node<T> node = new 链表翻转.Node<>(head, tarStr);
            if (idx == 0) {
                return node;
            }
            return init(node, atrArr, --idx);
        }

        private Node<T> reverse(Node<T> lkList){
            return reverse(lkList, null);
        }
        private Node<T> reverse(Node<T> lkList, Node<T> tmp) {
            if (lkList == null){
                return tmp;
            }
            Node<T> next =lkList.getNext();
            lkList.setNext(tmp);
            tmp = lkList;
            return reverse(next,tmp);
        }
    }

    static class Node<T> {
        private T val;

        private Node<T> next;

        public Node(Node<T> next, T val) {
            this.next = next;
            this.val = val;
        }
        public void print(){
            print(this);
        }
        private void print(Node<T> node) {
            System.out.print(node.getVal());
            if (node.getNext() != null) {
                System.out.print("->");
                print(node.getNext());
            } else {
                System.out.println();
            }
        }

        public Node<T> getNext() {
            return next;
        }

        public void setNext(Node<T> next) {
            this.next = next;
        }

        public T getVal() {
            return val;
        }

        public void setVal(T val) {
            this.val = val;
        }
    }
}
