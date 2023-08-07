import java.io.*;
import java.util.*;

public class Solution2164 {

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
		
		int N=Integer.parseInt(br.readLine());
		
		Queue<Integer> q=new LinkedList<>();
		
		for(int i=1;i<=N;i++) {
			q.add(i);
		}
		
		while(q.size()!=1) {
			q.poll();
			q.add(q.poll());
		}
		
		bw.write(q.poll()+"\n");
        
        bw.flush();
        
        br.close();
        bw.close();

	}

}
