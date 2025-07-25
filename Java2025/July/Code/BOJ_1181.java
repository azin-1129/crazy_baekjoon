package Code;

import java.util.*;
import java.io.*;

class BOJ_1181{
    static class Word{
        String word;
        int length;
        int[] weight;

        public Word(String word){
            this.word=word;
            this.length=word.length();
            this.weight=new int[word.length()];
            for(int i=0;i<word.length();i++){
                this.weight[i]=Integer.valueOf(word.charAt(i));
            }
        }

        @Override
        public String toString(){
            return "Length:"+length+", Weight:"+weight;
        }
    }
    public static void main(String[] args) throws Exception{
        String filepath=System.getProperty("user.dir")+"\\Input\\";
        int bojNum=1181;
        BufferedReader br=new BufferedReader(new FileReader(filepath+"input"+bojNum+".txt"));
        PriorityQueue<Word> pq=new PriorityQueue<>(new Comparator<Word>(){
            @Override
            public int compare(Word a, Word b){
                if(a.length!=b.length){
                    return Integer.compare(a.length, b.length);
                }else{
                    for(int i=0;i<a.length;i++){
                        if(a.weight[i]!=b.weight[i]){
                            return Integer.compare(a.weight[i], b.weight[i]);
                        }
                    }
                    return 0;
                }
            }
        });

        Set<String> hs=new HashSet<>();
        int N=Integer.parseInt(br.readLine());
        for(int i=0;i<N;i++){
            String str=br.readLine();
            if(hs.contains(str)){
                continue;
            }
            pq.offer(new Word(str));
            hs.add(str);
        }

        while(!pq.isEmpty()){
            System.out.println(pq.poll().word);
        }
        br.close();
    }
}