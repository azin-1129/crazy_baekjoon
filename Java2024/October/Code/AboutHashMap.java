package Code;
import java.util.*;

public class AboutHashMap {
    public static void main(String[] args) {
        HashMap<Integer, String> map=new HashMap<>(10, 0.8f);

        map.put(1, "One");
        map.put(2, "Two");
        map.put(3, "Three");

        // System.out.println(map);

        // map.remove(1);
        // System.out.println(map);

        System.out.println(map.get(1));
        System.out.println(map.containsKey(2));
        System.out.println(map.containsValue("Tree"));
        System.out.println(map.containsValue("Three"));
    }
}
