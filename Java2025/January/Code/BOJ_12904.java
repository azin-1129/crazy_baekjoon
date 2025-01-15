package Code;

import java.util.*;
import java.io.*;

class BOJ_12904{
    public static void main(String[] args) throws Exception{
        String filepath=System.getProperty("user.dir")+"\\Input\\";
        int bojNum=12904;
        BufferedReader br=new BufferedReader(new FileReader(filepath+"input"+bojNum+".txt"));

        String S=br.readLine();
        String T=br.readLine();

        StringBuffer sbT=new StringBuffer();

        sbT.append(T);

        int TIndex=T.length()-1;
        int SLength=S.length()-1;

        int result=0;
        while(TIndex>=SLength){
            String sbTInfo=sbT.toString();

            if(sbTInfo.equals(S)){
                result=1;
                break;
            }else{
                if((sbTInfo.charAt(TIndex))=='B'){
                    sbT.deleteCharAt(TIndex--);
                    sbT.reverse();
                }else{
                    sbT.deleteCharAt(TIndex--);
                }
            }
        }

        System.out.println(result);
    }
}