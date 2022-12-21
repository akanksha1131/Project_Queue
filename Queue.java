import java.util.*;
public class Queue {
    Head head;
    int priority;//
    Queue(Head head){
        this.head=head;
    }
    static class Head{
        Node front;
        Node rear;
        int count;
        Head(){

            this.count=0;
            this.front=null;
            this.rear=null;
        }
    }
    static class Node{
        String owner_name;
        Node next;

        Node(String owner_name){
            this.owner_name=owner_name;
            this.next=null;
        }
    }
    public static Queue enqueue(Queue queue, String name, int priority){
        Node newnode=new Node(name);
        queue.head.count++;
        if(queue.head.front==null){  //empty queue
            queue.head.front=newnode;
            queue.head.rear=newnode;
        }
        else if(queue.head.front==queue.head.rear){ // only 1 node in queue
            queue.head.front.next=newnode;
            queue.head.rear=newnode;
        }
        else{
            Node last=queue.head.front;
            while(last.next!=null){
                last=last.next;
            }
            last.next=newnode;
            queue.head.rear=newnode;
        }
        return queue;
    }
    //      *****
    public static void dequeue(Queue queue_h,Queue queue_m,Queue queue_l){
        if(queue_h.head.front!=null){
            Node del=queue_h.head.front;
            queue_h.head.front=del.next;
            del.next=null;
            System.out.println("***** Car processed: ["+del.owner_name+"]");
            queue_h.head.count--;
        }
        else if (queue_h.head.front==null) {
            if(queue_m.head.front!=null){
                Node del=queue_m.head.front;
                queue_m.head.front=del.next;
                del.next=null;
                System.out.println("***** Car processed: ["+del.owner_name+"]");
                queue_m.head.count--;
            }
            else if ((queue_m.head.front==null)){
                Node del=queue_l.head.front;
                queue_l.head.front=del.next;
                del.next=null;
                System.out.println("***** Car processed: ["+del.owner_name+"]");
                queue_l.head.count--;
            }

        }

        else{
            System.out.println("***** Empty queue");
        }
    }
    public static void queue_head(Queue queue_h,Queue queue_m,Queue queue_l){
        if(queue_h.head.front!=null){
            Node del=queue_h.head.front;
            System.out.println("***** Car to be processed first: ["+del.owner_name+"]");
        }
        else if (queue_h.head.front==null) {
            if(queue_m.head.front!=null){
                Node del=queue_m.head.front;
                System.out.println("***** Car to be processed first : ["+del.owner_name+"]");
            }
            else if ((queue_m.head.front==null)){
                Node del=queue_l.head.front;
                System.out.println("***** Car to be processed first: ["+del.owner_name+"]");
            }

        }
        else{
            System.out.println("***** Empty queue");
        }
    }
    public static void queue_rear(Queue queue_h,Queue queue_m,Queue queue_l){
        if(queue_l.head.rear!=null && queue_l.head.front!=null){
            Node del=queue_l.head.rear;
            System.out.println("***** Car to be processed last : ["+del.owner_name+"]");
        }

        else if (queue_l.head.rear==null && queue_l.head.front==null) {
            if(queue_m.head.rear!=null && queue_m.head.front!=null ){
                Node del=queue_m.head.rear;
                System.out.println("***** Car to be processed last : ["+del.owner_name+"]");
            }

            else if (queue_m.head.rear==null && queue_m.head.front==null){
                Node del=queue_h.head.rear;
                System.out.println("***** Car to be processed last : ["+del.owner_name+"]");
            }

        }
        else{
            System.out.println("***** Empty queue");

        }
    }
    public static void display(Queue queue_h,Queue queue_m,Queue queue_l){
        System.out.println("---------------------------------------------------------");
        System.out.println("Total cars in toll station: "+(queue_h.head.count+queue_m.head.count+queue_l.head.count));

        System.out.println("---------------------------------------------------------");
        System.out.println("\nHigh priority queue --->");
        Node print_h=queue_h.head.front;
        while(print_h!=null){
            System.out.print("["+print_h.owner_name+"]");
            print_h=print_h.next;
        }
        System.out.println("\nMedium priority queue --->");
        Node print_m=queue_m.head.front;
        while(print_m!=null){
            System.out.print("["+print_m.owner_name+"]");
            print_m=print_m.next;
        }
        System.out.println("\nLow priority queue --->");
        Node print_l=queue_l.head.front;
        while(print_l!=null){
            System.out.print("["+print_l.owner_name+"]");
            print_l=print_l.next;
        }
        System.out.println("\n---------------------------------------------------------");
    }

    public static void main(String[] args){
        Scanner sc= new Scanner(System.in);
        Head h_head=new Head();//
        Head m_head=new Head();
        Head l_head=new Head();
        Queue high_priority_queue=new Queue(h_head); //
        Queue medium_priority_queue=new Queue(m_head);
        Queue low_priority_queue=new Queue(l_head);
        int ch,num; int priority;
        System.out.println("Welcome to Toll Plaza!");
        do{
            System.out.println("Enter choice: \n1. Enter your car in Toll station (Enqueue)\n" +
                    "2. Process a car the Toll station (Dequeue)\n" +
                    "3. See which car leaves the Toll station first\n" +
                    "4. See which car leaves the Toll station last\n" +
                    "5. Display queues\n"+
                    "6. Exit");

            ch=sc.nextInt();
            switch(ch){
                case 1:
                    System.out.println("How many cars do you want to enter ?");
                    num=sc.nextInt();
                    for(int i=0;i<num;i++){
                        System.out.println("Enter the following number for your car: \n" + "1 for Ambulances\n" + "2 for VIP vehicles \n" + "3 for Common vehicles");
                        priority=sc.nextInt();
                        System.out.println("Enter your name :");
                        String owner_name=sc.next();
                        if(priority==1){
                            owner_name+=" :H";


                            high_priority_queue.enqueue(high_priority_queue,owner_name,priority);
                        }
                        else if(priority==2){
                            owner_name+=" :M";


                            medium_priority_queue.enqueue(medium_priority_queue,owner_name,priority);
                        }
                        else if(priority==3){
                            owner_name+=" :L";


                            low_priority_queue.enqueue(low_priority_queue,owner_name,priority);
                        }
                    }


                    display(high_priority_queue,medium_priority_queue,low_priority_queue);

                    break;
                case 2:


                    dequeue(high_priority_queue,medium_priority_queue,low_priority_queue);


                    display(high_priority_queue,medium_priority_queue,low_priority_queue);
                    break;
                case 3:


                    display(high_priority_queue,medium_priority_queue,low_priority_queue);


                    queue_head(high_priority_queue,medium_priority_queue,low_priority_queue);
                    break;
                case 4:


                    display(high_priority_queue,medium_priority_queue,low_priority_queue);


                    queue_rear(high_priority_queue,medium_priority_queue,low_priority_queue);
                    break;
                case 5:


                    display(high_priority_queue,medium_priority_queue,low_priority_queue);
                    break;
                case 6:

                    System.out.println("Exiting.....");
                    break;
                default:
                    System.out.println("Invalid choice");
                    break;
            }
        }
        while(ch!=6);


    }
}