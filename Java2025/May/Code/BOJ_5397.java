package Code;

import java.util.*;
import java.io.*;

class BOJ_5397{
    public static void main(String[] args) throws Exception{
        String filepath=System.getProperty("user.dir")+"\\Input\\";
        int bojNum=5397;
        BufferedReader br=new BufferedReader(new FileReader(filepath+"input"+bojNum+".txt"));

        int tc=Integer.parseInt(br.readLine());

        StringBuilder sb=new StringBuilder();
        for(int t=0;t<tc;t++){
            int cursor=0;
            LinkedList<String> list=new LinkedList<>();

            String[] inputs=br.readLine().split("");
            // System.out.println(Arrays.toString(inputs));
            for(int i=0;i<inputs.length;i++){
                // System.out.println(list);
                String current=inputs[i];

                switch(current){
                    case "-":
                        if(cursor>=1){
                            list.remove(cursor-1);
                            cursor-=1;
                        }
                        break;
                    case "<":
                        if(cursor>0){
                            cursor-=1;
                        }
                        break;
                    case ">":
                        if(cursor!=list.size()){
                            cursor+=1;
                        }
                        break;
                    default:
                        if(cursor==list.size()){
                            list.add(current);
                            cursor+=1;
                        }else{
                            // System.out.println(cursor+"에 문자 "+current+"를 삽입했습니다.");
                            list.add(cursor, current);
                            cursor+=1;
                        }
                        break;
                }
            }
            // System.out.println(list.toString().replaceAll("[\\[\\], ]",""));
            Iterator<String> it=list.iterator();
            while(it.hasNext()){
                sb.append(it.next());
            }
            sb.append("\n");
        }
        sb.deleteCharAt(sb.length()-1);
        System.out.println(sb);
        br.close();
    }
}