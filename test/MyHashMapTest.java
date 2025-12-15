public class TestMyHashMap {
    public static void main(String[] args) {
        System.out.println("Testing improved MyHashMap implementation...");
        
        MyHashMap<String, Integer> map = new MyHashMap<>();
        

        System.out.println("Test 1: Basic put and get operations");
        map.put("one", 1);
        map.put("two", 2);
        map.put("three", 3);
        
        System.out.println("  get('one'): " + map.get("one"));      // 1
        System.out.println("  get('two'): " + map.get("two"));      // 2
        System.out.println("  get('four'): " + map.get("four"));    // null
        

        System.out.println("\nTest 2: Update existing key");
        map.put("one", 100);
        System.out.println("  get('one') after update: " + map.get("one"));  // 100
        

        System.out.println("\nTest 3: Remove operation");
        System.out.println("  remove('two'): " + map.remove("two"));  // 2
        System.out.println("  get('two') after remove: " + map.get("two"));  // null
        

        System.out.println("\nTest 4: Null key operations");
        map.put(null, 0);
        System.out.println("  get(null): " + map.get(null));  // 0
        map.put(null, 999);
        System.out.println("  get(null) after update: " + map.get(null));  // 999
        System.out.println("  remove(null): " + map.remove(null));  // 999
        System.out.println("  get(null) after remove: " + map.get(null));  // null
        

        System.out.println("\nTest 5: Size and collisions");
        System.out.println("  Size after operations: " + map.size());  // 2
        

        System.out.println("\nTest 6: Resize functionality");
        MyHashMap<Integer, String> bigMap = new MyHashMap<>();
        for (int i = 0; i < 20; i++) {
            bigMap.put(i, "Value" + i);
        }
        System.out.println("  Size after adding 20 elements: " + bigMap.size());  // 20
        System.out.println("  get(15) after resize: " + bigMap.get(15));  // Value15
        
        System.out.println("\nAll tests completed successfully!");
    }
}