package Code;

import java.util.*;
import java.io.*;

class BOJ_3613 {
    public static void main(String[] args) throws Exception {
        String filepath = System.getProperty("user.dir") + "\\Input\\";
        int bojNum=3613;
        BufferedReader br = new BufferedReader(new FileReader(filepath + "input" + bojNum + ".txt"));

        String str=br.readLine();
        StringBuilder sb=new StringBuilder();
        // cpp 변수명 규칙 위배 검사
        if(str.charAt(0)=='_' || str.charAt(str.length()-1)=='_'){
            sb=new StringBuilder("Error!");
        }else{
            if(str.contains("_")){ // C언어 변수
                boolean upperFlag=false;
                for(int i=0;i<str.length();i++){
                    char ch=str.charAt(i);
                    if(Character.isUpperCase(ch)){
                        sb=new StringBuilder("Error!");
                        break;
                    }
                    if(ch=='_'){
                        if(str.charAt(i-1)=='_'){
                            sb=new StringBuilder("Error!");
                            break;
                        }
                        upperFlag=true;
                    }else{
                        if(upperFlag){
                            sb.append(Character.toUpperCase(ch));
                            upperFlag=false;
                        }else{
                            sb.append(ch);
                        }
                    }
                }
            }else if(Character.isLowerCase(str.charAt(0))){ // Java 변수
                for(int i=0;i<str.length();i++){
                    char ch=str.charAt(i);
                    if(Character.isUpperCase(ch)){
                        sb.append("_");
                        sb.append(Character.toLowerCase(ch));
                    }else{
                        sb.append(ch);
                    }
                }
            }else{
                sb=new StringBuilder("Error!");
            }
        }

        System.out.println(sb);
        br.close();
    }
}