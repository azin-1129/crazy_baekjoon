package Code;

import java.util.*;
import java.io.*;

class BOJ_14502{
    static class Node{
        int x;
        int y;

        public Node(int x, int y){
            this.x=x;
            this.y=y;
        }

        @Override
        public String toString(){
            return "("+x+","+y+")";
        }
    }
    static List<Node> viruses=new ArrayList<>();
    static List<Node> walls=new ArrayList<>();
    static Node[] combResult=new Node[3];
    static int[][] lab;
    static int[] dx={-1, 1, 0, 0};
    static int[] dy={0, 0, -1, 1};
    static int N, M, result;
    public static void main(String[] args) throws Exception{
        String filepath=System.getProperty("user.dir")+"\\Input\\";
        int bojNum=14502;
        BufferedReader br=new BufferedReader(new FileReader(filepath+"input"+bojNum+".txt"));
        StringTokenizer st;

        st=new StringTokenizer(br.readLine(), " ");
        N=Integer.parseInt(st.nextToken());
        M=Integer.parseInt(st.nextToken());
        lab=new int[N][M];

        for(int x=0;x<N;x++){
            st=new StringTokenizer(br.readLine(), " ");
            for(int y=0;y<M;y++){
                int value=Integer.parseInt(st.nextToken());
                if(value==0){ // 벽 세워둘 좌표 후보 추리기
                    walls.add(new Node(x, y));
                }else if(value==2){
                    viruses.add(new Node(x, y));
                }

                lab[x][y]=value;
            }
        }

        comb(0, 0);
        System.out.println(result);
        br.close();
    }
    static void comb(int start, int depth){
        if(depth>=3){
            BFS();
            return;
        }

        for(int i=start;i<walls.size();i++){
            combResult[depth]=walls.get(i);
            comb(i+1, depth+1);
        }
    }
    static void BFS(){
        // 연산 후 복구를 위해 원본 복제해 두기
        int[][] labOrigin=new int[N][M];
        for(int x=0;x<N;x++){
            for(int y=0;y<M;y++){
                labOrigin[x][y]=Integer.valueOf(lab[x][y]);
            }
        }

        boolean[][] visited=new boolean[N][M];
        Queue<Node> q=new ArrayDeque<>();
        for(Node virus:viruses){
            q.offer(new Node(virus.x, virus.y));
        }

        // 벽 부여
        for(Node wall:combResult){
            lab[wall.x][wall.y]=1;
        }
        while(!q.isEmpty()){
            Node node=q.poll();
            int x=node.x;
            int y=node.y;

            if(visited[x][y]){
                continue;
            }
            visited[x][y]=true;
            lab[x][y]=2;

            for(int d=0;d<4;d++){
                int nx=x+dx[d];
                int ny=y+dy[d];

                if(nx<0 || ny<0 || nx>=N || ny>=M){
                    continue;
                }

                if(lab[nx][ny]==1 | lab[nx][ny]==2){
                    continue;
                }

                q.offer(new Node(nx, ny));
            }
        }

        int score=0;
        for(int x=0;x<N;x++){
            for(int y=0;y<M;y++){
                if(lab[x][y]==0){
                    score+=1;
                }
                lab[x][y]=Integer.valueOf(labOrigin[x][y]);
            }
        }

        result=Math.max(score, result);
    }
}