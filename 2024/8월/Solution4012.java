package mon0803;

import java.io.*;
import java.util.*;

public class Solution4012 {
	static int N;
	static int[] ingred;
	static int[][] food;
	static int food_sum;

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		// N/2씩 재료 뽑기. ( A and B )
		// A 요리의 재료 a b의 시너지=시너지 [a][b] + 시너지 [b][a]
		// B 요리의 재료 c d = 시너지[c][d] + 시너지 [d][c]
		// abs(A 시너지-B 시너지)의 최소값
		BufferedReader br=new BufferedReader(new FileReader("C:\\SSAFY\\workspaces\\02-Java\\algo1\\src\\mon0803\\input4012.txt"));
		BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T=Integer.parseInt(br.readLine());
		
		for(int t=1;t<=T;t++) { // test case
			N=Integer.parseInt(br.readLine());
			//ingred=new int[N];
			
			food=new int[N][N];
			
			for(int i=0;i<N;i++) {
				food[i]=Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
				//System.out.println(Arrays.toString(food[i]));
			} // 시너지 정보 저장
			
			bw.write("#"+t+" 두 요리 간 시너지 차 최소값은 "+binaryCounting()+"입니다."+'\n');
		}
		bw.flush();
		
		br.close();
		bw.close();
	}
	
	static int binaryCounting() { // 바이너리 사용하시는 게
		food_sum=99999999;
		ingred=new int[N];
		for(int i=0;i<N;i++) {
			ingred[i]=i; // 재료 인덱스
		}
		
		for(int i=0;i<(1<<N);i++) { // 케이스
			List<Integer> in_A=new ArrayList<>(); // A 요리
			List<Integer> in_B=new ArrayList<>(); // B 요리
			//int[] in_B=new int[N/2];
			
			//System.out.println("i:"+Integer.toBinaryString(i));
			if(Integer.toBinaryString(i).replace("0","").length()!=N/2) continue; // 2:2 아니면 continue
			//System.out.println("1 갯수:"+Integer.toBinaryString(i).replace("0","").length());
			//int cnt=0;
			int food_tmp=0;
			
			for(int j=0;j<N;j++) { // 노중복 재료 추가
				if((i&(1<<j))!=0) {
					//System.out.println("j:"+Integer.toBinaryString(j));
					//tmp
					//System.out.print("A:"+ingred[j]+" ");
					in_A.add(ingred[j]);
				}else {
					//System.out.print("B:"+ingred[j]+" ");
					in_B.add(ingred[j]);
				}
			}
			//System.out.println("A:"+in_A+" B:"+in_B); // 케이스마다 완성
			
			int a_sum=0;
			int b_sum=0;
			
			for(int[] in_A // in_A 안에거 돌면서 food tmp += 하면 될거같으매냐ㅕㅗ햐ㅐㅣㅏㅎ드ㅐㅣㅀㄱ
			food_tmp=Math.abs((food[in_A.get(0)][in_A.get(1)]+food[in_A.get(1)][in_A.get(0)])
					-(food[in_B.get(0)][in_B.get(1)]+food[in_B.get(0)][in_B.get(1)])); // 시너지 차이
			
			// food_tmp=abs(시너지[a_1][a_2]+시너지[a_2][a_1]-시너지[b_1][b_2]+시너지[b_2][b_1])
			// if food_sum>food_tmp food_sum=food_tmp; sysout; // 최소 시너지 차로 갱신
			if(food_sum>food_tmp) {
				food_sum=food_tmp;
				//System.out.println("최소 시너지를 갱신합니다."+food_sum);
			}
			//System.out.println();
		}
		
		return food_sum;
	}

}
