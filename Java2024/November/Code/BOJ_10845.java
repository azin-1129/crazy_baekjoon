package Code;

import java.util.*;
import java.io.*;

class BOJ_10845{
    public static void main(String[] args) throws Exception{
        String filepath=System.getProperty("user.dir")+"\\Input\\";
        int bojNum=10845;
        BufferedReader br=new BufferedReader(new FileReader(filepath+"input"+bojNum+".txt"));

        int N=Integer.parseInt(br.readLine());
        int[] queue=new int[N];
        int queueIdx=N-1;
        int popIdx=N-1;

        for(int n=0;n<N;n++){
            System.out.println("현재 array 상태");
            System.out.println(Arrays.toString(queue));

            String command=br.readLine();
            System.out.println("커맨드:"+command);

            // push
            if(command.length()>=4 && command.substring(0,4).equals("push")){
                String[] pushform=command.split(" ");

                queue[queueIdx]=Integer.parseInt(pushform[1]);
                queueIdx--;
            }else{
                switch(command){
                    case "pop":
                        if(queueIdx==N-1){
                            System.out.println(-1);
                        }else{
                            System.out.println(queue[popIdx]);
                            queue[popIdx]=0;
                            popIdx--;

                            if(queueIdx==popIdx){ // 비어버림. 초기화
                                popIdx=N-1;
                                queueIdx=N-1;
                            }
                        }
                        break;
                    case "size":
                        System.out.println(popIdx-queueIdx);
                        break;
                    case "empty":
                        if(queueIdx==N-1){
                            System.out.println(1);
                        }else{
                            System.out.println(0);
                        }
                        break;  
                    case "front":
                        if(queueIdx==N-1){
                            System.out.println(-1);
                        }else{
                            System.out.println(queue[popIdx]);
                        }
                        break;
                    case "back":
                        if(queueIdx==N-1){
                            System.out.println(-1);
                        }else{
                            System.out.println(queue[queueIdx+1]);
                        }
                        break;
                }
            }
        }
        br.close();
    }
}