package algo1.assign0802;

import java.io.*;
import java.util.*;

public class Solution1954 {

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		
		BufferedReader br=new BufferedReader(new FileReader("C:\\SSAFY\\workspaces\\02-Java\\algo1\\src\\algo1\\assign0802\\input1954.txt"));
		BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));

		int T=Integer.parseInt(br.readLine()); // T
		
		for(int t=1;t<=T;t++) {
			int N=Integer.parseInt(br.readLine());
			int dist_now=0; // →
			int[] dx= {0,1,0,-1};
			int[] dy= {1,0,-1,0};
			int[][] result=new int[N][N];
			int[] nums=new int[N*N];
			int x=0; int y=0; int idx=1;
			int turn_cnt=0; // 4가 되면 탈락
			
			result[0][0]=1; // initialize
			
			for(int n=0;n<N*N;n++) {
				nums[n]=n+1;
			} // 넣을 숫자 어레이 초기화
			
			//System.out.println("nums:"+Arrays.toString(nums));
			
			while(true) {
				if(dist_now==4) dist_now=0;
				if(turn_cnt==4) break;
				
				//System.out.println("dist_now:"+dist_now+", x y"+x+" "+y);
				
				int nx=x+dx[dist_now];
				int ny=y+dy[dist_now];
				
				//System.out.println("nx"+nx+" ny"+ny);
				
				//System.out.println(nx+" "+ny);
				if(nx<0 || nx>=N || ny<0 || ny>=N) {dist_now++; turn_cnt++; continue;} // 범위 밖이면 방향 바꾸고 continue
				
				if(result[nx][ny]==0) { // empty
					result[nx][ny]=nums[idx++];
					//System.out.println("nx"+nx+", ny"+ny+"에 값을 부여");
					x=nx;
					y=ny;
					turn_cnt=0;
				}else { // 더이상 진행할 수 없음
					dist_now++;
					turn_cnt++;
					continue;
				}
				
			}
			
			bw.write("#"+t+'\n');
			for(int[] res:result) {
				bw.write(Arrays.toString(res).replaceAll("[\\[\\],]", "")+'\n');
			}
			
			bw.flush();
		}
		bw.close();
		br.close();
	}

}
