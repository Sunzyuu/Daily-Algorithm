package CodeThink.LinkNodeThink;

public class StringThink {
    public static void main(String[] args) {
        String abcdefg = reverseStr2("abcdefg", 2);
        System.out.println(abcdefg);

        strStr("aabaabaafa", "aabaaf");


    }


    public static void reverseString(char[] s) {
        int left = 0;
        int right = s.length - 1;

        while (left < right) {
            char temp = s[left];
            s[left] = s[right];
            s[right] = temp;
            right--;
            left++;
        }
    }

    public String reverseStr(String s, int k) {
        StringBuffer res = new StringBuffer();
        int length = s.length();
        int start = 0;
        while (start < length) {
            // 找到k处和2k处
            StringBuffer temp = new StringBuffer();
            // 与length进行判断，如果大于length了，那就将其置为length
            int firstK = Math.min(start + k, length);
            int secondK = Math.min(start + (2 * k), length);

            //无论start所处位置，至少会反转一次
            temp.append(s.substring(start, firstK));
            res.append(temp.reverse());

            // 如果firstK到secondK之间有元素，这些元素直接放入res里即可。
            if (firstK < secondK) { //此时剩余长度一定大于k。
                res.append(s.substring(firstK, secondK));
            }
            start += (2 * k);
        }
        return res.toString();
    }


    public static String reverseStr2(String s, int k) {
        StringBuffer result = new StringBuffer();
        int start = 0;
        int len = s.length();

        while (start < len) {
            StringBuffer temp = new StringBuffer();
            int firstK = Math.min(start + k, len);
            int secondK = Math.min(start + 2 * k, len);

            temp.append(s, start, firstK);
            result.append(temp.reverse());

            if (firstK < secondK) {
                result.append(s, firstK, secondK);
            }
            start += 2 * k;
        }
        return result.toString();
    }

    private static String reverse(String s, int i, int j) {
        char[] chars = s.toCharArray();
        int left = i;
        int right = j;

        while (left < right) {
            char temp = chars[left];
            chars[left] = chars[right];
            chars[right] = temp;
            right--;
            left++;
        }
        return chars.toString();
    }

    public String replaceSpace(String s) {
        StringBuffer result = new StringBuffer();

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ') {
                result.append("%20");
            } else {
                result.append(s.charAt(i));
            }
        }

        return result.toString();
    }

    /**
     * 不使用Java内置方法实现
     * <p>
     * 1.去除首尾以及中间多余空格
     * 2.反转整个字符串
     * 3.反转各个单词
     */
    public String reverseWords(String s) {
        // System.out.println("ReverseWords.reverseWords2() called with: s = [" + s + "]");
        // 1.去除首尾以及中间多余空格
        StringBuilder sb = removeSpace(s);
        // 2.反转整个字符串
        reverseString(sb, 0, sb.length() - 1);
        // 3.反转各个单词
        reverseEachWord(sb);
        return sb.toString();
    }

    private StringBuilder removeSpace(String s) {
        // System.out.println("ReverseWords.removeSpace() called with: s = [" + s + "]");
        int start = 0;
        int end = s.length() - 1;
        // 首先取出字符串首尾多余的空格
        while (s.charAt(start) == ' ') start++;
        while (s.charAt(end) == ' ') end--;
        StringBuilder sb = new StringBuilder();
        while (start <= end) {
            char c = s.charAt(start);
            // 删除字符串中间的连续空格
            if (c != ' ' || sb.charAt(sb.length() - 1) != ' ') {
                sb.append(c);
            }
            start++;
        }
        return sb;
    }

    /**
     * 反转字符串指定区间[start, end]的字符
     */
    public void reverseString(StringBuilder sb, int start, int end) {
        // System.out.println("ReverseWords.reverseString() called with: sb = [" + sb + "], start = [" + start + "], end = [" + end + "]");
        while (start < end) {
            char temp = sb.charAt(start);
            sb.setCharAt(start, sb.charAt(end));
            sb.setCharAt(end, temp);
            start++;
            end--;
        }
        // System.out.println("ReverseWords.reverseString returned: sb = [" + sb + "]");
    }

    private void reverseEachWord(StringBuilder sb) {
        int start = 0;
        int end = 1;
        int n = sb.length();
        while (start < n) {
            // start和end标记每个单词的首尾
            while (end < n && sb.charAt(end) != ' ') {
                end++;
            }
            reverseString(sb, start, end - 1);
            start = end + 1;
            end = start + 1;
        }
    }

    public String reverseLeftWords(String s, int n) {
        // 首先反转前n个字符
        s = reverseString(s, 0, n - 1);
        // 再反转n - s.length()- 1的字符
        s = reverseString(s, n, s.length() - 1);
        // 反转整个字符串
        s = reverseString(s, 0, s.length() - 1);

        return s;
    }


    private String reverseString(String s, int left, int right) {
        StringBuilder sb = new StringBuilder(s);
        while (left < right) {
            char temp = sb.charAt(left);
            sb.setCharAt(left, sb.charAt(right));
            sb.setCharAt(right, temp);
            left++;
            right--;
        }
        return sb.toString();
    }

    public static int strStr(String haystack, String needle) {
        if (needle.length() == 0) {
            return 0;
        }

        int[] next = new int[needle.length()];
        getNext(next, needle);

        int j = -1;
        for (int i = 0; i < haystack.length(); i++) {
            while (j >= 0 && haystack.charAt(i) != needle.charAt(j + 1)) {
                j = next[j];
            }

            if (haystack.charAt(i) == needle.charAt(j + 1)) {
                j++;
            }
            if (j == needle.length() - 1) {
                return (i - needle.length() + 1);
            }
        }

        return -1;
    }


    public static void getNext(int[] next, String s) {
        int j = -1;
        next[0] = j;

        for (int i = 1; i < s.length(); i++) {
            while (j >= 0 && s.charAt(i) != s.charAt(j + 1)) {
                j = next[j];
            }
            if (s.charAt(i) == s.charAt(j + 1)) {
                j++;
            }
            next[i] = j;
        }
    }

    // 将原字符串进行一次拼接，那么拼接后的字符串中间部分就是原字符串
    public static boolean repeatedSubstringPattern(String s) {
        String str = s + s;

        int[] next = new int[s.length()];
        getNext(next, s);
        int j = -1;
        for (int i = 1; i < str.length() - 1; i++) {
            while(j >= 0 && str.charAt(i) != s.charAt(j + 1)){
                j = next[j];
            }
            if(str.charAt(i) == s.charAt(j + 1)){
                j ++;
            }

            if(j == s.length() -1){
                return true;
            }
        }
        return false;
    }

    public static boolean repeatedSubstringPattern1(String s) {
//        String str = " " + s + s + " ";?
        return (s + s).indexOf(s, 1) != s.length();
    }

}
