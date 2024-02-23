import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.concurrent.BrokenBarrierException;

public class 뱀3190 {
    static class Node{
        int x;
        int y;

        public Node(int x, int y){
            this.x=x;
            this.y=y;
        }
    }
    static class Calc{
        int rotateTime;
        String rotate;

        public Calc(int rotateTime, String rotate){
            this.rotateTime=rotateTime;
            this.rotate=rotate;
        }
    }

    public static void main(String[] args) throws Exception{
        String path=System.getProperty("user.dir")+"/2월/";

        // BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br=new BufferedReader(new FileReader(path+"input3190.txt"));
        BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int N=Integer.parseInt(br.readLine());
        int K=Integer.parseInt(br.readLine());

        int snakeX=0;
        int snakeY=0;
        int tailX=0;
        int tailY=0;

        int[][] board=new int[N][N];
        board[0][0]=1; // 뱀 마킹

        for(int k=0;k<K;k++){
            st=new StringTokenizer(br.readLine());

            int appleX=Integer.parseInt(st.nextToken())-1;
            int appleY=Integer.parseInt(st.nextToken())-1;

            board[appleX][appleY]=2; // 사과 부여
        }

        int[] dx={0,1,0,-1};
        int[] dy={1,0,-1,0};

        int L=Integer.parseInt(br.readLine());
        int second=0;
        int calcTime=0;

        int flag=0; // 리팩토링 전 임시 플래그
        int rotateDelta=0;

        int rotateTime;
        String rotate="";

        Queue<Node> tailStack=new LinkedList<>();
        Queue<Calc> calc=new LinkedList<>();

        for(int l=0;l<L;l++){
            st=new StringTokenizer(br.readLine());

            rotateTime=Integer.parseInt(st.nextToken());
            rotate=st.nextToken();

            calc.add(new Calc(rotateTime,rotate));
        }

        calc.add(new Calc(100, rotate)); // 마지막 연산 처리
        // 연산 시작
        for(int l=0;l<=L;l++){
            if(flag==1) break;

            Calc calctemp=calc.poll();

            rotateTime=calctemp.rotateTime;
            rotate=calctemp.rotate;

            // 회전 전까지 진행 <- 마지막 연산을 못함
            for(int s=second;s<rotateTime;s++){
                if(flag==1) break;
                calcTime+=1;
                System.out.println(calcTime+"초 경과했습니다.");

                tailX=snakeX;
                tailY=snakeY;

                snakeX+=dx[rotateDelta];
                snakeY+=dy[rotateDelta]; // 전진

                if(snakeX>=N || snakeX<0 || snakeY>=N || snakeY<0){
                    System.out.println("영역을 벗어난 접근입니다.");
                    flag=1;
                    break;
                }

                switch(board[snakeX][snakeY]){
                    case 2: // 사과
                        System.out.println("꼬리를 늘린 후 진행합니다.");
                        tailStack.add(new Node(tailX, tailY)); // 꼬리를 늘린다.  
                        board[tailX][tailY]=1; // 마킹
                        System.out.printf("꼬리: (%d,%d)에 마킹했습니다.\n",tailX,tailY);
                        board[snakeX][snakeY]=1; // 진행
                        break;
                    case 1: // 뱀
                        System.out.println("뱀이 감지되어 종료합니다.");
                        flag=1;
                        break;
                    case 0: // 빈칸
                        // 꼬리 안 늘리고 진행
                        // 꼬리 안 늘리고 진행하는데 꼬리가 있으면..?
                        System.out.println("꼬리를 늘리지 않고 진행합니다.");

                        if(!tailStack.isEmpty()){ // 만약 꼬리를 보유하고 있다면
                            System.out.println("꼬리를 보유함에 따라 분기합니다.");
                            
                            Node tail=tailStack.poll();
                            board[tail.x][tail.y]=0; // 맨 끝 꼬리 지우기

                            tailStack.add(new Node(tailX, tailY)); // 이전 꼬리를 추가해 준다.
                            board[snakeX][snakeY]=1; // 마킹
                        }else{
                            board[tailX][tailY]=0; // 바로 이전거 지우기
                            board[snakeX][snakeY]=1; // 마킹
                        }
                        System.out.printf("헤드: (%d,%d)에 마킹했습니다.\n",snakeX,snakeY);
                        break;
                }
                System.out.println("연산되는 board 현황. 1은 뱀, 2는 사과입니다.");

                for(int i=0;i<N;i++){
                    for(int j=0;j<N;j++){
                        System.out.print(board[i][j]);
                    }
                    System.out.println();
                }
            }

            if(rotate.equals("L")){
                rotateDelta-=1;
                if(rotateDelta<0) rotateDelta=3;
            }else{
                rotateDelta+=1;
                if(rotateDelta>3) rotateDelta=0;
            }

            System.out.println("연산이 끝나 방향을 전환합니다:"+rotateDelta);
            second=rotateTime;
        }

        System.out.println(calcTime+"초에 종료됨");
    }
}