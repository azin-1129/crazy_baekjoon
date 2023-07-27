package Problems0727;

import java.io.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Solution1244 {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br=new BufferedReader(new FileReader("C:\\Users\\SSAFY\\Desktop\\백준\\백준\\src\\Problems0727\\input1244.txt"));
		BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
		
		int swtch=Integer.parseInt(br.readLine()); // 스위치 개수
		
		// 스위치 상태
		int[] status=Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		
		int std_cnt=Integer.parseInt(br.readLine()); // 학생 수
		
		int[][] stdnts=new int[std_cnt][2]; // 학생 정보 배열
		
		for(int i=0;i<std_cnt;i++) { // 학생 수 만큼
			int[] info=Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			stdnts[i][0]=info[0]; // 성별
			stdnts[i][1]=info[1]-1; // 부여받은 숫자. 스위치가 1번부터 시작해서 -1
		}
		
		for(int[] std:stdnts) {
			if(std[0]==1) { // 남자애면
				for(int idx=0;idx<status.length;idx++) {
					if(status[idx]%std[1]==0) { // 배수에 해당하는 스위치
						if(status[idx]==0) { // 상태 체인지
							status[idx]=1;
						}else {
							status[idx]=0;
						}			
					}
				}	
			}else if(std[0]==2) { // 여자애면
				int dist=Math.min(std[0]-1,status.length-std[0]); // 좌, 우 여백 중 더적은 거리
				
				for(int d=dist;d>=0;d--) {
					List<int[]> left=Arrays.asList(Arrays.copyOfRange(status, std[1]-dist, std[1]-1)); // 좌 슬라이스
					Collections.reverse(left); // 대칭 비교를 위한 리버스
					List<int[]> right=Arrays.asList(Arrays.copyOfRange(status, std[1]+1, std[1]+dist)); // 우 슬라이스
					
					if(left.equals(right)) break; else dist-=1;
				}
				
				// 최대 dist 확정
				
				for(int d=1;d<=dist;d++) {
					if(status[std[1]-d]==0) {
						status[std[1]-d]=1;
					}else {
						status[std[1]-d]=0;
					}
							
					if(status[std[1]+d]==0) {
						status[std[1]+d]=1;
					}else {
						status[std[1]+d]=0;
					}
				}
			}
		}
		bw.write(status.toString());
		bw.flush();
		bw.close();

	}

}
