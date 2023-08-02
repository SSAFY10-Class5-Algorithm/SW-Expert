import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
class Solution
{
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(bf.readLine());

		for (int tc = 1; tc <= T; tc++) {
			Stack<Integer> left = new Stack<>();

			String[] fife = bf.readLine().split("");
			int sum = 0;
			int count = 0;
			for (int i = 0; i < fife.length; i++) {

				if (fife[i].equals("(") && fife[i + 1].equals(")")) { // 레이저의 인덱스 표기
					if (!left.isEmpty())
						count++;

					continue;
				}
				if (fife[i].equals("(")) {
					left.push(count);
					continue;
				}

				if (fife[i].equals(")") && !fife[i - 1].equals("(")) {
					int not = left.pop();
					sum += (count + 1 - not);
					if (left.isEmpty())
						count = 0;
				}
			}

			sb.append("#" + tc + " " + sum).append('\n');
		}
		System.out.println(sb.toString());
	}
}