package letcode;

/*
  @Auther: 邓小军
  @Date: 2019/11/23 17:35
  @Description:
 */
public class 环形链表 {

    // 简单难度---是否存在环
    public boolean hasCycle(ListNode head) {
        if (head == null) return false;

        ListNode slow = head;
        ListNode fast = head;
        while(true){
            slow = slow.next;
            if (slow == null) return false;
            fast = fast.next;
            if (fast == null) return false;
            fast = fast.next;
            if (fast == null) return false;

            if (slow == fast){
                return true;
            }
        }
    }

    // 复杂难度---找到环入口
    public ListNode detectCycle(ListNode head) {
        if (head == null) return null;

        ListNode slow = head;
        ListNode fast = head;
        int count = 0;
        while(true){
            slow = slow.next;
            if (slow == null) return null;
            fast = fast.next;
            if (fast == null) return null;
            if (count < 2){
                fast = fast.next;
                if (fast == null) return null;
                //只有两个元素形成环的情况
                if (fast == head) return head;
            }

            if (slow == fast){
                if (count == 2){
                    return slow;
                }
                count++;
                if (count == 2){
                    fast = head;
                }
            }
        }
    }

    class ListNode {
      int val;
      ListNode next;
      ListNode(int x) {
          val = x;
          next = null;
      }
    }
}
