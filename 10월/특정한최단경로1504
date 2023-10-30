package d1030;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 특정한최단경로1504 {
	static class Node implements Comparable<Node>{
		int next;
		int cost;
		public Node(int next, int cost) {
			super();
			this.next = next;
			this.cost = cost;
		}
		@Override
		public int compareTo(Node o) {
			// TODO Auto-generated method stub
			return Integer.compare(this.cost,  o.cost);
		}
		@Override
		public String toString() {
			return "Node [next=" + next + ", cost=" + cost + "]";
		}
		
		
	}
	
	static int N,E, from;
	static List<Node>[] graph;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		int fromC, toC, cost; // graph tmp
		
		st=new StringTokenizer(br.readLine());
		
		N=Integer.parseInt(st.nextToken());
		E=Integer.parseInt(st.nextToken());
		
		graph=new ArrayList[N+1];
		
		
		for(int n=0;n<=N;n++) {
			graph[n]=new ArrayList<>();
		}
		
		for(int e=0;e<E;e++) {
			st=new StringTokenizer(br.readLine());
			
			fromC=Integer.parseInt(st.nextToken());
			toC=Integer.parseInt(st.nextToken());
			cost=Integer.parseInt(st.nextToken());
			
			graph[fromC].add(new Node(toC,cost));
			graph[toC].add(new Node(fromC,cost));
		}
		
		st=new StringTokenizer(br.readLine());
		
		int v1=Integer.parseInt(st.nextToken());
		int v2=Integer.parseInt(st.nextToken());
		
		int res=Math.min(dijkstra(1, v1)+dijkstra(v1,v2)+dijkstra(v2,N),
				dijkstra(1, v2)+dijkstra(v1,v2)+dijkstra(v1,N));
		
		int limit=1000*200000;
		
		if(res<(-limit) || res>limit) {
			bw.write("-1\n");
		}else {
			bw.write(res+"\n");
		}
		
		bw.flush();
		bw.close();
		br.close();
		
	}
	
	public static int dijkstra(int start, int to) {
		int[] dist=new int[N+1];
		boolean[] visited=new boolean[N+1];
		
		Arrays.fill(dist, Integer.MAX_VALUE);
		
		PriorityQueue<Node> pq=new PriorityQueue<>();
		// StringBuilder sb;
		
		Node nextNode;
		int nodeNow;
		
		pq.offer(new Node(start,0));
		dist[start]=0;
		
		while(!pq.isEmpty()) {
			// System.out.println("pq");
			nextNode=pq.poll();
			
			nodeNow=nextNode.next;
			
			if(nodeNow==to) {
				break; // 도착
			}
			
			if(visited[nodeNow]==true) {
				continue;
			}

			// System.out.println("visiting:"+nodeNow);
			visited[nodeNow]=true;
			
			for(Node nearby:graph[nodeNow]) {
				if(dist[nearby.next]>dist[nodeNow]+nearby.cost) {
					// 이어진 노드까지의 현재 거리>방문중인 노드까지의 거리+이어진 노드 비용
					dist[nearby.next]=dist[nodeNow]+nearby.cost;
					pq.offer(new Node(nearby.next, dist[nearby.next]));
				}
			}
		}
		
		// System.out.println("dist:"+Arrays.toString(dist));
		
		return dist[to];
	}
}
