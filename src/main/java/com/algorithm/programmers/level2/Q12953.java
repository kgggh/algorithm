package com.algorithm.programmers.level2;

/*
N개의 최소공배수
문제 설명
두 수의 최소공배수(Least Common Multiple)란 입력된 두 수의 배수 중 공통이 되는 가장 작은 숫자를 의미합니다.
예를 들어 2와 7의 최소공배수는 14가 됩니다. 정의를 확장해서,
n개의 수의 최소공배수는 n 개의 수들의 배수 중 공통이 되는 가장 작은 숫자가 됩니다.
n개의 숫자를 담은 배열 arr이 입력되었을 때 이 수들의 최소공배수를 반환하는 함수, solution을 완성해 주세요.

제한 사항
arr은 길이 1이상, 15이하인 배열입니다.
arr의 원소는 100 이하인 자연수입니다.
입출력 예
arr	       result
[2,6,8,14]	168
[1,2,3]	6
 */
public class Q12953 {
    public static int solution(int[] arr) {
        return lcm(arr);
    }

    /*
    최소공배수 - 최대공약수를 활용해 구해진 공배수를 나눠줌
     */
    public static int lcm(int[] arr) {
        if (arr.length == 1) {
            return arr[0];
        }

        int gcd = gcd(arr[0], arr[1]);
        int lcm = (arr[0] * arr[1]) / gcd;

        for (int i = 2; i < arr.length; i++) {
            gcd = gcd(lcm, arr[i]);
            lcm = (lcm * arr[i]) / gcd;
        }

        return lcm;
    }

    /*
    최대공약수 - 반복문 혹은 재귀를 활용해 구할수 있음.
     */
    public static int gcd(int a, int b) {
        if (a % b == 0) {
            return b;
        }
        return gcd(b, a % b);
    }

    public static void main(String[] args) {
        int result = solution(new int[]{2, 6, 8, 14});
        System.out.println(result);
    }
}
