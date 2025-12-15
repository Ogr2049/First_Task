public class TestMyHashMap {
    public static void main(String[] args) {
        System.out.println("Testing MyHashMap implementation...");
        
        MyHashMap<String, Integer> map = new MyHashMap<>();
        
        map.put("one", 1);
        map.put("two", 2);
        map.put("three", 3);
        
        System.out.println(map.get("one"));
        System.out.println(map.get("two"));
        System.out.println(map.get("four"));
        
        map.put("one", 100);
        System.out.println(map.get("one"));
        
        System.out.println(map.remove("two"));
        System.out.println(map.get("two"));
        
        map.put(null, 0);
        System.out.println(map.get(null));
        
        map.put(null, 999);
        System.out.println(map.get(null));
        
        System.out.println(map.remove(null));
        System.out.println(map.get(null));
        
        System.out.println("Size: " + map.size());
        
        MyHashMap<Integer, String> bigMap = new MyHashMap<>();
        for (int i = 0; i < 20; i++) {
            bigMap.put(i, "Value" + i);
        }
        
        System.out.println("Big map size: " + bigMap.size());
        System.out.println("Big map get 15: " + bigMap.get(15));
        
        System.out.println("All tests completed!");
    }
}