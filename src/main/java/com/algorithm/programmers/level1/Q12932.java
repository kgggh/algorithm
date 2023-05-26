package com.algorithm.programmers.level1;

/**
 * 자연수 뒤집어 배열로 만들기
 * 문제 설명
 * 자연수 n을 뒤집어 각 자리 숫자를 원소로 가지는 배열 형태로 리턴해주세요. 예를들어 n이 12345이면 [5,4,3,2,1]을 리턴합니다.
 *
 * 제한 조건
 * n은 10,000,000,000이하인 자연수입니다.
 * 입출력 예
 * n	return
 * 12345	[5,4,3,2,1]
 */
public class Q12932 {
    public static int[] solution(long n) {
        String num = String.valueOf(n);

        String[] splitNum = num.split("");
        int size = splitNum.length;
        int[] answer = new int[size];
        int j = 0;
        for (int i = size - 1; i >= 0; i--) {
            answer[j] = Integer.parseInt(splitNum[i]);
            j++;
        }

        return answer;
    }

    public static void main(String[] args) {
        int[] result = solution(12345);
    }
}
