package com.wanniwa.tank;

import java.io.IOException;
import java.util.Properties;

public class PropertyMgr {
	private static final Properties props = new Properties();

	static {
		try {
			props.load(PropertyMgr.class.getClassLoader().getResourceAsStream("config"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static Object get(String key) {
		return props.get(key);
	}

	//int getInt(key)
	//getString(key)

	public static void main(String[] args) {
		System.out.println(PropertyMgr.get("initTankCount"));

	}
}
