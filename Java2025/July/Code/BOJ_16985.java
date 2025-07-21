package Code;

import java.util.*;
import java.io.*;

class BOJ_16985{
    static class Node{
        int x;
        int y;
        int z;
        int count;

        public Node(int x, int y, int z, int count){
            this.x=x;
            this.y=y;
            this.z=z;
            this.count=count;
        }

        @Override
        public String toString(){
            return "("+this.x+","+this.y+","+this.z+"): "+this.count;
        }
    }
    static int[] permIndex=new int[5];
    static boolean[] visited=new boolean[5];
    static int[] dx={0, 0, -1, 1, 0, 0};
    static int[] dy={0, 0, 0, 0, -1, 1};
    static int[] dz={-1, 1, 0, 0, 0, 0};
    static int BOARD_SIZE=5;
    static int result=Integer.MAX_VALUE;
    static int[][][] maze=new int[BOARD_SIZE][BOARD_SIZE][BOARD_SIZE];
    static int[][][] permutationResult=new int[BOARD_SIZE][BOARD_SIZE][BOARD_SIZE];
    public static void main(String[] args) throws Exception{
        String filepath=System.getProperty("user.dir")+"\\Input\\";
        int bojNum=16985;
        BufferedReader br=new BufferedReader(new FileReader(filepath+"input"+bojNum+".txt"));

        for(int x=0;x<BOARD_SIZE;x++){
            for(int y=0;y<BOARD_SIZE;y++){
                StringTokenizer st=new StringTokenizer(br.readLine(), " ");
                for(int z=0;z<BOARD_SIZE;z++){
                    maze[x][y][z]=Integer.parseInt(st.nextToken());
                }
            }
        }

        // 판 쌓은 순열 필요
        perm(0);

        if(result==Integer.MAX_VALUE){
            System.out.println(-1);
        }else{
            System.out.println(result);
        }
        br.close();
    }
    static void perm(int depth){
        if(depth>=5){
            // System.out.println("현재 조합:"+Arrays.toString(permIndex));
            // print3DArr(maze, "원본입니다.");

            for(int i=0;i<BOARD_SIZE;i++){
                for(int x=0;x<BOARD_SIZE;x++){
                    for(int y=0;y<BOARD_SIZE;y++){
                        permutationResult[i][x][y]=Integer.valueOf(maze[permIndex[i]][x][y]);
                    }
                }
            }
            // print3DArr(permutationResult, "조합에 맞게 원본을 교정했습니다.");
            backTracking(0, 0);
            return;
        }

        for(int i=0;i<5;i++){
            if(!visited[i]){
                visited[i]=true;
                permIndex[depth]=i;
                perm(depth+1);
                visited[i]=false;
            }
        }
    }
    static void backTracking(int start, int depth){
        if(depth>=BOARD_SIZE){
            if(permutationResult[0][0][0]==1){
                BFS();
            }
            return;
        }

        for(int i=start;i<BOARD_SIZE;i++){
            for(int j=0;j<4;j++){
                backTracking(i+1, depth+1);
                rotate(i);
            }
        }
    }
    static void BFS(){
        boolean[][][] visited=new boolean[BOARD_SIZE][BOARD_SIZE][BOARD_SIZE];
        Queue<Node> q=new ArrayDeque<>();
        q.offer(new Node(0, 0, 0,  0));
        while(!q.isEmpty()){
            Node node=q.poll();
            if(node.x==(BOARD_SIZE-1) & node.y==(BOARD_SIZE-1) & node.z==(BOARD_SIZE-1)){
                result=Math.min(result, node.count);
                return;
            }

            if(visited[node.x][node.y][node.z]){
                continue;
            }

            visited[node.x][node.y][node.z]=true;
            for(int i=0;i<6;i++){
                int nx=node.x+dx[i];
                int ny=node.y+dy[i];
                int nz=node.z+dz[i];

                if(nx<0 || ny<0 || nz<0 || nx>=BOARD_SIZE || ny>=BOARD_SIZE || nz>=BOARD_SIZE){
                    continue;
                }

                if(permutationResult[nx][ny][nz]==0){
                    continue;
                }

                q.offer(new Node(nx, ny, nz, node.count+1));
            }
        }
    }
    static void rotate(int idx){
        int[][] copy=new int[BOARD_SIZE][BOARD_SIZE];

        for(int x=0;x<BOARD_SIZE;x++){
            for(int y=0;y<BOARD_SIZE;y++){
                copy[x][y]=Integer.valueOf(permutationResult[idx][x][y]);
            }
        }

        for(int x=0;x<BOARD_SIZE;x++){
            for(int y=0;y<BOARD_SIZE;y++){
                permutationResult[idx][y][(BOARD_SIZE-1)-x]=copy[x][y];
            }
        }
    }
    static void print3DArr(int[][][] arr, String str){
        System.out.println(str);
        for(int z=0;z<BOARD_SIZE;z++){
            for(int x=0;x<BOARD_SIZE;x++){
                for(int y=0;y<BOARD_SIZE;y++){
                    System.out.print(arr[z][x][y]+" ");
                }
                System.out.println();
            }
            System.out.println();
        }
    }
}