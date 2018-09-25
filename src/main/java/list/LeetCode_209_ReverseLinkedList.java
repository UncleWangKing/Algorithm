package list;

public class LeetCode_209_ReverseLinkedList {
    public static void main(String[] args) {
        //1->2->3->4->5->NULL
        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(3);
        ListNode listNode4 = new ListNode(4);
        ListNode listNode5 = new ListNode(5);

        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;
        listNode4.next = listNode5;

        reverseList(listNode1);

        while (null != listNode5){
            System.out.println(listNode5.val+",");
            listNode5 = listNode5.next;
        }
    }

    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
   }

    public static ListNode reverseList(ListNode head) {
        if(null == head)
            return head;
        ListNode preNode = null;
        ListNode nextNode = null;
        while(null != head.next){
            nextNode = head.next;
            head.next = preNode;
            preNode = head;
            head = nextNode;
        }
        head.next = preNode;
        return head;
    }
}
