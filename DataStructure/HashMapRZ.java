
package DataStructure;
import java.util.*;

class K{

}
class V{

}

class HashNode<K, V>{
    public K getKey() {
        return key;
    }

    final K key;
    V val;
    HashNode<K, V> next;
    public HashNode(K key, V val) {
        this.key = key;
        this.val = val;
    }

    public K getK() {
        return key;
    }

    public V getV() {
        return val;
    }

    public void setValue (V value) {
        this.val = value;
    }

}
public class HashMapRZ {
    private HashNode<K, V>[] bucketList;
    private static float LOADFACTOR = 0.75f;
    private static final int DEFAULT_CAPACITY = 16;
    private int curCapacity;
    private int size;

    public HashMapRZ(){
        this(DEFAULT_CAPACITY, LOADFACTOR);
    }

    public HashMapRZ(int capacity, float loadFactor){
        if (capacity <= 0){
            try{
                throw new IndexOutOfBoundsException();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        this.curCapacity = capacity;
        this.bucketList = new HashNode[curCapacity];
        this.size = 0;
        this.LOADFACTOR = loadFactor;
    }

    public int getSize(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    //add
    public V put(K key, V val){
        int index = getIndex(key);
        HashNode<K, V> head = bucketList[index];
        while(head != null){
            if (head.key.equals(key)){
                V oldVal = head.getV();
                head.setValue(val);
                return oldVal;
            }
            head = head.next;
        }

        HashNode<K, V> newNode = new HashNode<>(key, val);
        newNode.next = head;
        bucketList[index] = newNode;
        size++;
        if(needRehashing()){
            System.out.println("Rehashing is needed!");
            rehashing();
        }
        return null;
    }


    //remove
    public V remove(K key){
        int index = getIndex(key);
        HashNode<K, V> head = bucketList[index];

        if(equalsKey(key, head.getK())){
            V removedVal = bucketList[index].getV();
            bucketList[index] = null;
            size--;
            return removedVal;
        }
        HashNode<K, V> pre = null;
        while(head != null){
            if(equalsKey(head.getK(), key)){
                pre.next = head.next;
                size--;
                return head.getV();
            }
            pre = head;
            head = head.next;
        }
        return null;
    }



    //get
    public V get(K key){
        int index = getIndex(key);
        HashNode<K, V> head = bucketList[index];
        while (head != null){
            if (equalsKey(head.getK(), key)){
                return head.getV();
            }
            head = head.next;
        }
        return null;
    }

    public boolean containsKey (K key) {
        int index = getIndex(key);
        HashNode<K, V> node = bucketList[index];
        while (node != null) {
            if (equalsKey(key, node.getK())) return true;
            node = node.next;
        }
        return false;
    }


    //util
    private boolean needRehashing() {
        float ratio = (size + 0.0f) / bucketList.length;
        return ratio >= LOADFACTOR;
    }

    public boolean rehashing(){
        HashNode<K, V>[] oldBucketList = this.bucketList;
        this.bucketList = new HashNode[size * 2];
        size = 0;
        for (HashNode<K, V> node: oldBucketList){
            while(node != null){
                put(node.getKey(), node.getV());
                node = node.next;
            }
        }
        return true;
    }

    public int getIndex(K key){
        int index = hash(key) % bucketList.length;

        return index;
    }

    public int hash(K key){
        if (key == null) return 0;
        return key.hashCode() & 0x7fffffff;
    }

    public boolean equalsKey(K k1, K k2){
        if (k1 == null && k2 == null){
            return true;
        }
        if (k1 == null || k2 == null){
            return false;
        }
        return k1.equals(k2);
    }


    public static void main(String[] args) {
        HashMapRZ test = new HashMapRZ();
        System.out.print(test.getSize());


    }

}
