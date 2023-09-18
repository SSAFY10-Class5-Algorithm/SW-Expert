import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class SW5432_02 {
    static int sum;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int testCase = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= testCase ; tc++) {
            sum=0;
            String[] stick = br.readLine().split("");
            splitStick(stick);
//            System.out.println(Arrays.toString(cutted));
            sb.append("#").append(tc).append(" ").append(sum).append("\n");
        }
        System.out.println(sb.toString());
    }

    static void splitStick(String[] stick){
        List<Integer> leftBrackets = new ArrayList<>();
        int count = 0;

        // Cutting Process
        for (int i = 0; i < stick.length; i++) {
            // left bracket cases
            if(stick[i].equals("(")){
                leftBrackets.add(count);
            }

            // right bracket cases
            else{
                // 1. before was left bracket -> razer
                if(i!=0 && stick[i-1].equals("(")){
                    leftBrackets.remove(leftBrackets.size()-1);
                    count++;
                }
                // 2. one stick
                else {
                    int temp = count-leftBrackets.get(leftBrackets.size()-1)+1;
                    leftBrackets.remove(leftBrackets.size()-1);
                    sum+=temp;
                }
            }
        }
    }
}
