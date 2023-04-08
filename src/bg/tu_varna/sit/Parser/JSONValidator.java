package bg.tu_varna.sit.Parser;

import java.util.HashSet;
import java.util.Set;

public class JSONValidator {
    private static boolean validator;
    private static int level;
    private static boolean hadRootElement;
    private static int line;
    private static Set<String> keys=new HashSet<>();
    private static boolean isKey;
    //инициализира началото за да не дефинирам на ново различните флагове и полета които ползвам
    public static void initialize(){
        validator=true;
        level=0;
        hadRootElement=false;
        line=1;
        keys.clear();
    }
    public static boolean isValidator() {
        return validator;
    }

    public static void validateObject(String jsonString) throws JSONException {
        int i = 0;
        char[] chars = jsonString.toCharArray();
        int length = chars.length;

        while (i < length && isWhitespace(chars[i])) {
            if (chars[i]=='\n')
                line++;
            i++;
        }

        if (i == length) {
            validator=false;
            throw new JSONException("Unexpected end of input at line: "+line);
        }

        if (chars[i] != '{') {
            validator=false;
            throw new JSONException("Expected '{' at start of input at line: "+line);
        }
        else if (level==1 && hadRootElement){
            validator=false;
            throw new JSONException("Multiple JSON root elements "+line);
        }

        level++;

        i++;

        while (i < length && chars[i] != '}') {
            isKey=true;
            while (i < length && isWhitespace(chars[i])) {
                if (chars[i]=='\n')
                    line++;
                i++;
            }

            i = validateString(chars, i);

            while (i < length && isWhitespace(chars[i])) {
                if (chars[i]=='\n')
                    line++;
                i++;

            }

            if (i == length) {
                validator=false;
                throw new JSONException("Unexpected end of input at line: "+line);
            }

            if (chars[i] != ':') {
                validator=false;
                throw new JSONException("Expected ':' after key at line: "+line);
            }

            i++;

            while (i < length && isWhitespace(chars[i])) {
                if (chars[i]=='\n')
                    line++;
                i++;
            }

            if (i == length) {
                validator=false;
                throw new JSONException("Unexpected end of input at line: "+line);
            }

            if (chars[i] == '{') {
                validateObject(jsonString.substring(i));
                i += getEndOfObjectIndex(jsonString.substring(i)) + 1;
            }
            else if (chars[i] == '[') {
                validateArray(jsonString.substring(i));
                i += getEndOfArrayIndex(jsonString.substring(i)) + 1;
            }
            else if (chars[i] == '"') {
                i = validateString(chars, i);
            }
            else if (Character.isDigit(chars[i]) || chars[i] == '-') {
                i = validateNumber(chars, i);
            }
            else if (chars[i] == 't' || chars[i] == 'f' || chars[i] == 'n') {
                i = validateBooleanOrNull(chars, i);
            }
            else {
                validator=false;
                throw new JSONException("Unexpected character at line "+line+" position " + i);
            }

            while (i < length && isWhitespace(chars[i])) {
                if (chars[i]=='\n')
                    line++;
                i++;
            }

            if (i == length) {
                validator=false;
                throw new JSONException("Unexpected end of input at line: "+line);
            }

            if (chars[i] == ',') {
                i++;
                while (i < length && isWhitespace(chars[i])) {
                    if (chars[i]=='\n')
                        line++;
                    i++;
                }
                if (i == length) {
                    validator=false;
                    throw new JSONException("Unexpected end of input at line: "+line);
                }
            } else if (chars[i] != '}') {
                validator=false;
                throw new JSONException("Expected ',' or '}' after value at line: "+line);
            }
        }
        if (chars[i]=='}'){
            level--;
            hadRootElement =true;
            keys.clear();
            if (i<length && level==0) {
                i++;
                while (i < length && isWhitespace(chars[i])) {
                    if (chars[i] == '\n')
                        line++;
                    i++;
                }
                if (i<length && chars[i] == '{') {
                    validator = false;
                    throw new JSONException("Multiple JSON root elements " + line);
                } else if (i<length ){
                    validator = false;
                    throw new JSONException("Unexpected symbol " + chars[i] + " at line: " + line);
                }
            }

        }

    }

    private static void validateArray(String jsonString) throws JSONException{
        int i = 0;
        char[] chars = jsonString.toCharArray();
        int length = chars.length;

        while (i < length && isWhitespace(chars[i])) {
            if (chars[i]=='\n')
                line++;
            i++;
        }

        if (i == length) {
            validator=false;
            throw new JSONException("Unexpected end of input at line: "+line);
        }

        if (chars[i] != '[') {
            validator=false;
            throw new JSONException("Expected '[' at start of input at line: "+line);
        }

        i++;

        while (i < length && chars[i] != ']') {
            if (chars[i] == '{') {
                validateObject(jsonString.substring(i));
                i += getEndOfObjectIndex(jsonString.substring(i)) + 1;
            }
            else if (chars[i] == '[') {
                validateArray(jsonString.substring(i));
                i += getEndOfArrayIndex(jsonString.substring(i)) + 1;
            }
            else if (chars[i] == '"') {
                i = validateString(chars, i);
            }
            else if (Character.isDigit(chars[i]) || chars[i] == '-') {
                i = validateNumber(chars, i);
            }
            else if (chars[i] == 't' || chars[i] == 'f' || chars[i] == 'n') {
                i = validateBooleanOrNull(chars, i);
            }
            else {
                validator=false;
                throw new JSONException("Unexpected character at line "+line+" position " + i);
            }

            while (i < length && isWhitespace(chars[i])) {
                if (chars[i]=='\n')
                    line++;
                i++;
            }

            if (i == length) {
                validator=false;
                throw new JSONException("Unexpected end of input at line: "+line);
            }

            if (chars[i] == ',') {
                i++;
                while (i < length && Character.isWhitespace(chars[i])) {
                    if (chars[i]=='\n')
                        line++;
                    i++;
                }
                if (i == length) {
                    validator=false;
                    throw new JSONException("Unexpected end of input at line: "+line);
                }
            } else if (chars[i] != ']') {
                validator=false;
                throw new JSONException("Expected ',' or ']' after value at line: "+line);
            }
        }
    }
    private static int validateString(char[] chars, int start) throws JSONException {
        int i = start + 1;
        StringBuilder sb=new StringBuilder();

        while (i < chars.length && chars[i] != '"') {
            if (chars[i]=='\n'){
                validator=false;
                throw new JSONException("Unexpected new line at line: "+line);
            }
            sb.append(chars[i]);
            i++;

        }


        if (i == chars.length ) {
            validator=false;
            throw new JSONException("Unexpected end of input at line: "+line);
        }
        if (chars[i] != '"'){
            validator=false;
            throw new JSONException("Missing \" at: "+line);
        }
        if (isKey){
            if (keys.contains(sb.toString())){
                validator=false;
                throw new JSONException("Duplicated key value at line: "+line);
            }
            else
                keys.add(sb.toString());
            isKey=false;
        }

        return i+1;
    }

    private static int validateNumber(char[] chars, int start) throws JSONException {
        int i = start;
        char c;
        boolean isDouble = false;


        while (i < chars.length && ((c = chars[i]) == '-' || c == '.' || Character.isDigit(c))) {
            if (c=='.' && isDouble)
            {
                validator=false;
                throw new JSONException("Unexpected '.' at line: "+line);
            }
            if (c == '.') {
                isDouble = true;
            }

            i++;
        }

        if ((isDouble && i - start == 1)) {
            validator=false;
            throw new JSONException("Invalid number at line "+line+" position " + start);
        }

        return i;

    }

    private static int validateBooleanOrNull(char[] chars, int start) throws JSONException {
        if (start + 4 <= chars.length && new String(chars, start, 4).equals("true")) {
            return start+4;
        }
        if (start + 5 <= chars.length && new String(chars, start, 5).equals("false")) {
            return start+5;
        }
        if (start + 4 <= chars.length && new String(chars, start, 4).equals("null")) {
            return start+4;
        }
        throw new JSONException("Invalid boolean or null at line "+line+" position " + start);
    }

    private static int getEndOfObjectIndex(String jsonString) throws JSONException {
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
            validator=false;
            throw new JSONException("Unexpected end of input at line: "+line);
        }
        return i;
    }
    private static int getEndOfArrayIndex(String jsonString) throws JSONException {
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
            validator=false;
            throw new JSONException("Unexpected end of input at line: "+line);
        }
        return i;
    }

    private static boolean isWhitespace(char c) {
        return c == ' ' || c == '\t' || c == '\n' || c == '\r';
    }
}

