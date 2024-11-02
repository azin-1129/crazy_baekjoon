package Code;
import java.util.*;
import java.io.*;

class BOJ_17299{
    public static void main(String[] args) throws Exception{
        String filepath=System.getProperty("user.dir")+"\\";
        int bojNum=17299;
        BufferedReader br=new BufferedReader(new FileReader(filepath+"input"+bojNum+".txt"));
        StringBuilder sb=new StringBuilder();

        PriorityQueue<int[]> pq=new PriorityQueue<>(new Comparator<int[]>(){
            @Override
            public int compare(int[] o1, int[] o2){
                return Integer.compare(o1[0], o2[0]);
            }
        });

        int N=Integer.parseInt(br.readLine());
        int[] numbers=Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] status=new int[1000001];
        int[] result=new int[1000001];

        for(int i=0;i<N;i++){
            status[numbers[i]]+=1;
        }

        for(int i=N-1;i>=0;i--){
            while(!pq.isEmpty() && pq.peek()[0]<=status[numbers[i]]){
                pq.poll(); // 최소 힙
            }

            if(pq.isEmpty()){
                result[i]=-1; // 현재보다 빈도 잦은 수 없음
            }else{
                result[i]=pq.peek()[1];
            }

            pq.offer(new int[]{status[numbers[i]], numbers[i]});
        }

        for(int i=0;i<N;i++){
            sb.append(result[i]+" ");
        }

        System.out.print(sb);

        br.close();
    }
}