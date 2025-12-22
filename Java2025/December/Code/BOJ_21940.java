package Code;

import java.util.*;
import java.io.*;

class BOJ_21940{
    static final int INF=200001;
    public static void main(String[] args) throws Exception{
        String filepath=System.getProperty("user.dir")+"\\Input\\";
        int bojNum=21940;
        BufferedReader br=new BufferedReader(new FileReader(filepath+"input"+bojNum+".txt"));
        StringTokenizer st;

        st=new StringTokenizer(br.readLine(), " ");
        int N=Integer.parseInt(st.nextToken());
        int M=Integer.parseInt(st.nextToken());

        // 플로이드 그래프 초기화
        int[][] floyd=new int[N+1][N+1];
        for(int x=1;x<=N;x++){
            Arrays.fill(floyd[x], INF);
            floyd[x][x]=0;
        }

        for(int i=0;i<M;i++){
            st=new StringTokenizer(br.readLine(), " ");
            int from=Integer.parseInt(st.nextToken());
            int to=Integer.parseInt(st.nextToken());
            int weight=Integer.parseInt(st.nextToken());
            // 방향 그래프
            floyd[from][to]=weight;
        }

        for(int z=1;z<=N;z++){
            for(int x=1;x<=N;x++){
                for(int y=1;y<=N;y++){
                    floyd[x][y]=Math.min(floyd[x][z]+floyd[z][y], floyd[x][y]);
                }
            }
        }

        // floyd[x][y]+floyd[y][x] = 왕복 시간
        // 왕복시간 최댓값들 중 최솟값을 구해야 함.
        // 해당 최솟값에 해당하는 도시들 출력
        int K=Integer.parseInt(br.readLine());
        int[] activatedCitys=new int[K];
        st=new StringTokenizer(br.readLine(), " ");
        for(int i=0;i<K;i++){
            activatedCitys[i]=Integer.parseInt(st.nextToken());
        }

        // 활성화된(거주 중인) 도시만 고려하여 왕복시간 계산
        int[] roundTripDists=new int[N+1];
        for(int x=1;x<=N;x++){
            for(int y:activatedCitys){
                if(floyd[x][y]==INF || floyd[y][x]==INF){
                    continue;
                }
                // 도시 X를 기준으로 왕복 시간 최댓값 갱신
                roundTripDists[x]=Math.max(roundTripDists[x], floyd[x][y]+floyd[y][x]);
            }
        }
        System.out.println("왕복시간 최댓값:"+Arrays.toString(roundTripDists));

        int minRoundTripDist=Integer.MAX_VALUE;
        for(int i=1;i<=N;i++){
            minRoundTripDist=Math.min(minRoundTripDist, roundTripDists[i]);
        }
        System.out.println("왕복시간 최대 중 최소:"+minRoundTripDist);

        Queue<Integer> q=new ArrayDeque<>();
        for(int i=1;i<=N;i++){
            if(roundTripDists[i]==minRoundTripDist){
                q.offer(i);
            }
        }

        StringBuilder sb=new StringBuilder();
        while(!q.isEmpty()){
            sb.append(q.poll()+" ");
        }

        sb.deleteCharAt(sb.length()-1);
        System.out.println(sb);
        br.close();
    }
}