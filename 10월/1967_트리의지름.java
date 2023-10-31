package d1031;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class 트리의지름1967 {
	// 이론 참조 블로그: https://johoonday.tistory.com/217
	static class Node{
		int next;
		int cost;
		public Node(int next, int cost) {
			super();
			this.next = next;
			this.cost = cost;
		}
	}
	static int[] dist;
	static boolean[] visited;
	static List<Node>[] graph;
	static int deepOne, longest, N;
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		N=Integer.parseInt(br.readLine());
		graph=new ArrayList[N+1];
		visited=new boolean[N+1];
		dist=new int[N+1];
		
		for(int n=0;n<=N;n++) {
			graph[n]=new ArrayList<>();
		}
		
		int fromC, toC, cost;
		
		
		
		for(int n=0;n<(N-1);n++) {
			st=new StringTokenizer(br.readLine());
			
			fromC=Integer.parseInt(st.nextToken());
			toC=Integer.parseInt(st.nextToken());
			cost=Integer.parseInt(st.nextToken());
			
			graph[fromC].add(new Node(toC, cost));
			graph[toC].add(new Node(fromC,cost));
		}
		
		dfs(1);
		//System.out.println("1로부터 제일 먼 노드:"+deepOne);
		//System.out.println("거리테이블:"+Arrays.toString(dist));
		visited=new boolean[N+1];
		dist=new int[N+1];
		longest=0;
		dfs(deepOne);
		
		bw.write(longest+"\n");
		bw.flush();
		bw.close();
		br.close();
	}
	private static void dfs(int start) {
		// if(visited[start]) return;
		//System.out.println(start+"탐색중");
		
		visited[start]=true;
		
		for(Node nearby:graph[start]) {
			if(visited[nearby.next]) continue;
			
			if(dist[nearby.next]<dist[start]+nearby.cost) {
				dist[nearby.next]=dist[start]+nearby.cost;
				
				//System.out.println(nearby.next+"까지의 거리를"+dist[nearby.next]+"로 갱신");
			}
			
			if(dist[nearby.next]>longest) {
				longest=dist[nearby.next];
				deepOne=nearby.next;
			}
			
			dfs(nearby.next);
		}
	}
}
