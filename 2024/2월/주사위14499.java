import java.io.BufferedReader;
import java.io.FileReader;
import java.util.StringTokenizer;

public class 주사위14499 {
    public static void main(String[] args) throws Exception{
        String path=System.getProperty("user.dir")+"/2월/";

        BufferedReader br=new BufferedReader(new FileReader(path+"input14499.txt"));
        StringTokenizer st;
        StringBuilder sb=new StringBuilder();

        int N;
        int M;
        int diceX;
        int diceY;
        int K;

        int tmpE1;
        int tmpE2;
        int tmpW1;
        int tmpW2;
        int tmpS;
        int tmpN;

        int[][] dice=new int[4][3];

        st=new StringTokenizer(br.readLine());

        N=Integer.parseInt(st.nextToken());
        M=Integer.parseInt(st.nextToken());

        int[][] map=new int[N][M];

        diceX=Integer.parseInt(st.nextToken());
        diceY=Integer.parseInt(st.nextToken());

        K=Integer.parseInt(st.nextToken());
        int[] commands=new int[K];

        for(int n=0;n<N;n++){
            st=new StringTokenizer(br.readLine());
            for(int m=0;m<M;m++){
                map[n][m]=Integer.parseInt(st.nextToken());
            }
        }

        st=new StringTokenizer(br.readLine());

        for(int k=0;k<K;k++){
            commands[k]=Integer.parseInt(st.nextToken());
        }

        // 연산 시작
        for(int command:commands){
            switch(command){
                case 1: // 동
                    if(diceY+1>=M) continue;

                    tmpE1=dice[1][2];
                    tmpE2=dice[3][1];
                    dice[1][2]=dice[1][1];
                    dice[1][1]=dice[1][0];
                    dice[3][1]=tmpE1;
                    dice[1][0]=tmpE2;
                    diceY+=1;
                    break;
                case 2: // 서
                    if(diceY-1<0) continue;
                        
                    tmpW1=dice[1][0];
                    tmpW2=dice[3][1];
                    dice[1][0]=dice[1][1];
                    dice[1][1]=dice[1][2];
                    dice[3][1]=tmpW1;
                    dice[1][2]=tmpW2;
                    diceY-=1;
                    break;
                case 3: // 북
                    if(diceX-1<0) continue;

                    tmpN=dice[0][1];
                    dice[0][1]=dice[1][1];
                    dice[1][1]=dice[2][1];
                    dice[2][1]=dice[3][1];
                    dice[3][1]=tmpN;
                    diceX-=1;
                    break;
                case 4: // 남
                    if(diceX+1>=N) continue;

                    tmpS=dice[3][1];
                    dice[3][1]=dice[2][1];
                    dice[2][1]=dice[1][1];
                    dice[1][1]=dice[0][1];
                    dice[0][1]=tmpS;
                    diceX+=1;
                    break;
            }

            if(map[diceX][diceY]==0){
                map[diceX][diceY]=dice[3][1];
            }else{
                dice[3][1]=map[diceX][diceY];
                map[diceX][diceY]=0;
            }

            sb.append(dice[1][1]+"\n");
        }

        System.out.println(sb);
        br.close();
    }
}
