package Code;

import java.util.*;

class Pgm_1001_1 {
    public static void main(String[] args) throws Exception{
        int[] queue1=new int[]{1, 3, 4};
        int[] queue2=new int[]{1, 2, 4};

        System.out.println(solution(queue1, queue2));
    }
    public static int solution(int[] queue1, int[] queue2) {
        Queue<Integer>[] qs=new ArrayDeque[3];
        qs[1]=new ArrayDeque<>();
        qs[2]=new ArrayDeque<>();

        // 1. 합이 더 큰 큐 찾기
        int from=0;
        int to=0;
        long[] sumQ=new long[3];
        for(int value:queue1){
            sumQ[1]+=value;
            qs[1].offer(value);
        }
        for(int value:queue2){
            sumQ[2]+=value;
            qs[2].offer(value);
        }
        if(sumQ[1]>sumQ[2]){
            from=1;
            to=2;
        }else if(sumQ[1]<sumQ[2]){
            from=2;
            to=1;
        }else{ // 이미 절반 값
            return 0;
        }

        // 2. pop-insert 연산
        long half=(sumQ[1]+sumQ[2])/2;
        // System.out.println("절반 값은 "+half+"입니다.");
        int count=0;
        while(true){
            while(sumQ[from]>half){
                int pollValue=qs[from].poll();
                qs[to].offer(pollValue);
                sumQ[from]-=pollValue;
                sumQ[to]+=pollValue;
                // System.out.println(from+"번 Q에서 "+to+"번 Q로 보냅니다.");
                // System.out.println(from+"번 합:"+sumQ[from]+", "+to+"번 합:"+sumQ[to]);
                count++;
                if(count==(queue1.length*4)){ // 각 큐의 모든 원소가 가능한 위치의 수는 n*4
                    return -1;
                }
            }
            if(sumQ[from]==sumQ[to]){
                return count;
            }else{ // from, to swap
                if(qs[from].isEmpty()){
                    // System.out.println("q가 비었습니다.");
                    return -1;
                }
                int temp=from;
                from=to;
                to=temp;
            }
        }
    }
}