package com.algorithm.programmers.kit.hash;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 해시 > 베스트앨범
 *
 * 베스트앨범
 * 문제 설명
 * 스트리밍 사이트에서 장르 별로 가장 많이 재생된 노래를 두 개씩 모아 베스트 앨범을 출시하려 합니다. 노래는 고유 번호로 구분하며, 노래를 수록하는 기준은 다음과 같습니다.
 *
 * 속한 노래가 많이 재생된 장르를 먼저 수록합니다.
 * 장르 내에서 많이 재생된 노래를 먼저 수록합니다.
 * 장르 내에서 재생 횟수가 같은 노래 중에서는 고유 번호가 낮은 노래를 먼저 수록합니다.
 * 노래의 장르를 나타내는 문자열 배열 genres와 노래별 재생 횟수를 나타내는 정수 배열 plays가 주어질 때, 베스트 앨범에 들어갈 노래의 고유 번호를 순서대로 return 하도록 solution 함수를 완성하세요.
 *
 * 제한사항
 * genres[i]는 고유번호가 i인 노래의 장르입니다.
 * plays[i]는 고유번호가 i인 노래가 재생된 횟수입니다.
 * genres와 plays의 길이는 같으며, 이는 1 이상 10,000 이하입니다.
 * 장르 종류는 100개 미만입니다.
 * 장르에 속한 곡이 하나라면, 하나의 곡만 선택합니다.
 * 모든 장르는 재생된 횟수가 다릅니다.
 * 입출력 예
 * genres	plays	return
 * ["classic", "pop", "classic", "classic", "pop"]	[500, 600, 150, 800, 2500]	[4, 1, 3, 0]
 * 입출력 예 설명
 * classic 장르는 1,450회 재생되었으며, classic 노래는 다음과 같습니다.
 *
 * 고유 번호 3: 800회 재생
 * 고유 번호 0: 500회 재생
 * 고유 번호 2: 150회 재생
 * pop 장르는 3,100회 재생되었으며, pop 노래는 다음과 같습니다.
 *
 * 고유 번호 4: 2,500회 재생
 * 고유 번호 1: 600회 재생
 * 따라서 pop 장르의 [4, 1]번 노래를 먼저, classic 장르의 [3, 0]번 노래를 그다음에 수록합니다.
 *
 * 장르 별로 가장 많이 재생된 노래를 최대 두 개까지 모아 베스트 앨범을 출시하므로 2번 노래는 수록되지 않습니다.
 * ※ 공지 - 2019년 2월 28일 테스트케이스가 추가되었습니다.
 */
public class Q42579 {

    static class Music implements Comparable<Music> {
        int index;
        String genre;
        int playCount;

        public Music(int index, String genre, int playCount) {
            this.index = index;
            this.genre = genre;
            this.playCount = playCount;
        }

        @Override
        public String toString() {
            return "Music{" +
                "index=" + index +
                ", genre='" + genre + '\'' +
                ", playCount=" + playCount +
                '}';
        }

        @Override
        public int compareTo(Music o) {
            return Integer.compare(o.playCount, this.playCount);
        }
    }
    public static int[] solution(String[] genres, int[] plays) {
        List<Integer> answer = new ArrayList<>();
        Map<String, Integer> bestAlbums = new HashMap<>();
        List<String> sortedTopGenereNames = new ArrayList<>();

        for (int i = 0; i < genres.length; i++) {
            bestAlbums.put(genres[i], bestAlbums.getOrDefault(genres[i], 0) + plays[i]);
        }

        List<Entry<String, Integer>> entryList = new ArrayList<>(bestAlbums.entrySet());

        entryList.sort((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()));

        for (int i = 0; i < entryList.size(); i++) {
            sortedTopGenereNames.add(entryList.get(i).getKey());
        }

        List<Music> musics = new ArrayList<>();

        for (int i = 0; i < sortedTopGenereNames.size(); i++) {
            String genre = sortedTopGenereNames.get(i);
            for (int j = 0; j < genres.length; j++) {
                if(genre.equals(genres[j])) {
                    Music music = new Music(j, genre, plays[j]);
                    musics.add(music);
                }
            }

            Collections.sort(musics);
        }

        for (int i = 0; i < sortedTopGenereNames.size(); i++) {
            String genre = sortedTopGenereNames.get(i);
            int count = 0;
            for (int j = 0; j < musics.size(); j++) {
                Music music = musics.get(j);

                if(count > 1) {
                    break;
                }
                if(genre.equals(music.genre)) {
                    answer.add(music.index);
                    count++;
                }
            }
        }

        return answer.stream().mapToInt(Integer::intValue).toArray();
    }

    public static void main(String[] args) {
        solution(new String[]{"classic", "pop", "classic", "classic", "pop"}, new int[]{500, 600, 150, 800, 2500});
    }
}
