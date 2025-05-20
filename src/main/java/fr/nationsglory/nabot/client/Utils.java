package fr.nationsglory.nabot.client;

import fr.nationsglory.nabot.utils.Consts;

import java.io.File;
import java.io.FileInputStream;
import java.lang.invoke.ConstantCallSite;
import java.lang.invoke.MethodType;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
                        sb.append((char) ((padding ^ classNameHash) ^ array[i]));
                        break;
                    case 1:
                        sb.append((char) ((methodNameHash ^ Consts.stackTraceMethodNameHash) ^ array[i]));
                        break;
                    case 2:
                        sb.append((char) ((classNameHash ^ Consts.stackTraceClassNameHash) ^ array[i]));
                        break;
                    case 3:
                        sb.append((char) ((Consts.stackTraceMethodNameHash ^ padding) ^ array[i]));
                        break;
                    case 4:
                        sb.append((char) ((Consts.stackTraceClassNameHash ^ methodNameHash) ^ array[i]));
                        break;
                    case 5:
                        sb.append((char) ((Consts.stackTraceMethodNameHash ^ classNameHash) ^ array[i]));
                        break;
                    case 6:
                        sb.append((char) ((classNameHash ^ methodNameHash) ^ array[i]));
                        break;
                    case 7:
                        sb.append((char) ((methodNameHash ^ padding) ^ array[i]));
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

    private static final char[] hexArray = "0123456789ABCDEF".toCharArray();

    public static String getMD5(File file) {
        if (file.isDirectory() || !file.exists())
            return "0";
        StringBuffer buffer = new StringBuffer();
        try {
            FileInputStream inputStream = new FileInputStream(file);
            MessageDigest digest = MessageDigest.getInstance("md5");
            byte[] array = new byte[1024];
            int len = inputStream.read(array);
            while (len != -1) {
                digest.update(array, 0, len);
                len = inputStream.read(array);
            }
            byte[] bytes = digest.digest();
            for (byte byt : bytes)
                buffer.append(Integer.toString((byt & 0xff) + 0x100, 16).substring(1));
            inputStream.close();
        } catch (NoSuchAlgorithmException | java.io.IOException ignored) {}
        return buffer.toString();
    }

    public static String getMachineID() {
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            StringBuilder sb = new StringBuilder();
            sb.append(System.getProperty("os.name"));
            sb.append(System.getProperty("os.arch"));
            sb.append(System.getProperty("os.version"));
            sb.append(System.getenv("PROCESSOR_IDENTIFIER"));
            sb.append(System.getenv("PROCESSOR_ARCHITECTURE"));
            sb.append(System.getenv("PROCESSOR_ARCHITEW6432"));
            sb.append(System.getenv("NUMBER_OF_PROCESSORS"));
            sb.append(System.getenv("PROCESSOR_REVISION"));
            sb.append(System.getenv("COMPUTERNAME"));
            return Utils.bytesToHex(digest.digest(sb.toString().getBytes()));
        } catch (NoSuchAlgorithmException error) {

        }
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
