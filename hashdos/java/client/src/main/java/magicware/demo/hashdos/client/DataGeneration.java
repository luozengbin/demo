package magicware.demo.hashdos.client;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataGeneration {

	public static final String[] baseKeys = new String[] { "Aa", "BB" };
	public static final String[] fixKeys = new String[] { "XX", "YY", "ZZ", "AA", "CC", "DD", "EE", "FF", "GG"};

	public static String[] createCrossKeys(final int x) {
		String[] paramKeys = baseKeys;
		for (int i = 0; i < x; i++) {
			paramKeys = getNewKeys(paramKeys, paramKeys);
		}
		return paramKeys;
	}

	public static String[] createMixKeys(final int lenght, boolean apendFix) {

		List<String> result = new ArrayList<String>();

		String[] paramKeys = baseKeys;

		for (int i = 0; i <= (lenght - baseKeys.length); i++) {

			if (apendFix) {
				for (String paramKey : paramKeys) {
					for (String fixKey : fixKeys) {
						StringBuffer sb = new StringBuffer(paramKey);
						for (int j = 0; j < (lenght - paramKey.length() / 2); j++) {
							sb.append(fixKey);
						}
						result.add(sb.toString());
						
					}
					
				}
			}
			paramKeys = getNewKeys(paramKeys, baseKeys);
		}

		result.addAll(Arrays.asList(paramKeys));

		return result.toArray(new String[0]);
	}

	public static String[] getNewKeys(String[] xKey, String[] yKey) {

		String[] newKeys = new String[xKey.length * yKey.length];
		int idx = 0;
		for (int i = 0; i < xKey.length; i++) {
			for (int j = 0; j < yKey.length; j++) {
				newKeys[idx] = xKey[i] + yKey[j];
				idx++;
			}
		}
		return newKeys;
	}

	public static String[] createNormalKeys(String prefix, int x) {

		String[] newKeys = new String[x];

		for (int i = 0; i < x; i++) {
			newKeys[i] = new String(prefix + "-" + i);
		}
		return newKeys;
	}

	public static void createTestDataFile(String filename, String[] keys,
			String value) {

		FileWriter writer = null;
		try {
			writer = new FileWriter(new File(filename));

			for (String key : keys) {
				writer.write(key + "=" + value + "&");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {

			if (writer != null)
				try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}

	}

	public static void main(String[] args) {
		//createTestDataFile("CollisionData001.txt", createCrossKeys(4),"");
		//createTestDataFile("CollisionData002.txt", createMixKeys(16, false),"");
		createTestDataFile("CollisionData003.txt", createNormalKeys("XXXXXXXXXXXXXXXXXXXXXXXXXX", 65536),"");
		
	}
}
