package Code;

import java.io.*;
import java.util.*;
 
public class BOJ_10159 {
 
    public static void main(String[] args) throws Exception {
        String filepath=System.getProperty("user.dir")+"\\Input\\";
        int bojNum=10159;
        BufferedReader br=new BufferedReader(new FileReader(filepath+"input"+bojNum+".txt"));
        StringTokenizer st;
        StringBuilder sb=new StringBuilder();
 
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        int[][] adj=new int[N][N];

        for(int i=0;i<N;i++){
            adj[i][i]=1;
        }

        for(int i=0;i<M;i++){
            st=new StringTokenizer(br.readLine(), " ");
            int x=Integer.parseInt(st.nextToken())-1;
            int y=Integer.parseInt(st.nextToken())-1;

            adj[x][y]=1;
            adj[y][x]=-1;
        }

        // 플로이드-워셜(대소 관계)

        for(int k=0;k<N;k++){
            for(int x=0;x<N;x++){
                for(int y=0;y<N;y++){
                    if(adj[x][k]==1 && adj[k][y]==1){ // 정방향
                        adj[x][y]=1;
                    }

                    if(adj[x][k]==-1 && adj[k][y]==-1){ // 역방향
                        adj[x][y]=-1;
                    }
                }
            }
        }

        for(int i=0;i<N;i++){
            int cnt=0;
            for(int j=0;j<N;j++){
                if(adj[i][j]!=0){
                    cnt+=1;
                }
            }
            sb.append(N-cnt+"\n");
        }

        sb.deleteCharAt(sb.length()-1);

        System.out.println(sb);

        br.close();
    }
}