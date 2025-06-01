package Code;

import java.io.*;
import java.util.*;

class BOJ_1021{
    static Deque<Integer> dq;
    static int[] idxs;
    static int calc2Count=0;
    static int calc3Count=0;
    static int M;
    public static void main(String[] args) throws Exception {
        String filepath=System.getProperty("user.dir")+"\\Input\\";
        int bojNum=1021;
        BufferedReader br=new BufferedReader(new FileReader(filepath+"input"+bojNum+".txt"));
        StringTokenizer st;

        st=new StringTokenizer(br.readLine(), " ");
        int N=Integer.parseInt(st.nextToken());
        M=Integer.parseInt(st.nextToken());

        dq=new ArrayDeque<>();
        for(int i=1;i<=N;i++){
            dq.offer(i);
        }

        idxs=new int[M];
        st=new StringTokenizer(br.readLine(), " ");
        for(int i=0;i<M;i++){
            idxs[i]=Integer.parseInt(st.nextToken())-1; // 번호가 1부터 주어졌으므로 -1
        }

        for(int i=0;i<M;i++){
            // 연산 시, i+1<M idx 배열에 대한 연산이 필요
            int idx=idxs[i];

            // 큐 내 좌, 우 중 어디에 가까운가?
            int dqSize=dq.size();
            int middle=dqSize/2;

            System.out.println("dq 내 찾고자 하는 값의 idx:"+idx+", middle:"+middle+", dq Size:"+dqSize);
            System.out.println("idxs 상태:"+Arrays.toString(idxs));
            System.out.println(dq);
            if(middle>idx){ // 좌
                for(int j=0;j<idx;j++){
                    calc2();
                }
                calc1(i);

                for(int j=i+1;j<M;j++){
                    idxs[j]=idxs[j]-idx;
                    if(idxs[j]<0){
                        idxs[j]=(dqSize+idxs[j]);
                    }
                }
            }else if(middle<idx){ // 우
                for(int j=0;j<(dqSize-idx);j++){
                    calc3();
                }
                calc1(i);

                for(int j=i+1;j<M;j++){
                    idxs[j]=idxs[j]+(dqSize-idx);
                    if(idxs[j]>=dqSize){
                        idxs[j]=(idxs[j]-dqSize);
                    }
                }
            }else{ // 가운데라면, 2번 연산 수행
                for(int j=0;j<idx;j++){
                    calc2();
                }
                calc1(i);

                for(int j=i+1;j<M;j++){
                    idxs[j]=idxs[j]-idx;
                    if(idxs[j]<0){
                        idxs[j]=(dqSize+idxs[j]);
                    }
                }
            }
            System.out.println("left:"+calc2Count+", right:"+calc3Count);
            System.out.println();
        }

        System.out.println(calc2Count+calc3Count);
        br.close();
    }
    static void calc1(int idx){
        dq.pollFirst();

        for(int i=idx;i<M;i++){
            idxs[i]-=1;
        }
    }
    static void calc2(){
        int x=dq.pollFirst();
        dq.offerLast(x);
        calc2Count+=1;
    }
    static void calc3(){
        int x=dq.pollLast();
        dq.offerFirst(x);
        calc3Count+=1;
    }
}