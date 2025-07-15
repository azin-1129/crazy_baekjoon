package Code;

import java.util.*;
import java.io.*;

class BOJ_1941{
    static class Student{
        int x;
        int y;
        char team;

        public Student(int x, int y, char team){
            this.x=x;
            this.y=y;
            this.team=team;
        }

        @Override
        public String toString(){
            return "("+x+","+y+"):"+team;
        }
    }
    static class Node{
        int x;
        int y;

        public Node(int x, int y){
            this.x=x;
            this.y=y;
        }
    }
    static int count=0;
    static int BOARD_SIZE=5;
    static int STUDENT_COUNT=25;
    static int MAX_DEPTH=7;
    static Student[] students;
    static Student[] result;
    static int[] dx={-1, 1, 0, 0};
    static int[] dy={0, 0, -1, 1};
    public static void main(String[] args) throws Exception{
        String filepath=System.getProperty("user.dir")+"\\Input\\";
        int bojNum=1941;
        BufferedReader br=new BufferedReader(new FileReader(filepath+"input"+bojNum+".txt"));

        students=new Student[STUDENT_COUNT];
        result=new Student[MAX_DEPTH];
        for(int i=0;i<BOARD_SIZE;i++){
            String inputs=br.readLine();
            for(int j=0;j<BOARD_SIZE;j++){
                students[i*BOARD_SIZE+j]=new Student(i, j, inputs.charAt(j));
            }
        }
        comb(0, 0);
        System.out.println(count);
        br.close();
    }
    static void comb(int start, int depth){
        if(depth>=MAX_DEPTH){
            // 7명의 학생을 뽑았으니,
            // 현 학생들이 서로 인접하는가?
            if(!isAdj()){
                return;
            }
            // 다솜이가 4명 이상인가?
            if(countDasom()>=4){
                count+=1;
            }

            return;
        }

        for(int i=start;i<STUDENT_COUNT;i++){
            result[depth]=students[i];
            comb(i+1, depth+1);
        }
    }
    static boolean isAdj(){
        // 조합의 첫 노드부터 시작해서, 다른 노드로 모두 도달할 수 있는가?
        boolean[][] resultBoard=new boolean[BOARD_SIZE][BOARD_SIZE];
        boolean[][] visited=new boolean[BOARD_SIZE][BOARD_SIZE];
        for(Student std:result){
            resultBoard[std.x][std.y]=true;
        }

        // visited Count가 7보다 작다면.. 서로 인접하지 않은 것.
        int visitedCount=0;
        Queue<Node> sq=new ArrayDeque<>();
        sq.offer(new Node(result[0].x, result[0].y));

        while(!sq.isEmpty()){
            Node student=sq.poll();
            int x=student.x;
            int y=student.y;

            if(visited[x][y]){
                continue;
            }
            visited[x][y]=true;

            if(resultBoard[x][y]==true){ // BFS로 탐색 시, 조합 내 노드를 발견함
                visitedCount+=1;
            }

            for(int i=0;i<4;i++){
                int nx=x+dx[i];
                int ny=y+dy[i];

                if(nx<0 || ny<0 || nx>=BOARD_SIZE || ny>=BOARD_SIZE){
                    continue;
                }

                if(resultBoard[nx][ny]==false){
                    continue;
                }

                sq.offer(new Node(nx, ny));
            }
        }
        if(visitedCount==7){
            return true;
        }else{
            return false;
        }
    }

    static int countDasom(){
        int count=0;
        for(int i=0;i<MAX_DEPTH;i++){
            if(result[i].team=='S'){
                count+=1;
            }
        }

        return count;
    }
}