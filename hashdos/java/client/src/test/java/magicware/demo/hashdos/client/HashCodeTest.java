package magicware.demo.hashdos.client;

import java.util.HashMap;
import java.util.Map;

public class HashCodeTest {

	//@Test
	public void testNormalHash() throws Exception {
		System.out.println(">>> testNormalHash");
		Map<String, String> map = new HashMap<String, String>(); 
		map.put("item1", "value1");
		map.put("item2", "value2");
		for (String key : map.keySet()) {
			System.out.println(key + ":" + map.get(key) + " --> " + key.hashCode());
		}
	}
	
	//@Test
	public void testCollisionHash() throws Exception {
		System.out.println(">>> testCollisionHash");
		Map<String, String> map = new HashMap<String, String>(); 
		map.put("itemAa", "value1");
		map.put("itemBB", "value2");
		for (String key : map.keySet()) {
			System.out.println(key + ":" + map.get(key) + " --> " + key.hashCode());
		}
	}
}
