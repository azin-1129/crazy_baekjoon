package Code;

import java.util.*;

class Pgm_0930_1 {
    public static void main(String[] args) throws Exception{
        int[] fees=new int[]{180, 5000, 10, 600};
        String[] records=new String[]{
            "05:34 5961 IN",
            "06:00 0000 IN",
            "06:34 0000 OUT",
            "07:59 5961 OUT",
            "07:59 0148 IN",
            "18:59 0000 IN",
            "19:09 0148 OUT",
            "22:59 5961 IN",
            "23:00 5961 OUT"
        };

        System.out.println(Arrays.toString(solution(fees, records)));
    }
    public static int[] solution(int[] fees, String[] records) {
        List<Integer> costs=new ArrayList<>();
        
        // 0. [n][3] 형태로 변환
        int[][] infos=new int[records.length][3];
        int idx=0;
        for(String rec:records){
            int hour=Integer.valueOf(rec.substring(0,2));
            int minute=Integer.valueOf(rec.substring(3,5));
            int minutes=hour*60+minute;
            int carNum=Integer.valueOf(rec.substring(6,10));
            int io=0;
            if(rec.charAt(11)=='I'){
                io=1;
            }
            infos[idx][0]=minutes;
            infos[idx][1]=carNum;
            infos[idx][2]=io;
            idx++;
        }
        // 1. 차량번호 오름차순, 시간 내림차순
        Arrays.sort(infos, new Comparator<int[]>(){
            @Override
            public int compare(int[] o1, int[] o2){
                if(o1[1]==o2[1]){
                    return o2[0]-o1[0];
                }else{
                    return o1[1]-o2[1];
                }
            }
        });
        
        // 2. 현 차량 번호가 일치할 때까지만 Queue offer
        Queue<int[]> q=new ArrayDeque<>();
        int originCarNum=infos[0][1];
        idx=0;
        System.out.println("record 길이:"+records.length);
        while(idx<records.length){
            System.out.println(idx);
            int ioCount=0;
            while(idx<records.length && infos[idx][1]==originCarNum){
                q.offer(infos[idx++]);
                ioCount++;
            }

            if(idx<records.length){
                originCarNum=infos[idx][1];
            }
            
            // 3. IN/OUT 짝 맞추어 23:59 출차기록 추가
            if(ioCount%2!=0){
                q.offer(new int[]{1439, infos[idx-1][1], 0});
            }
            
            // 계산 큐 완성
            System.out.println("현 계산 큐 내용.");
            
            // 4. 현 차량에 대해 계산큐 연산
            int runtime=0;
            while(!q.isEmpty()){
                int[] info=q.poll();
                
                // 현 pop() 원소가 IN인 경우 +, OUT인 경우 -
                if(info[2]==0){
                    runtime+=info[0];
                }else{
                    runtime-=info[0];
                }
            }
            
            // fees={기본 시간(분), 기본 요금(원), 단위 시간(분), 단위 요금(원)}
            // 5. 가감한 값 기준으로 주차요금 연산
            // 연산 식: 기본 요금+[(총 주차(분)-기본시간)/단위시간]*단위요금
            System.out.println(runtime);
            int cost=0;
            if((runtime-fees[0])<=0){
                cost=fees[1];
            }else{
                if((runtime-fees[0])%fees[2]!=0){ // if 누적 주차시간<=기본? 기본요금
                    System.out.println("ceiling 연산을 진행한다.");
                    cost=fees[1]+(int)Math.ceil((double)(runtime-fees[0])/fees[2])*fees[3];
                    System.out.println(fees[1]+"+"+"ceiling"+runtime+"-"+fees[0]+"/"+fees[2]+"*"+fees[3]);
                    System.out.println("cost:"+cost);
                }else{ // else Math.ceiling 처리
                    cost=fees[1]+((runtime-fees[0])/fees[2])*fees[3];
                }
            }

            costs.add(cost);
        }
        // 6. result 배열에 결과 추가
        int[] answer = new int[costs.size()];
        idx=0;
        for(int cost:costs){
            answer[idx++]=cost;
        }
        return answer;
    }
}