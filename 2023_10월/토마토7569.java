package Gold.d1021;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class 토마토7569 {
	static class Tomato{
		int x;
		int y;
		int z;
		public Tomato(int z, int x, int y) {
			super();
			this.x = x;
			this.y = y;
			this.z = z;
		}
		@Override
		public String toString() {
			return "Tomato [x=" + x + ", y=" + y + ", z=" + z + "]";
		}
		
	}
	static int M,N,H,res;
	static int[][][] wareHouse;
	static int dx[]= {-1,1,0,0,0,0},
			dy[]= {0,0,-1,1,0,0},
			dz[]= {0,0,0,0,-1,1};
	static boolean[][][] visited;
	static Queue<Tomato> q;
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		st=new StringTokenizer(br.readLine());
		
		int before=0; // 사전 체크
		
		M=Integer.parseInt(st.nextToken());
		N=Integer.parseInt(st.nextToken());
		H=Integer.parseInt(st.nextToken());
		
		wareHouse=new int[H][N][M];
		visited=new boolean[H][N][M];
		res=-1;
		
		q=new ArrayDeque<>();
		
		for(int h=0;h<H;h++) {
			for(int n=0;n<N;n++) {
				st=new StringTokenizer(br.readLine());
				for(int m=0;m<M;m++) {
					wareHouse[h][n][m]=Integer.parseInt(st.nextToken());
					
					if(wareHouse[h][n][m]==1) {
						// 익은 토마토
						// before+=1;
						visited[h][n][m]=true;
						q.add(new Tomato(h,n,m));
					}else if(wareHouse[h][n][m]==-1){ // 토마토 없
						visited[h][n][m]=true; // 못가게 막음
					}else {
						before=1; // 안 익은 토마토
					}
				}
			}
		}
		
		if(before==0) { // 안 익은 토마토 없음
			res=0;
		}else {
			bfs();
		}
		
		bw.write(res+"\n");
		bw.flush();
		bw.close();
		br.close();
	}

	public static void bfs() {
		int day=0;
		int cx, cy, cz; // 시발점
		int nx, ny, nz; // 퍼질 곳
		Tomato tmp;
		int qSize;
		
		while(!q.isEmpty()) {
			day+=1;
			qSize=q.size();
			for(int d=0;d<qSize;d++) {
				tmp=q.poll();
				
				// System.out.println("익히기 시작:"+tmp);
				
				cx=tmp.x;
				cy=tmp.y;
				cz=tmp.z;
				
				for(int dt=0;dt<dx.length;dt++) {
					nx=cx+dx[dt];
					ny=cy+dy[dt];
					nz=cz+dz[dt];
					
					if(nx<0||nx>=N
							||ny<0||ny>=M
							||nz<0||nz>=H) { // 범위 밖
						continue;
					}
					if((visited[nz][nx][ny]==true)
							|| wareHouse[nz][nx][ny]==-1
							|| wareHouse[nz][nx][ny]==1) {
						continue;
					}
					
					q.offer(new Tomato(nz,nx,ny)); // 익혔다.
//					System.out.println(nz+","+nx+","+ny+"를 익혔다.");
					visited[nz][nx][ny]=true;
					wareHouse[nz][nx][ny]=1;
				}
			} // 하루 끝
			if(isPass(wareHouse)) {
				res=day;
				return;
			}
		}
		
		// 할만큼 했음.
		return;
	}
	
	public static boolean isPass(int[][][] wareHouse) {
		for(int z=0;z<H;z++) {
			for(int x=0;x<N;x++) {
				for(int y=0;y<M;y++) {
					if(wareHouse[z][x][y]==0) { // 안 익은 토마토
						return false;
					}
				}
			}
		}
		
		return true;
	}
}
