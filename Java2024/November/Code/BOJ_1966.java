package Code;

import java.util.*;
import java.io.*;

class BOJ_1966{
    static class Document{
        int index;
        int weight;

        Document(int index, int weight){
            this.index=index;
            this.weight=weight;
        }

        @Override
        public String toString(){
            return "(idx:"+this.index+", weight:"+this.weight+")";
        }
    }
    public static void main(String[] args) throws Exception{
        String filepath=System.getProperty("user.dir")+"\\Input\\";
        int bojNum=1966;
        BufferedReader br=new BufferedReader(new FileReader(filepath+"input"+bojNum+".txt"));

        int T=Integer.parseInt(br.readLine());

        for(int t=1;t<=T;t++){
            // System.out.println("Test Case:"+t);
            int[] inputs=Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int N=inputs[0]; // 문서 수
            int docIdx=inputs[1]; // 찾고자 하는 문서 인덱스
            int[] weights=Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            Queue<Document> q=new ArrayDeque<>();

            for(int i=0;i<N;i++){
                q.offer(new Document(i, weights[i]));
            }

            int printIdx=0;

            int maxIdx=N-1;
            Arrays.sort(weights);

            while(!q.isEmpty()){
                // System.out.println(q.toString());

                Document current=q.poll();

                if(!q.isEmpty() && current.weight<weights[maxIdx]){
                    // System.out.println("뒤로 뺌");
                    q.offer(current);
                }else{
                    // System.out.println("출력중");
                    printIdx+=1;
                    maxIdx-=1; // 큰 값부터 출력
                    if(current.index==docIdx){
                        break;
                    }
                }
            }
            System.out.println(printIdx);
        }

        br.close();
    }
}