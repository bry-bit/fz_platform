package com.system.util;

import net.sf.json.JSONSerializer;
import net.sf.json.xml.XMLSerializer;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

import java.util.Map;
public class XmlJsonUtils {
 
    /**
     * JSON(数组)字符串转换成XML字符串
     */
    public static String json2xml(String jsonString) {
        XMLSerializer xmlSerializer = new XMLSerializer();
        xmlSerializer.setTypeHintsEnabled(false); 
        String xml = xmlSerializer.write(JSONSerializer.toJSON(jsonString));
        xml = xml.replace("<o>", "").replace("</o>", "");
        xml = xml.replaceAll("\r\n", "").concat("\r\n");
        return xml;
    }
    /**
     * xml 转 json
     */
    public static String xml2json(String xmlString) throws JSONException {
       JSONObject jsonObject = XML.toJSONObject(xmlString);
        return jsonObject.toString(3);
    }
}