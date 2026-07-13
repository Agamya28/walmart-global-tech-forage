import java.util.*;
public class PowerOfTwoMaxHeap {
    private ArrayList<Integer> heap;
    private int childrenPerNode;

    public PowerOfTwoMaxHeap(int branchingFactorExponent){
        heap=new ArrayList<>();
        childrenPerNode=1<<branchingFactorExponent;
    }
    private int parent(int index){
        return (index-1)/childrenPerNode;
    }
    private void swap(int i,int j){
        int temp=heap.get(i);
        heap.set(i,heap.get(j));
        heap.set(j,temp);
    }
    public void insert(int node){
        heap.add(node);
        int currentIndex=heap.size()-1;
        while (currentIndex>0 && heap.get(currentIndex)>heap.get(parent(currentIndex))){
            int parentIndex=parent(currentIndex);
            swap(currentIndex,parentIndex);
            currentIndex=parentIndex;
        }
    }
    public int popMax(){
        if (heap.isEmpty()) throw new NoSuchElementException("Heap is Empty");

        int max=heap.get(0);
        int lastValue=heap.get(heap.size()-1);
        heap.set(0,lastValue);
        heap.remove(heap.size()-1);
        if (!heap.isEmpty()){
            bubbleDown();
        }
        return max;
    }
    private void bubbleDown(){
        int currentIndex=0;
        
        while(true){
        int largest=currentIndex;
        int firstChild=currentIndex*childrenPerNode+1;
        for (int child=0;child<childrenPerNode;child++){
            int childIndex=firstChild+child;
            if (childIndex<heap.size()){
                if (heap.get(childIndex) > heap.get(largest)){
                largest = childIndex;
            }
            }
        }
        
        if(largest==currentIndex) break;
        swap(currentIndex,largest);
        currentIndex=largest;
    }

    }
    public void printHeap() {
        System.out.println("Heap: " + heap);
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter branching factor exponent: ");
        int branchingFactorExponent = sc.nextInt();
        PowerOfTwoMaxHeap heap = new PowerOfTwoMaxHeap(branchingFactorExponent);

    while (true) {
        System.out.println("\n1. Insert");
        System.out.println("2. Pop Max");
        System.out.println("3. Exit");
        System.out.print("Enter your choice: ");

        int choice = sc.nextInt();

        switch (choice) {
            case 1:
                System.out.print("Enter value to insert: ");
                int value = sc.nextInt();
                heap.insert(value);
                System.out.println("Inserted successfully.");
                heap.printHeap();
                break;

            case 2:
                try {
                    System.out.println("Maximum element: " + heap.popMax());
                    heap.printHeap();
                } catch (NoSuchElementException e) {
                    System.out.println(e.getMessage());
                }
                break;

            case 3:
                System.out.println("Exiting...");
                sc.close();
                return;

            default:
                System.out.println("Invalid choice!");
        }
    }
    }
}
