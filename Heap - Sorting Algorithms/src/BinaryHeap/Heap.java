package BinaryHeap;

import java.util.ArrayList;
import java.util.Arrays;

public class Heap {
    int[] heap = new int[1000000];
    int lastIndex = 0;

    private void maxHeapify(int[] arr, int index, int length) {
        int left = leftChild(index), right = rightChild(index), largest = index;
        if(left < length && arr[left] > arr[largest]) {
            largest = left;
        }
        if(right < length && arr[right] > arr[largest]) {
            largest = right;
        }

        if(largest != index) {
            swap(arr, largest, index);
            maxHeapify(arr,largest,length);
        }
    }

    private void heapifyUp(int[] arr, int index) {
        while(index > 0) {
            if(arr[parent(index)] <= arr[index]) {
                swap(arr,index,parent(index));
                index = parent(index);
            } else {
                break;
            }
        }
    }

    public void buildMaxHeap(int[] arr) {
        int length = arr.length;
        this.lastIndex = length;
        for (int i= (int)Math.floor(length/2.0)-1; i>=0; i--){
            maxHeapify(arr,i,length);
        }
        this.heap = arr;
    }

    public void maxHeapInsert(int key) {
        if(lastIndex >= heap.length) {
            System.out.println("Can not insert more elements!");
            return;
        }
        this.heap[this.lastIndex] = key;
        heapifyUp(this.heap,this.lastIndex);
        this.lastIndex++;
    }

    public int extractMax() {
        if(lastIndex == 0) {
            return -1;
        }
        int max = heap[0];
        heap[0] = heap[this.lastIndex - 1];
        this.lastIndex--;
        maxHeapify(heap,0,lastIndex);
        return max;
    }

    public int[] heapSort(int[] arr) {
        System.out.println("Array length is " + arr.length);
        long Start = System.nanoTime();
        buildMaxHeap(arr);
        for(int i=0;i<this.heap.length;i++) {
//            System.out.println(Arrays.toString(arr));
            int removed = extractMax();
            this.heap[this.lastIndex] = removed;
        }
        long duration  =  System.nanoTime() - Start;
        System.out.println("time taken = " + duration/1000 + " us");
        return arr;
    }

    public void printHeap() {
        for(int i=0;i<this.lastIndex;i++) {
            System.out.print(heap[i] + " ");
        }
        System.out.println();
    }

    private int parent(int i) {return (i - 1) / 2;}
    private int leftChild(int i) {return 2 * i + 1;}
    private int rightChild(int i) {return 2 * i + 2;}
    private void swap(int[] arr, int i, int j) {
        int x = arr[i];
        arr[i] = arr[j];
        arr[j] = x;
    }

}
