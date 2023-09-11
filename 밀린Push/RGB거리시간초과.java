import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main1149_0829 {
	static int[][] colors;
	static int total_cost, N;
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));

		N=Integer.parseInt(br.readLine()); // 집 수들

		total_cost=Integer.MAX_VALUE; // 최종 합산
		colors=new int[N][3];;
		for(int n=0;n<N;n++) {
			colors[n]=Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		}
		rec(0, 0, 0);
		rec(0, 1, 0);
		rec(0, 2, 0);
		bw.write(total_cost+"\n");
		bw.flush();
		bw.close();
		br.close();
	}
	static void rec(int cnt, int cidx, int cost) {
		//System.out.println("rec"+cnt+","+cidx+","+cost+"");
		// System.out.println("N:"+N);
		if(cnt==N) {
			//System.out.println("코드가 계싼한 금액:"+cost);
			if(total_cost>cost) {
				total_cost=cost;
			}
			return;
		}
		switch(cidx) {
		case 0:
			// 다음 색상으로 cidx를 택하고 cost에 합산.
			rec(cnt+1, 1, cost+colors[cnt][1]); 
			rec(cnt+1, 2, cost+colors[cnt][2]);
			break;
		case 1:
			rec(cnt+1, 0, cost+colors[cnt][0]);
			rec(cnt+1, 2, cost+colors[cnt][2]);
			break;
		case 2:
			rec(cnt+1, 0, cost+colors[cnt][0]);
			rec(cnt+1, 1, cost+colors[cnt][1]);
			break;
		}
	}

}