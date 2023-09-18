import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
 
public class Solution {
     
    public static ArrayList<ArrayList<Integer>> graph;
    public static int N, M, max;
    public static boolean [] check;
     
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
         
        int T = Integer.parseInt(br.readLine());
         
        for(int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
             
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            max = Integer.MIN_VALUE;
            check = new boolean [N+1];
            graph = new ArrayList<>();
             
            for(int i = 0; i <= N; i++) {
                graph.add(new ArrayList<>());
            }
             
            for(int i = 0; i<M; i++) {
                st = new StringTokenizer(br.readLine());
                 
                int v1 = Integer.parseInt(st.nextToken());
                int v2 = Integer.parseInt(st.nextToken());
                 
                graph.get(v1).add(v2);
                graph.get(v2).add(v1);
            }
             
            for(int i = 1; i<=N; i++) {
                check[i] = true;                
                longest_dist(i, 1);
                check[i] = false;
            }
             
            sb.append("#").append(tc).append(" ").append(max).append("\n");
        }
        System.out.println(sb);
    }
     
    public static void longest_dist(int idx, int dist) {//하나씩 발견하면서 넘어감
        int cnt = 0;
         
        for(int i = 0; i < graph.get(idx).size(); i++) {
            if(!check[graph.get(idx).get(i)]) {
                 
                check[graph.get(idx).get(i)] = true;
                longest_dist(graph.get(idx).get(i), dist + 1);
                check[graph.get(idx).get(i)] = false;
                cnt++;
            }
        }
         
        if(cnt == 0) {
            max = Math.max(max, dist);
        }
    }
}