package fr.nationsglory.nabot.client;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class Utils {

    public static class Consts {

        public static final int stackTraceClassNameHash;
        public static final int stackTraceMethodNameHash;

        public static final Map<Integer, String> map = new HashMap<Integer, String>();

        static {
            StackTraceElement element = Thread.currentThread().getStackTrace()[1];
            stackTraceClassNameHash = element.getClassName().hashCode();
            stackTraceMethodNameHash = element.getMethodName().hashCode();
        }

        private static void put(String value, int key) {
            map.put(key, value);
        }

        private static String get(int key) {
            return map.get(key);
        }

        public static String add(String value, int padding) {
            char[] array = value.toCharArray();
            int hash = calculateHash(array);
            String fromMap = get(hash);
            if (fromMap != null)
                return fromMap;
            StackTraceElement[] elements = Thread.currentThread().getStackTrace();
            int classNameHash = elements[2].getClassName().hashCode();
            int methodNameHash = elements[2].getMethodName().hashCode();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < array.length; ++i) {
                switch (i % 8) {
                    case 0:
                        sb.append(padding ^ classNameHash ^ array[i]);
                        break;
                    case 1:
                        sb.append(methodNameHash ^ Consts.stackTraceMethodNameHash  ^ array[i]);
                        break;
                    case 2:
                        sb.append(classNameHash ^ Consts.stackTraceClassNameHash ^ array[i]);
                        break;
                    case 3:
                        sb.append(Consts.stackTraceMethodNameHash ^ padding ^ array[i]);
                        break;
                    case 4:
                        sb.append(Consts.stackTraceClassNameHash ^ methodNameHash ^ array[i]);
                        break;
                    case 5:
                        sb.append(Consts.stackTraceMethodNameHash ^ classNameHash ^ array[i]);
                        break;
                    case 6:
                        sb.append(classNameHash ^ methodNameHash ^ array[i]);
                        break;
                    case 7:
                        sb.append(methodNameHash ^ padding ^ array[i]);
                        break;
                }
            }
            String result = sb.toString();
            put(result, hash);
            return result;
        }

        private static int calculateHash(char[] array) {
            int result = 1;
            for (int i = 0; i < array.length; ++i) {
                char c = array[i];
                int x = c & 0xFF;
                int y = c | 0xFF;
                int z = c ^ 0xFF;
                int t1 = (x << 4) | (y >>> 4);
                int t2 = (z << 3) | (t1 >>> 6);
                x = (x & ((x << 2) | (x >>> 2))) & y;
                z = ((z >> 4) | (y << 2)) | z;
                y = ((y >>> 4) | (z << 6)) ^ y;
                t1 = t1 + y;
                t2 = ((x >>> 5) | (z << 2)) | t2;
                result ^= x ^ y ^ z ^ t1 ^ t2;
            }
            return result;
        }
    }

    private static final char[] hexArray = {}; //TODO

    public static String getMD5(File file) {
        return ""; //TODO
    }

    public static String getMachineID() {
        return ""; //TODO
    }

    private static String bytesToHex(byte[] byArray) {
        char[] cArray = new char[byArray.length * 2];
        for (int i = 0; i < byArray.length; ++i) {
            int n = byArray[i] & 255;
            cArray[i * 2] = hexArray[n >>> 4];
            cArray[i * 2 + 1] = hexArray[n & 15];
        }
        return new String(cArray);
    }


}
