package com.system.util;

import java.util.HashMap;
import java.util.Map;

public class MapUtil {
    public Map<String, Object> toMap(String code, String msg, Object data) {
        Map<String, Object> map = new HashMap<>();
        map.put("code", code);
        map.put("msg", msg);
        map.put("data", data);
        return map;
    }
}
