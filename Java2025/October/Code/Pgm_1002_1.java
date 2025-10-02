package Code;

import java.util.*;

class Pgm_1002_1 {
    public static void main(String[] args) throws Exception{
        String[] survey=new String[]{
            "RT"
        };
        int[] choices=new int[]{
            1
        };
        System.out.println(solution(survey ,choices));
    }
    public static String solution(String[] survey, int[] choices) {
        // 0. choices 원소에 모두 -4 해서, 양/음 여부로 유형 선택지 판단
        int length=survey.length; // survey.length=choices.length
        for(int i=0;i<length;i++){
            choices[i]-=4;
        }

        // 1. 유형 별 점수를 관리할 LinkedHashMap 작성
        Map<Character, Integer> scores=new LinkedHashMap<>();
        scores.put('R', 0);
        scores.put('T', 0);

        scores.put('C', 0);
        scores.put('F', 0);

        scores.put('J', 0);
        scores.put('M', 0);

        scores.put('A', 0);
        scores.put('N', 0);

        // 2. 성격 유형 점수 계산
        // 응답 점수가 -면 원래 score+Math.abs(응답 점수), +면 원래 score+응답 점수
        for(int i=0;i<length;i++){
            int valueBefore=0;
            if(choices[i]<0){
                Character firstChar=survey[i].charAt(0);
                valueBefore=scores.get(firstChar);
                scores.put(firstChar, valueBefore+(int)Math.abs(choices[i]));
            }else if(choices[i]>0){
                Character secondChar=survey[i].charAt(1);
                valueBefore=scores.get(secondChar);
                scores.put(secondChar, valueBefore+choices[i]);
            }
        }
        
        // 3. scores 합산 정보를 기반으로 유형 출력
        int idx=0;
        Queue<Integer> calcScoreQ=new ArrayDeque<>();
        Queue<Character> calcCharacterQ=new ArrayDeque<>();
        String result="";
        for(Character key:scores.keySet()){
            calcScoreQ.offer(scores.get(key));
            calcCharacterQ.offer(key);
            idx++;
            if(idx!=0 && idx%2==0){ // 지표 패스
                int scoreTemp=-1;
                Character biggerChar='0';
                while(!calcScoreQ.isEmpty()){
                    int scoreNow=calcScoreQ.poll();
                    Character charNow=calcCharacterQ.poll();
                    if(scoreTemp<scoreNow){
                        scoreTemp=scoreNow;
                        biggerChar=charNow;
                    }
                }
                result+=biggerChar;
            }
        }

        return result;
    }
}