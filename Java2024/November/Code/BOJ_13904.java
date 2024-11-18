package Code;

import java.util.*;
import java.io.*;

class BOJ_13904{
    static class Assignment implements Comparable<Assignment>{
        int due;
        int score;

        public Assignment(int due, int score){
            this.due=due;
            this.score=score;
        }

        @Override
        public int compareTo(Assignment assignment){
            return Integer.compare(assignment.score, this.score);
        }

        @Override
        public String toString(){
            return "[남은 일수: "+this.due+", 스코어: "+this.score+"]\n";
        }
    }
    public static void main(String[] args) throws Exception{
        String filepath=System.getProperty("user.dir")+"\\Input\\";
        int bojNum=13904;
        BufferedReader br=new BufferedReader(new FileReader(filepath+"input"+bojNum+".txt"));

        int N=Integer.parseInt(br.readLine());
        boolean[] check=new boolean[1001];

        PriorityQueue<Assignment> pq=new PriorityQueue<>();

        for(int n=0;n<N;n++){
            int[] inputs=Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            pq.offer(new Assignment(inputs[0], inputs[1])); // d : w
        }

        int total=0;

        // System.out.println(pq); // 실제 정렬된 결과와 다를 수 있음

        while(!pq.isEmpty()){
            Assignment next=pq.poll();

            if(!check[next.due]){
                total+=next.score;
                check[next.due]=true;
            }else{
                for(int i=next.due-1;i>=1;i--){
                    if(!check[i]){
                        total+=next.score;
                        check[i]=true;
                        break;
                    }
                }
            }
        }

        System.out.println(total);
        br.close();
    }
}