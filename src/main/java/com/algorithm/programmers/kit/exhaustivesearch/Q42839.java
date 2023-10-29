package com.algorithm.programmers.kit.exhaustivesearch;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * 코딩테스트 연습 > 완전탐색 >소수 찾기
 * 소수 찾기
 * 문제 설명
 * 한자리 숫자가 적힌 종이 조각이 흩어져있습니다. 흩어진 종이 조각을 붙여 소수를 몇 개 만들 수 있는지 알아내려 합니다.
 *
 * 각 종이 조각에 적힌 숫자가 적힌 문자열 numbers가 주어졌을 때, 종이 조각으로 만들 수 있는 소수가 몇 개인지 return 하도록 solution 함수를 완성해주세요.
 *
 * 제한사항
 * numbers는 길이 1 이상 7 이하인 문자열입니다.
 * numbers는 0~9까지 숫자만으로 이루어져 있습니다.
 * "013"은 0, 1, 3 숫자가 적힌 종이 조각이 흩어져있다는 의미입니다.
 * 입출력 예
 * numbers	return
 * "17"	3
 * "011"	2
 * 입출력 예 설명
 * 예제 #1
 * [1, 7]으로는 소수 [7, 17, 71]를 만들 수 있습니다.
 *
 * 예제 #2
 * [0, 1, 1]으로는 소수 [11, 101]를 만들 수 있습니다.
 *
 * 11과 011은 같은 숫자로 취급합니다.
 */
public class Q42839 {
    static Set<Integer> combinationNumbers = new HashSet<>();

    public static int solution(String numbers) {
        int answer = 0;

        // 1. 조합으로 모든 수 만들기
        recursive("", numbers);

        // 2. 소수인지 확인하기
        Iterator<Integer> iterator = combinationNumbers.iterator();
        while (iterator.hasNext()) {
            if(isPrime(iterator.next())) {
                answer++;
            }
        }

        return answer;
    }


    // 에라토스테네스의 체 활용
    private static boolean isPrime(Integer number) {
        if(number <= 1) {
            return false;
        }

        int limit = (int) Math.sqrt(number);
        for (int i = 2; i <= limit; i++) {
            if(number % i == 0) {
                return false;
            }
        }

        return true;
    }

    // 재귀함수 활용
    private static void recursive(String comb, String numbers) {
        if(!comb.equals("")) {
            combinationNumbers.add(Integer.parseInt(comb));
        }

        for (int i = 0; i < numbers.length(); i++) {
            recursive(comb + numbers.charAt(i), numbers.substring(0, i) + numbers.substring(i+1));
        }
    }

    public static void main(String[] args) {
        int result = solution("17");
        System.out.println(result);
    }
}
