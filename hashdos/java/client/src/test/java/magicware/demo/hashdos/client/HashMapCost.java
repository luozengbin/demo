package magicware.demo.hashdos.client;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class HashMapCost {

	//@Test
	public void testNormalKeys() {
		System.out.println(">>> testNormalKeys");
		doHashTest(DataGeneration.createNormalKeys("X", 65536));
		System.out.println("<<< testNormalKeys");
	}

	//@Test
	public void testCrossKeys() {
		System.out.println(">>> testCrossKeys");
		doHashTest(DataGeneration.createCrossKeys(4));
		System.out.println("<<< testCrossKeys");
	}

	@Test
	public void testMixKeysCollision() {
		System.out.println(">>> testMixKeysCollision");
		doHashTest(DataGeneration.createMixKeys(14, true));
		System.out.println(">>> testMixKeysCollision");
	}

	private void doHashTest(String[] keys) {
		
		System.out.println("[key count] : " + keys.length);

		// create time
		long start = System.currentTimeMillis();

		Map<String, String> map = new HashMap<String, String>();

		for (String key : keys) {
			map.put(key, "hash test");
		}

		long end = System.currentTimeMillis();

		System.out.println("[Hashmap creating] : " + ((end - start) / 1000.0) + " seconds");

		// read time
		start = System.currentTimeMillis();

		for (String key : keys) {
			String value = map.get(key);
			//System.out.println(key + " --> " + value);
		}
		end = System.currentTimeMillis();

		System.out.println("[Hashmap reading] : " + ((end - start) / 1000.0) + " seconds");
	}

}
