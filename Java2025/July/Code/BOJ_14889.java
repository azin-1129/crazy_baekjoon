package Code;

import java.util.*;
import java.io.*;

class BOJ_14889{
    static int N;
    static int result=Integer.MAX_VALUE;
    static int[] combination, members;
    static int[][] scores;
    public static void main(String[] args) throws Exception{
        String filepath=System.getProperty("user.dir")+"\\Input\\";
        int bojNum=14889;
        BufferedReader br=new BufferedReader(new FileReader(filepath+"input"+bojNum+".txt"));
        StringTokenizer st;

        N=Integer.parseInt(br.readLine());
        members=new int[N];
        combination=new int[N/2];
        scores=new int[N][N];
        for(int i=0;i<N;i++){
            members[i]=i;
            st=new StringTokenizer(br.readLine(), " ");
            for(int j=0;j<N;j++){
                scores[i][j]=Integer.parseInt(st.nextToken());
            }
        }
        
        comb(0, 0);
        System.out.println(result);
        br.close();
    }
    static void comb(int start, int depth){
        if(depth==(N/2)){
            // System.out.println("팀원을 한 번 뽑아 봤어요.");
            // System.out.println(Arrays.toString(combination));
            calc();
            return;
        }

        for(int i=start;i<N;i++){
            combination[depth]=members[i];
            comb(i+1, depth+1);
        }
    }
    static void calc(){
        int combScore=0;
        boolean[] visited=new boolean[N];
        for(int x:combination){
            for(int y:combination){
                combScore+=scores[x][y];
            }
            visited[x]=true;
        }
        // System.out.println("해당 팀원들은 사전에 합의한 조합으로 처리했어요.");
        // System.out.println(Arrays.toString(visited));
        // System.out.println("이 팀의 점수는 "+combScore+"점 입니다.");

        int otherScore=0;
        for(int x=0;x<N;x++){
            if(visited[x]){
                continue;
            }
            for(int y=0;y<N;y++){
                if(visited[y]){
                    continue;
                }
                otherScore+=scores[x][y];
            }
        }

        // System.out.println("나머지 팀원의 점수는 "+otherScore+"점 입니다.");
        result=Math.min(Math.abs((combScore-otherScore)), result);
    }
}