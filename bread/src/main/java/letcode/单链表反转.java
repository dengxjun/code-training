package letcode;

/**
 * @Auther: 邓小军
 * @Date: 2019/11/28 10:58
 * @Description:
 */
public class 单链表反转 {
    public static Node reverseLink(Node head){

        Node newNode = null;

        newNode = reverse(newNode,head);

        while (newNode != null){
            System.out.print(newNode.getValue()+"\t");
            newNode = newNode.getNext();
        }

        return null;
    }

    public static Node reverse(Node last, Node singleLink){
        Node nextLink = singleLink.getNext();
        singleLink.setNext(last);

        if (nextLink == null) return singleLink;

        return reverse(singleLink, nextLink);
    }

    public static void main(String[] args) {
        Node head = new Node(0,null);
        Node node_1 = new Node(1,null);
        Node node_2 = new Node(2,null);
        Node node_3 = new Node(3,null);
        Node node_4 = new Node(4,null);
        Node node_5 = new Node(5,null);

        head.setNext(node_1).setNext(node_2).setNext(node_3).setNext(node_4).setNext(node_5);

        reverseLink(head);
    }
}

class Node{
    int value;
    Node next;

    public Node(int value, Node next) {
        this.value = value;
        this.next = next;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
        return;
    }

    public Node getNext() {
        return next;
    }

    public Node setNext(Node next) {
        this.next = next;
        return next;
    }
}
