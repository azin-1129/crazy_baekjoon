import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;
import java.util.StringTokenizer;

public class 뱀3190 {
    static class Node{
        int x;
        int y;

        Node(int x, int y){
            this.x=x;
            this.y=y;
        }
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int N=Integer.parseInt(br.readLine());
        int K=Integer.parseInt(br.readLine());

        int snakeX=0;
        int snakeY=0;
        int tailX=0;
        int tailY=0;

        int[][] board=new int[N][N];

        for(int k=0;k<K;k++){
            st=new StringTokenizer(br.readLine());

            int appleX=Integer.parseInt(st.nextToken());
            int appleY=Integer.parseInt(st.nextToken());

            board[appleX][appleY]=2; // 사과 부여
        }

        int[] dx={0,1,0,-1};
        int[] dy={1,0,-1,0};

        int L=Integer.parseInt(br.readLine());
        int second=0;

        int flag=0; // 리팩토링 전 임시 플래그
        int rotateDelta=0;

        Stack<Node> tailStack=new Stack<>();
        // 연산 시작
        for(int l=0;l<L;l++){
            if(flag==1) break;

            st=new StringTokenizer(br.readLine());

            int rotateTime=Integer.parseInt(st.nextToken());
            String rotate=st.nextToken();

            // 회전
            for(int s=second;s<rotateTime;s++){
                tailX=snakeX;
                tailY=snakeY;
                board[tailX][tailY]=1; // 마킹

                snakeX+=dx[rotateDelta];
                snakeY+=dy[rotateDelta]; // 전진

                if(snakeX>=N || snakeX<0 || snakeY>=N || snakeY<0){
                    flag=1;
                    break;
                }

                switch(board[snakeX][snakeY]){
                    case 2: // 사과
                        tailStack.add(new Node(tailX, tailY));
                        board[snakeX][snakeY]=1; // 진행
                        break;
                    case 1: // 뱀
                        flag=1;
                        break;
                    case 0: // 빈칸
                        Node mark=tailStack.pop(); // 꼬리 안 늘림
                        board[mark.x][mark.y]=0;
                        break;
                }
            }

            if(rotate.equals("L")){
                rotateDelta-=1;
                if(rotateDelta<0) rotateDelta=3;
            }else{
                rotateDelta+=1;
                if(rotateDelta>3) rotateDelta=0;
            }

            second=rotateTime-1;
        }

        System.out.println(second+"초에 종료됨");
    }
}