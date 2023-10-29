package com.algorithm.programmers.kit.exhaustivesearch;

import java.util.*;

/**
 * 완전탐색 > 모의고사
 * 문제 설명
 * 수포자는 수학을 포기한 사람의 준말입니다. 수포자 삼인방은 모의고사에 수학 문제를 전부 찍으려 합니다. 수포자는 1번 문제부터 마지막 문제까지 다음과 같이 찍습니다.
 *
 * 1번 수포자가 찍는 방식: 1, 2, 3, 4, 5, 1, 2, 3, 4, 5, ...
 * 2번 수포자가 찍는 방식: 2, 1, 2, 3, 2, 4, 2, 5, 2, 1, 2, 3, 2, 4, 2, 5, ...
 * 3번 수포자가 찍는 방식: 3, 3, 1, 1, 2, 2, 4, 4, 5, 5, 3, 3, 1, 1, 2, 2, 4, 4, 5, 5, ...
 *
 * 1번 문제부터 마지막 문제까지의 정답이 순서대로 들은 배열 answers가 주어졌을 때, 가장 많은 문제를 맞힌 사람이 누구인지 배열에 담아 return 하도록 solution 함수를 작성해주세요.
 *
 * 제한 조건
 * 시험은 최대 10,000 문제로 구성되어있습니다.
 * 문제의 정답은 1, 2, 3, 4, 5중 하나입니다.
 * 가장 높은 점수를 받은 사람이 여럿일 경우, return하는 값을 오름차순 정렬해주세요.
 * 입출력 예
 * answers	return
 * [1,2,3,4,5]	[1]
 * [1,3,2,4,2]	[1,2,3]
 * 입출력 예 설명
 * 입출력 예 #1
 *
 * 수포자 1은 모든 문제를 맞혔습니다.
 * 수포자 2는 모든 문제를 틀렸습니다.
 * 수포자 3은 모든 문제를 틀렸습니다.
 * 따라서 가장 문제를 많이 맞힌 사람은 수포자 1입니다.
 *
 * 입출력 예 #2
 *
 * 모든 사람이 2문제씩을 맞췄습니다.
 */
public class Q42840 {

    public static int[] solution(int[] answers) {
        int[] firstStudentPattern =  {1, 2, 3, 4, 5}; //5
        int[] secondStudentPattern = {2, 1, 2, 3, 2, 4, 2, 5}; //8
        int[] thirdStudentPattern =  {3, 3, 1, 1, 2, 2, 4, 4, 5, 5}; // 10

        Map<Integer, Integer> studentScore = new HashMap<>();

        // 1. 학생들 방식과 정답 비교
        for (int i = 0; i < answers.length; i++) {
            int answer = answers[i];

            if(firstStudentPattern[i % 5]  == answer) {
                studentScore.put(1, studentScore.getOrDefault(1, 0) + 1);

            }
            if(secondStudentPattern[i % 8] == answer) {
                studentScore.put(2, studentScore.getOrDefault(2, 0) + 1);
            }

            if(thirdStudentPattern[i % 10]  == answer) {
                studentScore.put(3, studentScore.getOrDefault(3, 0) + 1);
            }
        }

        // 2. 최대점수 찾기
        int max = 0;
        for (Map.Entry<Integer, Integer> entry : studentScore.entrySet()) {
            if(entry.getValue() >= max) {
                max = entry.getValue();
            }
        }

        // 3. 최대점수와 비교, 동일점수일시 그룹에 추가
        List<Integer> answer = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : studentScore.entrySet()) {
            Integer student = entry.getKey();

            if(entry.getValue() == max) {
                answer.add(student);
            }
        }

        // 4. 동일 점수일시 오름차순 정렬
        Collections.sort(answer);

        int[] result = new int[answer.size()];

        for (int i = 0; i < answer.size(); i++) {
            result[i] = answer.get(i);
        }

        return result;
    }

    public static void main(String[] args) {
        solution(new int[]{1,2,3,4,5, 6});
    }
}
