import java.util.*;
import java.io.*;

class BOJ_17299{
    public static void main(String[] args) throws Exception{
        String filepath=System.getProperty("user.dir")+"\\";
        int bojNum=17299;
        BufferedReader br=new BufferedReader(new FileReader(filepath+"input"+bojNum+".txt"));

        Stack<Integer> stack=new Stack<>();
        PriorityQueue<int[]> pq=new PriorityQueue<>(new Comparator<int[]>(){
            @Override
            public int compare(int[] o1, int[] o2){
                return Integer.compare(o1[0], o2[0]);
            }
        });

        int N=Integer.parseInt(br.readLine());
        int[] numbers=Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] status=new int[1000001];

        for(int i=0;i<numbers.length;i++){
            status[numbers[i]]+=1;
            stack.push(numbers[i]);
        }

        while(!stack.isEmpty()){
            int current=stack.pop();
            if(status[current]>=maximum){ // 이러면 현재 status 1일 때 2 3이면 답없
                System.out.print("-1 ");
            }else{
                System.out.println();
            }
        }

        br.close();
    }
}