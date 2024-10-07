package bronze;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.stream.IntStream;

public class Solution3040 {
	static int[] res=new int[9];
	static int[] smuff;
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		BufferedReader br=new BufferedReader(new FileReader("C:\\SSAFY\\workspaces\\02-Java\\BaekJoon\\src\\bronze\\input3040.txt"));
		BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
		int[] smuff=new int[9];
		
		for(int i=0;i<9;i++) {
			smuff[i]=Integer.parseInt(br.readLine());			
		}
		
		comb(0, 0);

	}
	
	static int[] comb(int cnt, int start) {
		if(cnt==9) {
			if(IntStream.of(res).sum()==100) {
				return res;
			}else return null;
		}
		
		for(int i=start;i<9;i++) {
			res[i]=smuff[i];
			comb(++cnt, i+1);
		}
		
	}

}
