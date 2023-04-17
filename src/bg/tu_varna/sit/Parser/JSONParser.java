package bg.tu_varna.sit.Parser;


import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class JSONParser {
    public Map<String, Object> parseObject(String jsonString) {
        Map<String, Object> map = new LinkedHashMap<>();
        int i = 0;
        char[] chars = jsonString.toCharArray();
        int length = chars.length;

        while (i < length && isWhitespace(chars[i])) {
            i++;
        }
        i++;

        while (i < length && chars[i] != '}') {
            while (i < length && isWhitespace(chars[i])) {
                i++;
            }

            String key = parseString(chars, i);
            i += key.length()+2;

            while (i < length && isWhitespace(chars[i])) {
                i++;
            }

            i++;

            while (i < length && isWhitespace(chars[i])) {
                i++;
            }

            Object value;
            if (chars[i] == '{') {
                value = parseObject(jsonString.substring(i));
                i += getEndOfObjectIndex(jsonString.substring(i)) + 1;
            } else if (chars[i] == '[') {
                value = parseArray(jsonString.substring(i));
                i += getEndOfArrayIndex(jsonString.substring(i)) + 1;
            } else if (chars[i] == '"') {
                value = parseString(chars, i);
                i += ((String) value).length()+2;
            } else if (Character.isDigit(chars[i]) || chars[i] == '-') {
                value = parseNumber(chars, i);
                i += String.valueOf(value).length();
            } else {
                value = parseBooleanOrNull(chars, i);
                i += String.valueOf(value).length();
            }

            map.put(key, value);

            while (i < length && isWhitespace(chars[i])) {
                i++;
            }

            if (chars[i] == ',') {
                i++;
                while (i < length && isWhitespace(chars[i])) {
                    i++;
                }
            }
        }

        return map;
    }

    private List<Object> parseArray(String jsonString){
        List<Object> list = new ArrayList<>();
        int i = 0;
        char[] chars = jsonString.toCharArray();
        int length = chars.length;

        while (i < length && isWhitespace(chars[i])) {
            i++;
        }

        i++;

        while (i < length && chars[i] != ']') {
            Object element;
            if (chars[i] == '{') {
                element = parseObject(jsonString.substring(i));
                i += getEndOfObjectIndex(jsonString.substring(i)) + 1;
            } else if (chars[i] == '[') {
                element = parseArray(jsonString.substring(i));
                i += getEndOfArrayIndex(jsonString.substring(i)) + 1;
            } else if (chars[i] == '"') {
                element = parseString(chars, i);
                i += ((String) element).length()+2;
            } else if (Character.isDigit(chars[i]) || chars[i] == '-') {
                element = parseNumber(chars, i);
                i += String.valueOf(element).length();//ako e с toString ще създава винаги обекти
            } else {
                element = parseBooleanOrNull(chars, i);
                i += String.valueOf(element).length();
            }

            list.add(element);

            while (i < length && isWhitespace(chars[i])) {
                i++;
            }

            if (chars[i] == ',') {
                i++;
                while (i < length && isWhitespace(chars[i])) {
                    i++;
                }
            }
        }

        return list;
    }
    private String parseString(char[] chars, int start){
        StringBuilder sb = new StringBuilder();
        int i = start + 1;

        while (i < chars.length &&  chars[i] != '"') {
            sb.append(chars[i]);
            i++;
        }

        return sb.toString();
    }

    private Object parseNumber(char[] chars, int start){
        int i = start;
        boolean isDouble = false;
        Object result;

        while (i < chars.length && (chars[i] == '-' || chars[i] == '.' || Character.isDigit(chars[i]))) {
            if (chars[i] == '.') {
                isDouble = true;
            }
            i++;
        }
        String strNumber=new String(chars, start, i - start);
        if (isDouble){
            result= Double.parseDouble(strNumber);
        }
        else {
            result= Integer.parseInt(strNumber);
        }
        return result;

    }

    private Object parseBooleanOrNull(char[] chars, int start){
        if (start + 4 <= chars.length && new String(chars, start, 4).equals("true")) {
            return true;
        }
        if (start + 5 <= chars.length && new String(chars, start, 5).equals("false")) {
            return false;
        }
        return null;
    }

    private int getEndOfObjectIndex(String jsonString){
        int i = 0;
        char[] chars = jsonString.toCharArray();
        int length = chars.length;
        int objectCount = 1;
        while (i < length && objectCount > 0) {
            i++;
            if (chars[i] == '{') {
                objectCount++;
            } else if (chars[i] == '}') {
                objectCount--;
            }
        }
        return i;
    }
    private int getEndOfArrayIndex(String jsonString) {
        int i = 0;
        char[] chars = jsonString.toCharArray();
        int length = chars.length;
        int arrayCount = 1;
        while (i < length && arrayCount > 0) {
            i++;
            if (chars[i] == '[') {
                arrayCount++;
            } else if (chars[i] == ']') {
                arrayCount--;
            }
        }
        return i;
    }

    private boolean isWhitespace(char c) {
        return c == ' ' || c == '\t' || c == '\n' || c == '\r';
    }
}
