package Code;

import java.util.*;
import java.io.*;

class BOJ_1541{
    static Queue<Character> q=new ArrayDeque<>();
    public static void main(String[] args) throws Exception{
        String filepath=System.getProperty("user.dir")+"\\Input\\";
        int bojNum=1541;
        BufferedReader br=new BufferedReader(new FileReader(filepath+"input"+bojNum+".txt"));

        String input=br.readLine();
        for(int i=0;i<input.length();i++){
            q.offer(input.charAt(i));
        }
        int result=parseNumber();

        while(!q.isEmpty()){
            char c=q.peek();
            switch(c){
                case '+':
                    q.poll();
                    result+=parseNumber();
                    break;
                case '-':
                q.poll();
                    result-=parseBulk();
                    break;
            }
        }

        System.out.println(result);
        br.close();
    }
    static int parseNumber(){
        String temp="";

        while(!q.isEmpty()){
            char c=q.peek();
            if(c>57 || c<48){
                break;
            }
            temp+=q.poll();
        }
        // System.out.println(temp);
        return Integer.valueOf(temp);
    }
    static int parseBulk(){
        int temp=parseNumber();
        while(!q.isEmpty()){
            char c=q.poll();
            if(c==43){ // +
                temp+=parseNumber();
            }else if(c==45){ // -
                System.out.println("벌크값:"+temp);
                return temp;
            }
        }

        return temp;
    }
}