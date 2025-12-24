package Code;

import java.util.*;
import java.io.*;

class BOJ_1507{
    public static void main(String[] args) throws Exception{
        String filepath=System.getProperty("user.dir")+"\\Input\\";
        int bojNum=1507;
        BufferedReader br=new BufferedReader(new FileReader(filepath+"input"+bojNum+".txt"));
        StringTokenizer st;

        // N*N 모두 연결되어 있기 때문에,
        // 필요 없는 간선 정보를 제거해야 한다.

        int INF=2501;
        int N=Integer.parseInt(br.readLine());
        int[][] floyd=new int[N][N];
        int[][] floydCopy=new int[N][N];
        for(int i=0;i<N;i++){
            st=new StringTokenizer(br.readLine(), " ");
            for(int j=0;j<N;j++){
                floyd[i][j]=Integer.parseInt(st.nextToken());
            }
            System.arraycopy(floyd[i], 0, floydCopy[i], 0, N);
            floyd[i][i]=INF;
        }

        for(int z=0;z<N;z++){
            for(int x=0;x<N;x++){
                for(int y=0;y<N;y++){
                    if(floyd[x][y]==(floyd[x][z]+floyd[z][y])){
                        floyd[x][y]=INF; // 필요없는 간선 정보. 특정 노드를 경유한 결과와 동일
                    }
                }
            }
        }

        // 엣지 소거 결과
        // System.out.println("불필요 엣지를 소거했습니다.");
        // for(int x=0;x<N;x++){
        //     System.out.println(Arrays.toString(floyd[x]));
        // }
        
        // 소거된 간선 제외 비용 합계
        int result=0;
        for(int x=0;x<N;x++){
            for(int y=0;y<N;y++){
                if(floyd[x][y]!=INF){
                    result+=floyd[x][y];
                }
            }
        }

        // 모순 체크(불가능한 경우. 즉 -1)를 위해 다시 한 번 플로이드
        for(int x=0;x<N;x++){
            floyd[x][x]=0; // x to x 비용은 0. INF로 설정했던 것을 되돌림
        }

        for(int z=0;z<N;z++){
            for(int x=0;x<N;x++){
                for(int y=0;y<N;y++){
                    floyd[x][y]=Math.min(floyd[x][y], floyd[x][z]+floyd[z][y]);
                }
            }
        }

        // System.out.println("플로이드를 다시 진행했습니다.");
        // for(int x=0;x<N;x++){
        //     System.out.println(Arrays.toString(floyd[x]));
        // }

        // System.out.println("카피한 원본:");
        // for(int x=0;x<N;x++){
        //     System.out.println(Arrays.toString(floydCopy[x]));
        // }
        
        // 원본이 모순된 입력인지 판단
        boolean isImpossible=false;
        for(int x=0;x<N;x++){
            if(isImpossible){
                break;
            }
            for(int y=0;y<N;y++){
                if(floyd[x][y]!=floydCopy[x][y]){
                    isImpossible=true;
                    break;
                }
            }
        }

        if(isImpossible){
            System.out.println(-1);
        }else{
            // N*N 완전 연결에서 절반 값을 제거
            System.out.println(result/2);
        }

        br.close();
    }
}