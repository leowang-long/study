package com.leowang.tools.utils;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

public class JMap<K, V> extends HashMap<K, V> {

	/**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */
	private static final long serialVersionUID = 5406351984946192039L;
	// public JMap() {
	// super();
	// }
	// public JMap(HashMap<K, V> map) {
	// super(map);
	// }

	@SuppressWarnings("rawtypes")
	public Map getMap() {
		return this;
	}

	public String getStr(Object key) {
		V v = super.get(key);
		String dataStr = "";
		dataStr = JUtils.objectToString(v);
		return dataStr;
	}

	public String toJsionString() {
		return JUtils.objectToString(this);
	}

	public JSONObject toJsion() {

		JSONObject jsonObject = new JSONObject(this);

		return jsonObject;
	}
}
