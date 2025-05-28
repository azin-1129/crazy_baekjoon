package Code;

import java.util.*;
import java.io.*;

class BOJ_2493{
    static class Top implements Comparable<Top>{
        int number;
        int height;

        public Top(int number, int height){
            this.number=number;
            this.height=height;
        }

        @Override
        public String toString(){
            return this.number+"번 탑의 높이:"+this.height;
        }

        @Override
        public int compareTo(Top t){
            return this.height-t.height;
        }
    }

    public static void main(String[] args) throws Exception{
        String filepath=System.getProperty("user.dir")+"\\Input\\";
        int bojNum=2493;
        BufferedReader br=new BufferedReader(new FileReader(filepath+"input"+bojNum+".txt"));

        int N=Integer.parseInt(br.readLine());
        int[] result=new int[N+1]; // 탑 번호는 1부터 시작
        Stack<Integer> stack=new Stack<>();
        PriorityQueue<Top> pq=new PriorityQueue<>();
        StringTokenizer st=new StringTokenizer(br.readLine(), " ");
        while(st.hasMoreTokens()){
            stack.push(Integer.parseInt(st.nextToken()));
        }

        for(int currentTopNumber=N;currentTopNumber>1;currentTopNumber--){
            System.out.println("최소힙 상태:"+pq);
            int currentTopHeight=stack.pop();
            int preTopHeight=stack.peek();

            while(!pq.isEmpty()){
                if(pq.peek().height<preTopHeight){
                    result[pq.poll().number]=currentTopNumber-1;
                }else{
                    break;
                }
            }
            if(preTopHeight>currentTopHeight){
                result[currentTopNumber]=currentTopNumber-1;
            }else{
                pq.add(new Top(currentTopNumber, currentTopHeight));
            }

            // 필요 : 대기 stack, 결과 result arr
            // 근데 대기를 stack으로 처리했을 때, 수신 처리가 안 되는 작은 탑들이 존재할 거 같다.
            // 그럼 대기를 최소 힙 사용하면 어떨까..

            // 왼쪽(peek)이 현재보다 크다? 수신 처리. result[현재]=왼쪽
            // 왼쪽(peek)이 현재 이하이다? 대기 wait.push(현재)
            // 대기스택 원소가 수신 가능할 때 까지만 pop: while(!pq.isEmpty()) 걸어놓고, prev가 최소힙 peek()값 초과일 때까지만 poll()

            // 수신 불가는 어떻게 처리하지..? 원래 스택이 비었을 때, 대기 스택에 있는 애들에 한해서 result[현재]=0 하면 될 듯. 근데 걍 놔둬도 되겠다
        }
        StringBuilder sb=new StringBuilder();
        for(int i=1;i<=N;i++){
            sb.append(result[i]+" ");
        }
        sb.deleteCharAt(sb.length()-1);
        System.out.println(sb);
        br.close();
    }
}