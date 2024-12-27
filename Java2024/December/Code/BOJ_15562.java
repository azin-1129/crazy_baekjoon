package Code;

import java.util.*;
import java.io.*;

class Gear{
    boolean isRotate;
    boolean rotateDir;
    int GEAR_SIZE=8;
    int[] gearStat;
    int[] gearStatIdx;

    public Gear(int[] stats){
        this.gearStat=new int[GEAR_SIZE];
        this.gearStatIdx=new int[GEAR_SIZE];

        for(int i=0;i<GEAR_SIZE;i++){ // 초기 gear idx
            gearStatIdx[i]=i;
        }

        for(int i=0;i<GEAR_SIZE;i++){
            gearStat[i]=stats[i];
        }
    }

    public String toString(){
        return "gear 상태:"+Arrays.toString(this.gearStat)+"\ngearStatIdx:"+Arrays.toString(this.gearStatIdx)+"\n";
    }

    public void setCommand(boolean isRotate, boolean rotateDir){
        this.isRotate=isRotate;
        this.rotateDir=rotateDir;
    }

    public int getGearStat(int idx){
        return this.gearStat[gearStatIdx[idx]];
    }

    public void rotate(){
        if(this.rotateDir){
            clock();
        }else{
            reverseClock();
        }
        this.isRotate=false;
    }

    private void clock(){
        // System.out.println("회전 이전:"+Arrays.toString(gearStatIdx));
        for(int i=0;i<GEAR_SIZE;i++){
            if(gearStatIdx[i]-1>=0){
                gearStatIdx[i]-=1;
            }else{
                gearStatIdx[i]=7;
            }
        }
        // System.out.println("회전 결과:"+Arrays.toString(gearStatIdx));
    }

    private void reverseClock(){
        // System.out.println("회전 이전:"+Arrays.toString(gearStatIdx));
        for(int i=0;i<GEAR_SIZE;i++){
            if(gearStatIdx[i]+1==GEAR_SIZE){
                gearStatIdx[i]=0;
            }else{
                gearStatIdx[i]+=1;
            }
        }
        // System.out.println("회전 결과:"+Arrays.toString(gearStatIdx));
    }
}

class BOJ_15562{
    static int root=0;
    static int left=6;
    static int right=2;
    public static void main(String[] args) throws Exception{
        String filepath=System.getProperty("user.dir")+"\\Input\\";
        int bojNum=15562;
        BufferedReader br=new BufferedReader(new FileReader(filepath+"input"+bojNum+".txt"));
        List<Integer> rotatingIdx;

        int T=Integer.parseInt(br.readLine());
        Gear[] gears=new Gear[T];
        for(int i=0;i<T;i++){
            int[] stats=Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();

            gears[i]=new Gear(stats); // 기어 정보 입력
        }
        
        int K=Integer.parseInt(br.readLine());
        for(int i=0;i<K;i++){
            rotatingIdx=new ArrayList<>(); // 커맨드 초기화
            int[] command=Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray(); // 톱니 번호는 1부터 시작
            // System.out.println((command[0])+"번째 톱니를 "+command[1]+" 방향으로 회전하는 "+(i+1)+" 번째 연산입니다.");
            int toRotateGearIdx=command[0]-1;
            boolean rotatePosition=command[1]>0;

            gears[toRotateGearIdx].setCommand(true, rotatePosition);
            rotatingIdx.add(toRotateGearIdx);

            // left side
            int l=Integer.valueOf(toRotateGearIdx)-1;

            while(l>=0){
                if(gears[l].getGearStat(right)!=gears[l+1].getGearStat(left) && gears[l+1].isRotate==true){
                    // System.out.println((l+1)+"번째 기어의 right인 "+gears[l].getGearStat(right)+"와 "+(l+2)+"번째 기어의 left인 "
                    // +gears[l+1].getGearStat(left)+"가 달라 "+!(gears[l+1].rotateDir)+" 방향으로 연쇄되어야 합니다.");

                    gears[l].setCommand(true, !(gears[l+1].rotateDir));
                    // System.out.println((l+1)+"번째 기어 연쇄 연산을 추가합니다.");
                    rotatingIdx.add(l);
                }
                l-=1;
            }

            // right size
            int r=Integer.valueOf(toRotateGearIdx)+1;
            while(r<T){
                if(gears[r].getGearStat(left)!=gears[r-1].getGearStat(right) && gears[r-1].isRotate==true){
                    // System.out.println((r+1)+"번째 기어의 left인 "+gears[r].getGearStat(left)+"와 "+(r)+"번째 기어의 right인 "
                    // +gears[r-1].getGearStat(right)+"가 달라 "+!(gears[r-1].rotateDir)+" 방향으로 연쇄되어야 합니다.");

                    gears[r].setCommand(true, !(gears[r-1].rotateDir));
                    // System.out.println((r+1)+"번째 기어 연쇄 연산을 추가합니다.");
                    rotatingIdx.add(r);
                }
                r+=1;
            }

            // System.out.println();
            // System.out.println("확정한 연산들을 수행합니다.");

            // 확정지은 연산들 수행
            for(int rIdx : rotatingIdx){
                // System.out.println((rIdx+1)+"번째 톱니를 회전합니다:");
                // System.out.println("ㅡㅡㅡㅡ기어 회전중ㅡㅡㅡㅡ");
                gears[rIdx].rotate();
                // System.out.println("ㅡㅡㅡㅡ기어 회전중ㅡㅡㅡㅡ");
            }
            // System.out.println("회전 완료.");
        }

        int count=0;
        for(int i=0;i<T;i++){
            if(gears[i].getGearStat(root)==1){
                count+=1;
            }
        }

        System.out.println(count);
        br.close();
    }
}