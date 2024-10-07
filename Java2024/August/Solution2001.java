package mon0803;

import java.io.*;
import java.util.Arrays;

public class Solution2001 {

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		
		BufferedReader br=new BufferedReader(new FileReader("C:\\SSAFY\\workspaces\\02-Java\\algo1\\src\\mon0803\\input2001.txt"));
		BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T=Integer.parseInt(br.readLine());
		
		for(int t=1;t<=T;t++) {
			String[] nm_tmp=br.readLine().split(" ");
			//System.out.println(Arrays.toString(nm_tmp));
			int N=Integer.parseInt(nm_tmp[0]); // 창문 크기
			int M=Integer.parseInt(nm_tmp[1]); // 파리채 크기
			//System.out.println(N+" "+M);
			int[][] window=new int[N][];
			int heat_sum=0; // 최대 파리 수
			
			for(int i=0;i<N;i++) { // 창문 정보 부여
				int[] win_tmp=Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
				window[i]=win_tmp;
				//System.out.println(Arrays.toString(win_tmp));
			}
			
			for(int x=0;x<(N-M)+1;x++) {
				for(int y=0;y<(N-M+1);y++) { // x,y= m*m 배열 할당 가능한 start position
					//System.out.println("x"+x+" "+"y"+y);
					int[][] heatbox=new int[M][M];
					int heat_tmp=0;
					
					for(int idx=0;idx<M;idx++) {
						//System.out.println(Arrays.toString(window[idx])+" "+x+" "+Arrays.toString(heatbox[idx])+" "+x);
						//System.out.println("x:"+x+" "+"idx:"+idx);
						System.arraycopy(window[x+idx], y, heatbox[idx], 0, M);
						//System.out.println(Arrays.toString(heatbox[idx]));
					}
					
					for(int[] hb:heatbox) {
						heat_tmp+=Arrays.stream(hb).sum();
					}
					
					if(heat_tmp>heat_sum) heat_sum=heat_tmp;
					//System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
				}
			}
			
			bw.write("#"+t+" "+heat_sum+'\n');
		}
		
		bw.flush();
		bw.close();
	}

}
