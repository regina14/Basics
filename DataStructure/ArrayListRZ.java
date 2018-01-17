package DataStructure;

import java.util.Arrays;

public class ArrayListRZ {

    private static final int DEFAULT_CAPACITY = 10;
    private Object[] data;
    private int capacity;
    private int size;

    //Constructor
    public ArrayListRZ(){
        this.data = new Object[DEFAULT_CAPACITY];
        capacity = DEFAULT_CAPACITY;
    }

    public ArrayListRZ(int capacity){
        this.data = new Object[capacity];
        this.capacity = capacity;
    }

    //Add method
    public void add(Object o){
        if (size == capacity){
            expand();
        }
        data[size++] = o;
    }

    public void add(int index, Object o){
        if (index < 0 || index >= size){
            throw new IndexOutOfBoundsException();
        }

        if (size == capacity){
            expand();
        }
        Object[] newData = new Object[capacity];
        System.arraycopy(data, 0, newData, 0, index);
        System.arraycopy(data, index, newData, index + 1, size - index);
        newData[index] = o;
        data = newData;
        size++;
    }

    //Remove method
    public void remove(int index){
        if (index < 0 || index >= size){
            throw new IndexOutOfBoundsException();
        }
        Object[] newData = new Object[capacity];
        System.arraycopy(data, 0, newData, 0, index);
        System.arraycopy(data, index + 1, newData, index, size - index);
        data = newData;
        size--;
    }

    public void remove(Object o){
        for(int i = 0; i < size; i++){
            if(data[i].equals(o)){
                remove(i);
                break;
            }
        }
    }


    //Set method
    public void set(int index, Object o){
        if (index < 0 || index >= size){
            throw new IndexOutOfBoundsException();
        }
        data[index] = o;
    }

    //Get method
    public Object get(int index) {
        if (index < 0 || index >= size){
            throw new IndexOutOfBoundsException();
        }
        return data[index];
    }

    //Contains method
    public boolean contains(Object o){
        for(int i = 0; i < size; i++){
            if(data[i].equals(o)){
                return true;
            }
        }
        return false;
    }

    //Empty method
    public boolean isEmpty(){
        return size == 0;
    }

    //index
    public int indexOf(Object o){
        for(int i = 0; i < size; i++){
            if(data[i].equals(o)){
                return i;
            }
        }
        return -1;
    }

    //getNewCapacity
    public int getNewCapacity(){
        return capacity = capacity + (capacity >> 1);
    }

    //getSize
    public int getSize(){
        return size;
    }

    //Clear method
    public void clear(){
        data = new Object[capacity];
        size = 0;
    }



    //Expand method
    public void expand(){
        Object[] newData = new Object[getNewCapacity()];
        System.arraycopy(data, 0, newData, 0, size);
        data = newData;
    }


    public static void main(String[] args) {
        ArrayListRZ test = new ArrayListRZ(5);

        //Add
        test.add(1);
        System.out.println(test.get(0));
        test.add(2);
        System.out.println(test.get(1));
        test.add(0, 3);
        System.out.println(test.get(0));

        for(int i = 0; i < 10; i ++){
            test.add(i);
        }
        System.out.println(test.getSize());
    }
}
