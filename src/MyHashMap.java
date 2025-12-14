package com.company;

public class MyHashMap<K, V> {
    private static final int INITIAL_SIZE = 16;
    private Node<K, V>[] bucketsArray;

    @SuppressWarnings("unchecked")
    public MyHashMap() {
        bucketsArray = new Node[INITIAL_SIZE];
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
        int myIndex = calculateIndex(myKeyData);
        Node<K, V> currentNode = bucketsArray[myIndex];

        while (currentNode != null) {
            if (myKeyData.equals(currentNode.myKeyData)) {
                V oldMyValueData = currentNode.myValueData;
                currentNode.myValueData = myValueData;
                return oldMyValueData;
            }
            currentNode = currentNode.nextNode;
        }

        bucketsArray[myIndex] = new Node<>(myKeyData, myValueData, bucketsArray[myIndex]);
        return null;
    }

    public V get(K myKeyData) {
        int myIndex = calculateIndex(myKeyData);
        Node<K, V> currentNode = bucketsArray[myIndex];

        while (currentNode != null) {
            if (myKeyData.equals(currentNode.myKeyData)) {
                return currentNode.myValueData;
            }
            currentNode = currentNode.nextNode;
        }
        return null;
    }

    public V remove(K myKeyData) {
        int myIndex = calculateIndex(myKeyData);
        Node<K, V> currentNode = bucketsArray[myIndex];
        Node<K, V> previous_Node = null;

        while (currentNode != null) {
            if (myKeyData.equals(currentNode.myKeyData)) {
                if (previous_Node == null) {
                    bucketsArray[myIndex] = currentNode.nextNode;
                } else {
                    previous_Node.nextNode = currentNode.nextNode;
                }
                return currentNode.myValueData;
            }
            previous_Node = currentNode;
            currentNode = currentNode.nextNode;
        }
        return null;
    }

    private int calculateIndex(K myKeyData) {
        return myKeyData == null ? 0 : Math.abs(myKeyData.hashCode() % INITIAL_SIZE);
    }
}