package CodingNeetcodeLeetcode.ArraysHashing;

import java.util.Arrays;
import java.util.LinkedList;

public class EncodeAndDecodeString {
    public static void main(String[] args) {
        LinkedList<String> list = new LinkedList<>();
        list.add("Hello");
        list.add("World");
        System.out.println(encode(list));
        decode(encode(list));
    }

    private static void decode(String encode) {
        LinkedList<String> decoded = new LinkedList<>();
        int index = 0;
        int length = 0;
        int sepId= -1;
        while (index < encode.length()) {
            sepId = encode.indexOf("#", index);
            length = Integer.parseInt(encode.substring(index, sepId));
            decoded.add(encode.substring(sepId + 1, sepId +length + 1));
            index = sepId + length+1;
        }
        Arrays.stream(decoded.stream().toArray(String[]::new)).forEach(System.out::println);
    }

    private static String encode(LinkedList<String> list) {
        StringBuilder sb = new StringBuilder();
        for (String s : list) {
            sb.append(s.length());
            sb.append("#");
            sb.append(s);
        }
        return sb.toString();
    }
}
