package Code;

import java.util.*;
import java.io.*;

class BOJ_5427{
    static class Node implements Comparable<Node>{
        int x;
        int y;
        int depth;

        public Node(int x, int y, int depth){
            this.x=x;
            this.y=y;
            this.depth=depth;
        }

        public int compareTo(Node n){
            return Integer.compare(this.depth, n.depth); // 오름차순
        }
    }
    static class FireInfo{
        int x;
        int y;
        int iter;

        public FireInfo(int x, int y, int iter){
            this.x=x;
            this.y=y;
            this.iter=iter;
        }

        public String toString(){
            return "x:"+x+", y:"+y;
        }
    }
    static int[] dx={-1, 1, 0, 0};
    static int[] dy={0, 0, -1, 1};
    static int w, h;
    static char[][] building;
    static Queue<FireInfo> fq;
    public static void main(String[] args) throws Exception{
        String filepath=System.getProperty("user.dir")+"\\Input\\";
        int bojNum=5427;
        BufferedReader br=new BufferedReader(new FileReader(filepath+"input"+bojNum+".txt"));
        StringBuilder sb=new StringBuilder();

        int T=Integer.parseInt(br.readLine());
        for(int t=1;t<=T;t++){
            StringTokenizer st=new StringTokenizer(br.readLine(), " ");
            w=Integer.parseInt(st.nextToken());
            h=Integer.parseInt(st.nextToken());
            System.out.println("가로:"+w+", 세로:"+h);

            building=new char[h][w];
            boolean[][] visited=new boolean[h][w];

            int startX=-1;
            int startY=-1;
            for(int x=0;x<h;x++){
                String inputs=br.readLine();
                for(int y=0;y<w;y++){
                    char current=inputs.charAt(y);
                    building[x][y]=current;

                    if(current=='@'){
                        startX=x;
                        startY=y;
                    }
                }
            }

            // pq 내 노드들 진행도가 다를 텐데.. 불 붙이고 말고는 어떻게 맞춰 주지?
            // pq 정렬 기준을 이동 수로 맞추고, 이동 수가 바뀌었다면 불 지펴주면 될 듯?
            // pq 내 타입은.. 현X, 현Y, 이동 수

            PriorityQueue<Node> pq=new PriorityQueue<>();
            fq=new ArrayDeque<>();
            int moveDepth=-1;
            int result=-1;
            boolean flag=false;
            Node node=new Node(startX, startY, 0);
            pq.offer(node);
            printing("초기 배열 상태입니다.");
            for(int x=0;x<h;x++){
                for(int y=0;y<w;y++){
                    if(building[x][y]=='*'){
                        fq.offer(new FireInfo(x, y, 0));
                    }
                }
            }
            System.out.println("fq를 초기화했습니다:"+fq);
            while(!pq.isEmpty()){
                Node current=pq.poll();

                int currentX=current.x;
                int currentY=current.y;
                System.out.println("cx:"+currentX+", cy:"+currentY);

                // 이동 수가 다르다면 1초가 지난 것이라 판단하고 방화한다.
                if(current.depth!=moveDepth){
                    moveDepth+=1;
                    // 방화를 1칸씩만 해야 하는데..
                    fire(moveDepth);
                    printing("불이 번졌습니다.");
                }

                // 방문했다면 패스
                if(visited[currentX][currentY]){
                    continue;
                }

                // 방문 처리
                visited[currentX][currentY]=true;

                for(int d=0;d<4;d++){
                    int nextX=currentX+dx[d];
                    int nextY=currentY+dy[d];

                    System.out.println("nx:"+nextX+", ny:"+nextY);

                    // 만약 범위 밖이라면, 탈출했으므로 break하고, result=current.depth
                    if(nextX<0 || nextY<0 || nextX>=h || nextY>=w){
                        System.out.println("출구를 발견했습니다:"+nextX+","+nextY);
                        System.out.println("현재 좌표:"+currentX+","+currentY);
                        result=current.depth+1;
                        flag=true;
                        break;
                    }

                    // 방문했다면 제낀다.
                    if(visited[nextX][nextY]){
                        continue;
                    }
                    // 불이면 제낀다.
                    if(building[nextX][nextY]=='*'){
                        continue;
                    }
                    // 벽이면 제낀다.
                    if(building[nextX][nextY]=='#'){
                        continue;
                    }
                    // pq에 다음 좌표를 삽입한다.
                    pq.offer(new Node(nextX, nextY, current.depth+1));
                }
                if(flag){
                    break;
                }
            }
            if(result==-1){
                sb.append("IMPOSSIBLE\n");
            }else{
                sb.append(result+"\n");
            }
        }

        sb.deleteCharAt(sb.length()-1);
        System.out.println(sb);
        br.close();
    }
    static void fire(int iter){
        while(!fq.isEmpty()){
            if(fq.peek().iter!=iter){
                break;
            }
            FireInfo fi=fq.poll();
            int x=fi.x;
            int y=fi.y;
            for(int d=0;d<4;d++){
                int nextX=x+dx[d];
                int nextY=y+dy[d];

                System.out.println("불을 붙일 자리를 탐색하고 있어요:"+nextX+", "+nextY);
                // 건물 밖엔 불이 붙을 수 없다.
                if(nextX<0 || nextY<0 || nextX>=h || nextY>=w){
                    continue;
                }
                // 이미 불? 패스
                if(building[nextX][nextY]=='*'){
                    continue;
                }
                // 벽? 패스
                if(building[nextX][nextY]=='#'){
                    continue;
                }

                // 불 붙을 예정
                building[nextX][nextY]='*';
                System.out.println(nextX+", "+nextY+"에 불이 붙었습니다.");
                fq.offer(new FireInfo(nextX, nextY, iter+1));
            }
        }
    }
    static void printing(String str){
        System.out.println(str);
        for(int x=0;x<h;x++){
            System.out.println(Arrays.toString(building[x]));
        }
        System.out.println();
    }
}