import java.util.Objects;

public class MyHashMap<K, V> {
    private static final int INITIAL_SIZE = 16;
    private static final float LOAD_FACTOR = 0.75f;
    private Node<K, V>[] bucketsArray;
    private int size;
    private int threshold;
    
    @SuppressWarnings("unchecked")
    public MyHashMap() {
        bucketsArray = new Node[INITIAL_SIZE];
        size = 0;
        threshold = (int)(INITIAL_SIZE * LOAD_FACTOR);
    }
    
    private static class Node<K, V> {
        final K myKeyData;
        V myValueData;
        Node<K, V> nextNode;
        
        Node(K myKeyData, V myValueData, Node<K, V> nextNode) {
            this.myKeyData = myKeyData;
            this.myValueData = myValueData;
            this.nextNode = nextNode;
        }
    }
    
    public V put(K myKeyData, V myValueData) {
        if (myKeyData == null) {
            return putForNullKey(myValueData);
        }
        
        int myIndex = calculateIndex(myKeyData);
        Node<K, V> currentNode = bucketsArray[myIndex];
        
        while (currentNode != null) {
            if (Objects.equals(myKeyData, currentNode.myKeyData)) {
                V oldMyValueData = currentNode.myValueData;
                currentNode.myValueData = myValueData;
                return oldMyValueData;
            }
            currentNode = currentNode.nextNode;
        }
        
        bucketsArray[myIndex] = new Node<>(myKeyData, myValueData, bucketsArray[myIndex]);
        size++;
        
        if (size > threshold) {
            resize(bucketsArray.length * 2);
        }
        
        return null;
    }
    
    private V putForNullKey(V myValueData) {
        Node<K, V> currentNode = bucketsArray[0];
        
        while (currentNode != null) {
            if (currentNode.myKeyData == null) {
                V oldMyValueData = currentNode.myValueData;
                currentNode.myValueData = myValueData;
                return oldMyValueData;
            }
            currentNode = currentNode.nextNode;
        }
        
        bucketsArray[0] = new Node<>(null, myValueData, bucketsArray[0]);
        size++;
        
        if (size > threshold) {
            resize(bucketsArray.length * 2);
        }
        
        return null;
    }
    
    public V get(K myKeyData) {
        if (myKeyData == null) {
            return getForNullKey();
        }
        
        int myIndex = calculateIndex(myKeyData);
        Node<K, V> currentNode = bucketsArray[myIndex];
        
        while (currentNode != null) {
            if (Objects.equals(myKeyData, currentNode.myKeyData)) {
                return currentNode.myValueData;
            }
            currentNode = currentNode.nextNode;
        }
        return null;
    }
    
    private V getForNullKey() {
        Node<K, V> currentNode = bucketsArray[0];
        
        while (currentNode != null) {
            if (currentNode.myKeyData == null) {
                return currentNode.myValueData;
            }
            currentNode = currentNode.nextNode;
        }
        return null;
    }
    
    public V remove(K myKeyData) {
        if (myKeyData == null) {
            return removeForNullKey();
        }
        
        int myIndex = calculateIndex(myKeyData);
        Node<K, V> currentNode = bucketsArray[myIndex];
        Node<K, V> previousNode = null;
        
        while (currentNode != null) {
            if (Objects.equals(myKeyData, currentNode.myKeyData)) {
                if (previousNode == null) {
                    bucketsArray[myIndex] = currentNode.nextNode;
                } else {
                    previousNode.nextNode = currentNode.nextNode;
                }
                size--;
                return currentNode.myValueData;
            }
            previousNode = currentNode;
            currentNode = currentNode.nextNode;
        }
        return null;
    }
    
    private V removeForNullKey() {
        Node<K, V> currentNode = bucketsArray[0];
        Node<K, V> previousNode = null;
        
        while (currentNode != null) {
            if (currentNode.myKeyData == null) {
                if (previousNode == null) {
                    bucketsArray[0] = currentNode.nextNode;
                } else {
                    previousNode.nextNode = currentNode.nextNode;
                }
                size--;
                return currentNode.myValueData;
            }
            previousNode = currentNode;
            currentNode = currentNode.nextNode;
        }
        return null;
    }
    
    private int calculateIndex(K myKeyData) {
        return Math.abs(Objects.hashCode(myKeyData) % bucketsArray.length);
    }
    
    @SuppressWarnings("unchecked")
    private void resize(int newCapacity) {
        Node<K, V>[] oldBucketsArray = bucketsArray;
        bucketsArray = new Node[newCapacity];
        threshold = (int)(newCapacity * LOAD_FACTOR);
        
        for (Node<K, V> currentNode : oldBucketsArray) {
            while (currentNode != null) {
                Node<K, V> nextNode = currentNode.nextNode;
                int newIndex = calculateIndex(currentNode.myKeyData);
                currentNode.nextNode = bucketsArray[newIndex];
                bucketsArray[newIndex] = currentNode;
                currentNode = nextNode;
            }
        }
    }
    
    public int size() {
        return size;
    }
}