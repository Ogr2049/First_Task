public class MyHashMapTest {
    public static void main(String[] args) {
        System.out.println("Testing MyHashMap implementation...");
        
        MyHashMap<String, Integer> map = new MyHashMap<>();
        

        map.put("one", 1);
        map.put("two", 2);
        map.put("three", 3);
        
        System.out.println("Get 'one': " + map.get("one"));
        System.out.println("Get 'two': " + map.get("two"));
        System.out.println("Get 'four': " + map.get("four"));
        

        map.put("one", 100);
        System.out.println("After update, get 'one': " + map.get("one"));
        

        System.out.println("Remove 'two': " + map.remove("two"));
        System.out.println("After remove, get 'two': " + map.get("two"));
        

        map.put(null, 0);
        System.out.println("Get null key: " + map.get(null));
        
        System.out.println("All tests completed!");
    }
}