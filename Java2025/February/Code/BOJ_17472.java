import java.io.*;
import java.util.*;

class Node17472{
    int from;
    int to;
    int weight;

    Node17472(int from, int to, int weight){
        this.from=from;
        this.to=to;
        this.weight=weight;
    }

    @Override
    public String toString(){
        return from+" to "+to+"="+weight;
    }
}

public class BOJ_17472 {
    static int[] dx={-1, 1, 0, 0};
    static int[] dy={0, 0, -1, 1};
    static boolean[][] visitedForNumbering;
    static int[][] graph, graphInfo;
    static int N, M, edgeCount, numbering;
    static int[] parent;
    public static void main(String[] args) throws Exception{
        String filepath=System.getProperty("user.dir")+"\\Input\\";
        int bojNum=17472;
        BufferedReader br=new BufferedReader(new FileReader(filepath+"input"+bojNum+".txt"));
        StringTokenizer st;
    
        st=new StringTokenizer(br.readLine()," ");
        N=Integer.parseInt(st.nextToken());
        M=Integer.parseInt(st.nextToken());

        graph=new int[N][M];
        for(int x=0;x<N;x++){
            st=new StringTokenizer(br.readLine(), " ");
            for(int y=0;y<M;y++){
                graph[x][y]=Integer.parseInt(st.nextToken());
            }
        }

        // 섬 번호 부여
        visitedForNumbering=new boolean[N][M];
        numbering=1;
        for(int x=0;x<N;x++){
            for(int y=0;y<M;y++){
                if(graph[x][y]==1 & visitedForNumbering[x][y]==false){
                    graph[x][y]=numbering;
                    numberingDFS(x, y, numbering);
                    numbering+=1;
                }
            }
        }  

        // 좌표 추가(수정해야할 부분)
        graphInfo=new int[100][3]; // N*M<=100
        for(int i=0;i<100;i++){
            graphInfo[i][2]=10;
        }

        for(int x=0;x<N;x++){
            for(int y=0;y<M;y++){
                if(graph[x][y]>0){
                    bridge(x, y, graph[x][y], 0);
                    bridge(x, y, graph[x][y], 1);
                    bridge(x, y, graph[x][y], 2);
                    bridge(x, y, graph[x][y], 3);
                }
            }
        }

        // 크루스칼
        Arrays.sort(graphInfo, (o1, o2)->o1[2]-o2[2]);
        parent=new int[numbering]; // 섬 개수<=6
        for(int i=1;i<numbering;i++){
            parent[i]=i;
        }

        System.out.println(kruskal());
        br.close();
    }
    static void numberingDFS(int x, int y, int number){
        for(int i=0;i<4;i++){
            int nx=x+dx[i];
            int ny=y+dy[i];

            if(nx<0 || ny<0 || nx>=N || ny>=M){
                continue;
            }

            if(graph[nx][ny]==0){
                continue;
            }
            if(visitedForNumbering[nx][ny]){
                continue;
            }

            visitedForNumbering[nx][ny]=true;
            graph[nx][ny]=number; // 섬 번호 부여
            numberingDFS(nx, ny, number);
        }
    }
    static void bridge(int x, int y, int originRegion, int dir){
        int depth=1;
        while(true){
            int nx=x+dx[dir]*depth;
            int ny=y+dy[dir]*depth;

            if(nx<0 || ny<0 || nx>=N || ny>=M){
                break; // 범위 밖
            }

            if(graph[nx][ny]==originRegion){
                break; // 섬 파고들기
            }

            if(graph[nx][ny]==0){
                depth+=1;
                continue;
            }

            if(graph[nx][ny]>0 & depth<3){
                break;
            }

            if(depth>=3){
                graphInfo[edgeCount][0]=originRegion;
                graphInfo[edgeCount][1]=graph[nx][ny];
                graphInfo[edgeCount][2]=depth-1;
                edgeCount+=1;
                break;
            }
            depth+=1;
        }
    }
    static int kruskal(){
        int totalWeight=0;
        for(int i=0;i<edgeCount;i++){
            if(find(graphInfo[i][0])!=find(graphInfo[i][1])){
                union(graphInfo[i][0], graphInfo[i][1]);
                totalWeight+=graphInfo[i][2];
            }
        }

        // 부모 중 빈 곳이 있다면, 섬을 다 잇지 못한 것이기 때문에 return -1, or totalWeight
        for(int i=2;i<numbering;i++){
            if(parent[i]==i){
                return -1;
            }
        }
        return totalWeight;
    }
    static void union(int x, int y){
        int xParent=find(x);
        int yParent=find(y);

        if(xParent<yParent){
            parent[yParent]=xParent;
        }else{
            parent[xParent]=yParent;
        }
    }
    static int find(int x){
        if(parent[x]==x){
            return x;
        }else{
            return find(parent[x]);
        }
    }
}
