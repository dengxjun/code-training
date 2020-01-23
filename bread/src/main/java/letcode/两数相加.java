package letcode;

/**
 * Created by Administrator on 2019/12/1.
 */
public class 两数相加 {

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int flag = 0;
        ListNode tmp = new ListNode(0);
        ListNode head = tmp;
        while (l1 != null || l2 != null) {
            int sum = 0;
            if (l1 == null) {
                sum = l2.val + flag;
                l2 = l2.next;
            } else if (l2 == null) {
                sum = l1.val + flag;
                l1 = l1.next;
            } else {
                sum = l1.val + l2.val + flag;
                l1 = l1.next;
                l2 = l2.next;
            }

            int v = 0;
            if (sum > 9) {
                flag = 1;
                v = sum - 10;
            } else {
                flag = 0;
                v = sum;
            }

            tmp.next = new ListNode(v);
            tmp = tmp.next;

        }
        if (flag == 1) {
            tmp.next = new ListNode(1);
        }
        return head.next;
    }

    public static ListNode getList(String nums){
        char[] arr = nums.toCharArray();
        ListNode tmp = null;
        for (int j = 0; j < arr.length; j++) {
            ListNode node = new ListNode(arr[j] - '0');
            if (tmp != null){
                node.next = tmp;
            }
            tmp = node;
        }

        return tmp;
    }

    public static void main(String[] args) {
        addTwoNumbers(getList("9"),getList("199999999999999999999999999999999999999999")).scan();

//        System.out.println( 9999999991l + 9);

    }
}

class ListNode {
     int val;
     ListNode next;
     ListNode(int x) { val = x; }

    public void scan(){
        System.out.print(val + "|");
        while (next != null){
            System.out.print(next.val + "|");
            next = next.next;
        }
    }
}
