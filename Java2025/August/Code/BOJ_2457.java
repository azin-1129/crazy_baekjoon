package Code;

import java.util.*;
import java.io.*;

class BOJ_2457{
    static class Flower{
        int[] bloom;
        int[] closed;

        public Flower(int bloomMonth, int bloomDay, int closedMonth, int closedDay){
            this.bloom=new int[]{bloomMonth, bloomDay};
            this.closed=new int[]{closedMonth, closedDay};
        }

        @Override
        public String toString(){
            return "핀 날:"+bloom[0]+"\\"+bloom[1]+", 진 날:"+closed[0]+"\\"+closed[1];
        }
    }
    static int result=Integer.MAX_VALUE;
    static int N;
    static Flower[] flowers;
    public static void main(String[] args) throws Exception{
        String filepath=System.getProperty("user.dir")+"\\Input\\";
        int bojNum=2457;
        BufferedReader br=new BufferedReader(new FileReader(filepath+"input"+bojNum+".txt"));

        N=Integer.parseInt(br.readLine());
        flowers=new Flower[N];
        StringTokenizer st;
        for(int i=0;i<N;i++){
            st=new StringTokenizer(br.readLine(), " ");
            flowers[i]=new Flower(
                Integer.parseInt(st.nextToken()),
                Integer.parseInt(st.nextToken()),
                Integer.parseInt(st.nextToken()),
                Integer.parseInt(st.nextToken())
            );
        }

        for(int i=0;i<N;i++){
            if(flowers[i].bloom[0]==12){ // 피는 날이 12월: 첫 꽃으로 선택 안함
                continue;
            }
            if(flowers[i].closed[0]==12){ // 지는 날이 12월일 때,
                if(flowers[i].bloom[0]>3 & flowers[i].bloom[1]>1){ // 피는 날이 3/1을 초과한다면 선택 안함
                    continue;
                }
            }
            System.out.println("우선 꽃"+i+"를 고릅니다.");
            choose(i, flowers[i].closed[0], flowers[i].closed[1], 1);
        }

        System.out.println(result);
        br.close();
    }
    static void choose(int idx, int closedMonth, int closedDay, int count){
        if(closedMonth==12){
            System.out.println(count+"개 골랐어요.");
            result=Math.min(result, count);
            return;
        }

        for(int i=(idx+1);i<N;i++){
            System.out.println(i+"번째 꽃을 고려하고 있습니다.");
            if(isNextFlowerEnabled(flowers[idx], flowers[i])){
                System.out.println("다음은 "+i+"꽃입니다.");
                choose(i, flowers[i].closed[0], flowers[i].closed[1], count+1);
            }
        }
    }
    static boolean isNextFlowerEnabled(Flower origin, Flower next){
        int originClosedMonth=origin.closed[0];
        int originClosedDay=origin.closed[1];
        int nextBloomMonth=next.bloom[0];
        int nextBloomDay=next.bloom[1];
        int nextBloomEndMonth=next.closed[0];
        int nextBloomEndDay=next.closed[1]-1;

        if(nextBloomEndDay==0){ // 이전 월로 교체해야 한다.
            switch(nextBloomEndMonth){
                case 2:
                    nextBloomEndDay=31;
                    break;
                case 3:
                    nextBloomEndDay=28;
                    break;
                case 4:
                    nextBloomEndDay=31;
                    break;
                case 5:
                    nextBloomEndDay=30;
                    break;
                case 6:
                    nextBloomEndDay=31;
                    break;
                case 7:
                    nextBloomEndDay=30;
                    break;
                case 8:
                    nextBloomEndDay=31;
                    break;
                case 9:
                    nextBloomEndDay=31;
                    break;
                case 10:
                    nextBloomEndDay=30;
                    break;
                case 11:
                    nextBloomEndDay=30;
                    break;
                case 12:
                    nextBloomEndDay=30;
                    break;
            }
            nextBloomEndMonth-=1;
        }

        System.out.println(origin+" 정보");
        System.out.println(originClosedMonth+"/"+originClosedDay);
        System.out.println(next+" 정보");
        System.out.println(nextBloomMonth+"/"+nextBloomDay);
        System.out.println(nextBloomEndMonth+"/"+nextBloomEndDay);
        // origin 지는 날이 next의 핀 날 이상, 지는 날-1 이하인가?
        if(checkDateRight(originClosedMonth, originClosedDay, nextBloomMonth, nextBloomDay)
        & checkDateLeft(originClosedMonth, originClosedDay, nextBloomEndMonth, nextBloomEndDay)){
            return true;
        }

        return false;
    }
    static boolean checkDateLeft(int om, int od, int nm, int nd){
        if(om<=nm){
            if(om==nm){
                if(nd>=od){
                    return true;
                }
            }else{
                return true;
            }
        }

        return false;
    }
    static boolean checkDateRight(int om, int od, int nm, int nd){
        if(om>=nm){
            if(om==nm){
                if(nd<=od){
                    return true;
                }
            }else{
                return true;
            }
        }

        return false;
    }
}