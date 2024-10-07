package silver;
import java.util.*;
import java.io.*;
public class Solution1244 {

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		BufferedReader br=new BufferedReader(new FileReader("C:\\SSAFY\\workspaces\\02-Java\\BaekJoon\\src\\silver\\input1244.txt"));
		BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
		
		int swh_n=Integer.parseInt(br.readLine());
		int[] swh_stat=Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		System.out.println("원래 스위치 상태:"+Arrays.toString(swh_stat));
		int std_n=Integer.parseInt(br.readLine());
		int[][] student=new int[std_n][2];
		
		for(int std=0;std<std_n;std++) {
			int[] std_tmp=Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			student[std][0]=std_tmp[0]; // 성별
			student[std][1]=std_tmp[1]; // 부여 넘버
			
			if(std_tmp[0]==1) { // 남학생
				for(int i=0;i<swh_stat.length;i++) { // 배수 스위치만 상태 변경
					if((i+1)%std_tmp[1]==0) {
						if(swh_stat[i]==0) swh_stat[i]=1; else swh_stat[i]=0;
					}
				}
				System.out.println("남학생 변경:"+Arrays.toString(swh_stat));
			}else { // 여학생
				int now=std_tmp[1]-1; // 현재 스위치 위치
				int dist=Math.min(std_tmp[1]-2, swh_stat.length-std_tmp[1])+1; // 최대 distance. idx라 +1 필요
				System.out.println("최대 수용 dist:"+dist);
				
				for(int d=1;d<=dist;d++) {
					System.out.println("d:"+d);
					System.out.println("여학생 연산 위치:"+(now-d)+","+(now+d));
					if(swh_stat[now-d]==swh_stat[now+d]) { // 대칭 시
						if(swh_stat[now-d]==0) swh_stat[now-d]=1; else swh_stat[now-d]=0;
						if(swh_stat[now+d]==0) swh_stat[now+d]=1; else swh_stat[now+d]=0;
					}
					else System.out.println("최대 dist:"+d); break;
				}
				
				if(swh_stat[now]==0) swh_stat[now]=1; else swh_stat[now]=0; // 대칭과 상관 없이 현재 변경
				
				System.out.println("여학생 변경:"+Arrays.toString(swh_stat));
			}
		}
		
		bw.write(Arrays.toString(swh_stat));
		bw.flush();
		bw.close();
		br.close();

	}

}
