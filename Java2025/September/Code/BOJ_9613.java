package Code;

import java.util.*;
import java.io.*;

class BOJ_9613{
    static int result;
    static int MAX_VAL=1000000;
    static int COMB_SIZE=2;
    static int N;
    static int[] combResult=new int[COMB_SIZE];
    static int[] numbers;
    static ArrayList<List<Integer>> divisors;
    public static void main(String[] args) throws Exception{
        String filepath=System.getProperty("user.dir")+"\\Input\\";
        int bojNum=9613;
        BufferedReader br=new BufferedReader(new FileReader(filepath+"input"+bojNum+".txt"));

        divisors=new ArrayList<>();
        for(int i=0;i<=MAX_VAL;i++){
            divisors.add(new ArrayList<>());
        }

        StringBuilder sb=new StringBuilder();
        int T=Integer.parseInt(br.readLine());
        for(int t=0;t<T;t++){
            result=0;

            StringTokenizer st=new StringTokenizer(br.readLine(), " ");
            N=Integer.parseInt(st.nextToken());
            numbers=new int[N];
            for(int i=0;i<N;i++){
                numbers[i]=Integer.parseInt(st.nextToken());
            }

            comb(0, 0);
            sb.append(result+"\n");
        }

        sb.deleteCharAt(sb.length()-1);
        System.out.println(sb);
        br.close();
    }
    static void comb(int start, int depth){
        if(depth==COMB_SIZE){ // 2개 조합 완성. GCD 진행
            gcd();
            return;
        }

        for(int i=start;i<N;i++){
            combResult[depth]=numbers[i];
            comb(i+1, depth+1);
        }
    }
    static void gcd(){
        // System.out.println("조합 결과:"+Arrays.toString(combResult));
        if(divisors.get(combResult[0]).isEmpty()){
            for(int i=1;i<=Math.sqrt(combResult[0]);i++){
                if((i*i)==combResult[0]){
                    divisors.get(combResult[0]).add(i);
                    continue;
                }

                if(combResult[0]%i==0){
                    divisors.get(combResult[0]).add(i);
                    divisors.get(combResult[0]).add(combResult[0]/i);
                }
            }
            Collections.sort(divisors.get(combResult[0]), Collections.reverseOrder());
        }
        if(divisors.get(combResult[1]).isEmpty()){
            for(int i=1;i<=Math.sqrt(combResult[1]);i++){
                if((i*i)==combResult[1]){
                    divisors.get(combResult[1]).add(i);
                    continue;
                }

                if(combResult[1]%i==0){
                    divisors.get(combResult[1]).add(i);
                    divisors.get(combResult[1]).add(combResult[1]/i);
                }
            }
            Collections.sort(divisors.get(combResult[1]), Collections.reverseOrder());
        }

        // System.out.println(combResult[0]+"약수:"+divisors.get(combResult[0]));
        // System.out.println(combResult[1]+"약수:"+divisors.get(combResult[1]));

        if(combResult[0]==combResult[1]){
            result+=combResult[0];
        }else if(combResult[0]>combResult[1]){
            List<Integer> smaller=divisors.get(combResult[1]);
            for(int divisor:divisors.get(combResult[0])){
                if(smaller.contains(divisor)){
                    result+=divisor;
                    break;
                }
            }
        }else{
            List<Integer> smaller=divisors.get(combResult[0]);
            for(int divisor:divisors.get(combResult[1])){
                if(smaller.contains(divisor)){
                    result+=divisor;
                    break;
                }
            }
        }
    }
}