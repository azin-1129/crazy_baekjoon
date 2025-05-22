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
            // int cursor=0;
            LinkedList<String> list=new LinkedList<>();
            ListIterator<String> iter=list.listIterator();

            String[] inputs=br.readLine().split("");
            // System.out.println(Arrays.toString(inputs));
            for(int i=0;i<inputs.length;i++){
                // System.out.println(list);
                String current=inputs[i];

                switch(current){
                    case "-":
                        if(iter.hasPrevious()){
                            iter.previous();
                            iter.remove();
                        }
                        break;
                    case "<":
                        if(iter.hasPrevious()){
                            iter.previous();
                        }
                        break;
                    case ">":
                        if(iter.hasNext()){
                            iter.next();
                        }
                        break;
                    default:
                        iter.add(current);
                        // iter.next();
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