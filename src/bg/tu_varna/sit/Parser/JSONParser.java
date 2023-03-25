package bg.tu_varna.sit.Parser;


import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public abstract class JSONParser  implements  JSONParserInterface{
    public Map<String, Object> parseObject(String jsonString) throws JSONException {
        Map<String, Object> map = new LinkedHashMap<>();
        int i = 0;
        char[] chars = jsonString.toCharArray();
        int length = chars.length;

        while (i < length && Character.isWhitespace(chars[i])) {
            i++;
        }

        if (i == length) {
            throw new JSONException("Unexpected end of input");
        }

        if (chars[i] != '{') {
            throw new JSONException("Expected '{' at start of input");
        }

        i++;

        while (i < length && chars[i] != '}') {
            while (i < length && Character.isWhitespace(chars[i])) {
                i++;
            }

            String key = parseString(chars, i);
            i += key.length()+2;

            while (i < length && Character.isWhitespace(chars[i])) {
                i++;
            }

            if (i == length) {
                throw new JSONException("Unexpected end of input");
            }

            if (chars[i] != ':') {
                throw new JSONException("Expected ':' after key");
            }

            i++;

            while (i < length && Character.isWhitespace(chars[i])) {
                i++;
            }

            if (i == length) {
                throw new JSONException("Unexpected end of input");
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
                i += String.valueOf(value).length();//ako e с toString ще създава винаги обекти
            } else if (chars[i] == 't' || chars[i] == 'f' || chars[i] == 'n') {
                value = parseBooleanOrNull(chars, i);
                i += String.valueOf(value).length();
            } else {
                throw new JSONException("Unexpected character at position " + i);
            }

            map.put(key, value);

            while (i < length && Character.isWhitespace(chars[i])) {
                i++;
            }

            if (i == length) {
                throw new JSONException("Unexpected end of input");
            }

            if (chars[i] == ',') {
                i++;
                while (i < length && Character.isWhitespace(chars[i])) {
                    i++;
                }
                if (i == length) {
                    throw new JSONException("Unexpected end of input");
                }
            } else if (chars[i] != '}') {
                throw new JSONException("Expected ',' or '}' after value");
            }
        }

        return map;
    }

    private List<Object> parseArray(String jsonString) throws JSONException{
        List<Object> list = new ArrayList<>();
        int i = 0;
        char[] chars = jsonString.toCharArray();
        int length = chars.length;

        while (i < length && Character.isWhitespace(chars[i])) {
            i++;
        }

        if (i == length) {
            throw new JSONException("Unexpected end of input");
        }

        if (chars[i] != '[') {
            throw new JSONException("Expected '[' at start of input");
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
            } else if (chars[i] == 't' || chars[i] == 'f' || chars[i] == 'n') {
                element = parseBooleanOrNull(chars, i);
                i += String.valueOf(element).length();
            } else {
                throw new JSONException("Unexpected character at position " + i);
            }

            list.add(element);

            while (i < length && Character.isWhitespace(chars[i])) {
                i++;
            }

            if (i == length) {
                throw new JSONException("Unexpected end of input");
            }

            if (chars[i] == ',') {
                i++;
                while (i < length && Character.isWhitespace(chars[i])) {
                    i++;
                }
                if (i == length) {
                    throw new JSONException("Unexpected end of input");
                }
            } else if (chars[i] != ']') {
                throw new JSONException("Expected ',' or ']' after value");
            }
        }

        return list;
    }
    private String parseString(char[] chars, int start) throws JSONException {
        StringBuilder sb = new StringBuilder();
        int i = start + 1;
        char c;

        while (i < chars.length && (c = chars[i++]) != '"') {
            if (i == chars.length) {
                throw new JSONException("Unexpected end of input after '\\'");
            }

            sb.append(c);
        }

        if (i == chars.length && chars[i - 1] != '"') {
            throw new JSONException("Unexpected end of input");
        }

        return sb.toString();
    }

    private Object parseNumber(char[] chars, int start) throws JSONException {
        int i = start;
        char c;
        boolean isDouble = false;
        Object result;

        while (i < chars.length && ((c = chars[i]) == '-' || c == '.' || Character.isDigit(c))) {
            if (c == '.') {
                isDouble = true;
            }
            i++;
        }

        if ((isDouble && i - start == 1)) {
            throw new JSONException("Invalid number at position " + start);
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

    private Object parseBooleanOrNull(char[] chars, int start) throws JSONException {
        Boolean result;
        if (start + 4 <= chars.length && new String(chars, start, 4).equals("true")) {
            result =Boolean.valueOf("true");
            return result;
        }
        if (start + 5 <= chars.length && new String(chars, start, 5).equals("false")) {
            result =Boolean.valueOf("false");
            return result;
        }
        if (start + 4 <= chars.length && new String(chars, start, 4).equals("null")) {
            return "null";
        }
        throw new JSONException("Invalid boolean or null at position " + start);
    }

    private int getEndOfObjectIndex(String jsonString) throws JSONException {
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
        if (objectCount != 0) {
            throw new JSONException("Unexpected end of input");
        }
        return i;
    }
    private int getEndOfArrayIndex(String jsonString) throws JSONException {
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
        if (arrayCount != 0) {
            throw new JSONException("Unexpected end of input");
        }
        return i;
    }

    private boolean isWhitespace(char c) {
        return c == ' ' || c == '\t' || c == '\n' || c == '\r';
    }

    private boolean isStructuralChar(char c) {
        return c == '{' || c == '}' || c == '[' || c == ']' || c == ':' || c == ',';
    }
}
