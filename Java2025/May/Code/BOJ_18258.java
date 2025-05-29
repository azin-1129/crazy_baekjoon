package Code;

import java.util.*;
import java.io.*;

class BOJ_18258{
    public static void main(String[] args) throws Exception{
        String filepath=System.getProperty("user.dir")+"\\Input\\";
        int bojNum=18258;
        BufferedReader br=new BufferedReader(new FileReader(filepath+"input"+bojNum+".txt"));

        int N=Integer.parseInt(br.readLine());
        Deque<Integer> deq=new ArrayDeque<>();
        StringTokenizer st;
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<N;i++){
            st=new StringTokenizer(br.readLine(), " ");
            String command=st.nextToken();

            if(command.equals("push")){
                int value=Integer.parseInt(st.nextToken());
                deq.offer(value);
            }else{
                boolean isEmpty=deq.isEmpty();
                switch(command){
                    case "pop":
                        if(!isEmpty){
                            sb.append(deq.pollFirst());
                        }else{
                            sb.append(-1);
                        }
                        break;
                    case "size":
                        sb.append(deq.size());
                        break;
                    case "empty":
                        if(isEmpty){
                            sb.append(1);
                        }else{
                            sb.append(0);
                        }
                        break;
                    case "front":
                        if(!isEmpty){
                            sb.append(deq.peekFirst());
                        }else{
                            sb.append(-1);
                        }
                        break;
                    case "back":
                        if(!isEmpty){
                            sb.append(deq.peekLast());
                        }else{
                            sb.append(-1);
                        }
                        break;
                }
            }
            if(!command.equals("push")){
                sb.append("\n");
            }
        }
        if(sb.length()>0){
            sb.deleteCharAt(sb.length()-1);
        }
        System.out.println(sb);
        br.close();
    }
}