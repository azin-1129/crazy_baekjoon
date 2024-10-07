package Gold.d1021;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class 가장긴바이토닉부분수열11054 {
	static int[] subArr;

	static int N, A[], maxLen;
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		// StringTokenizer st;
		
		maxLen=0;
		
		N=Integer.parseInt(br.readLine());
		A=Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

		bitmasking();
		
		bw.write(maxLen+"\n");
		bw.flush();
		bw.close();
		br.close();
	}

	public static void bitmasking() {
		int cnt=0;
		int idx=0;

		for(int i=0;i<(1<<N);i++) {
			System.out.println("i:"+Integer.toBinaryString(i));
			cnt=Integer.bitCount(i);
			System.out.println("cnt:"+cnt);
			if(cnt==0) continue;
			
			idx=0;
			
			subArr=new int[cnt];
			for(int j=0;j<N;j++) {
				// System.out.println("bitmask 시도");
				if((i&(1<<j))!=0) { // 1이 1개인데 j가 왜 2번 들어가지? ArrayIndexOutOfBounts
					System.out.println("j:"+Integer.toBinaryString((1<<j)));
					// System.out.print(A[j]+" ");
					subArr[idx++]=A[j];
				}
			}
			// System.out.println();
			
			// 부분수열 완성
			
			// System.out.println("부분수열:"+Arrays.toString(subArr));
			if(bitonic(subArr)==true) {
				// System.out.println(Arrays.toString(subArr)+"는 바이토닉");
				maxLen=Math.max(maxLen, cnt); // 해당 i는 다 봄
			}
			
		}
	}
	
	public static boolean bitonic(int[] subArr) {
		int flag=0;
		int lF=0; int rF=0; // 좌우
		
		for(int i=0;i<subArr.length;i++) {
			lF=0;
			rF=0;
			
			if(i==0) { // 맨앞
				for(int j=0;j<(subArr.length-1);j++) {
					if(subArr[j]>=subArr[j+1]) {
						flag=1; // 실패 표시
						break;
					}
				}
				if(flag==0) {
					return true;
				}else {
					flag=0;
					continue; // 다음 시도
				}
			}
			
			if(i==(subArr.length-1)) { // 맨뒤
				for(int j=0;j<(subArr.length-1);j++) {
					if(subArr[j]<=subArr[j+1]) {
						flag=1;
						break;
					}
				}
				if(flag==0) {
					return true;
				}else {
					return false;
				}
			}
			
			// 사이
			
			for(int j=0;j<i;j++) {
				if(subArr[j]>=subArr[j+1]) {
					lF=1;
					break;
				}
			}
			
			for(int j=i;j<(subArr.length-1);j++) {
				if(subArr[j]<=subArr[j+1]) {
					rF=1;
					break;
				}
			}
			
			if(lF==0 && rF==0) {
				return true;
			}else {
				flag=0;
				continue; // 다음 시도
			}
			
		}
		
		return false;
	}
}
