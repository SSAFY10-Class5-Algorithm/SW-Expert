import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.StringTokenizer;
 
public class Solution {
 
    public static Set<String> set;
 
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
 
        int T = Integer.parseInt(br.readLine());
 
        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
 
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
 
            int num = N/4;
             
            set = new HashSet<String>();
 
            String str = br.readLine();
            for (int i = 0; i < num; i++) {
 
                String str1 = str;
                int idx = 0;
                 
                while(idx < N) {
                    set.add(str1.substring(idx, idx+num));
                    idx +=num;
                }
                 
                str = str1.substring(1,str1.length()) + str1.charAt(0);
            }
             
            int [] res = new int [set.size()];
            String [] arr = new String [set.size()];
            Iterator<String> iter = set.iterator();
             
            int idx = 0;
            while(iter.hasNext()) {
                arr[idx++] = iter.next();
            }
             
            for(int i = 0; i < arr.length; i++) {
                String s = arr[i];
                int tmp = 0;
                for(int j = 0; j < s.length(); j++) {
                    if(Character.isDigit(s.charAt(j))){//숫자일때 
                        tmp += (s.charAt(j) - '0') * Math.pow(16, s.length() - j - 1);
                    }
                    else {//문자일때
                        tmp += (s.charAt(j) - 55) * Math.pow(16, s.length() - j - 1);
                    }
                }
                res[i] = tmp;
            }
            Arrays.sort(res);
            sb.append("#").append(tc).append(" ").append(res[set.size()-K]).append("\n");
        }
        System.out.println(sb);
    }
}