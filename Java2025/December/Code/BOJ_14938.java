package Code;

import java.util.*;
import java.io.*;

public class BOJ_14938 {
    public static void main(String[] args) throws Exception{
        String filepath=System.getProperty("user.dir")+"\\Input\\";
        int bojNum=14938;
        BufferedReader br=new BufferedReader(new FileReader(filepath+"input"+bojNum+".txt"));
        StringTokenizer st;

        // 답: 범위 m 이내에서 획득할 수 있는 아이템 양의 최대치
        st=new StringTokenizer(br.readLine(), " ");
        int N=Integer.parseInt(st.nextToken()); // 노드 수
        int M=Integer.parseInt(st.nextToken()); // 수색 범위
        int R=Integer.parseInt(st.nextToken()); // 엣지 수

        int[] itemCounts=new int[N+1];
        st=new StringTokenizer(br.readLine(), " ");
        for(int i=0;i<N;i++){
            itemCounts[i+1]=Integer.parseInt(st.nextToken());
        }

        // 플로이드 배열 정보 초기화
        int[][] floyd=new int[N+1][N+1];
        for(int i=1;i<=N;i++){
            Arrays.fill(floyd[i], 16);
            floyd[i][i]=0; // 자기 자신까지는 0
        }
        for(int i=0;i<R;i++){
            st=new StringTokenizer(br.readLine(), " ");
            int from=Integer.parseInt(st.nextToken());
            int to=Integer.parseInt(st.nextToken());
            int weight=Integer.parseInt(st.nextToken());
            // 양방향
            floyd[from][to]=weight;
            floyd[to][from]=weight;
        }

        // 양방향 그래프 distance 확보(플로이드 워셜)
        for(int z=1;z<=N;z++){
            for(int x=1;x<=N;x++){
                for(int y=1;y<=N;y++){
                    floyd[x][y]=Math.min(floyd[x][y], floyd[x][z]+floyd[z][y]);
                }
            }
        }

        System.out.println("플로이드 워셜 결과:");
        for(int x=1;x<=N;x++){
            for(int y=1;y<=N;y++){
               System.out.print(floyd[x][y]+" "); 
            }
            System.out.println();
        }

        // 1~n번 시작점부터 낙하, m 이내 노드 아이템 획득(visited 처리)
        int result=0;
        for(int from=1;from<=N;from++){
            int cost=0;
            for(int to=1;to<=N;to++){
                if(floyd[from][to]>M){
                    continue;
                }
                cost+=itemCounts[to];
            }
            result=Math.max(result, cost);
        }

        System.out.println(result);
        br.close();
    }
}
