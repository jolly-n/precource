package menu;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.Arrays;
import java.util.List;

public enum Category {

    JAPANESE("일식", 1, List.of("규동", "우동", "미소시루", "스시", "가츠동", "오니기리", "하이라이스", "라멘", "오코노미야끼")),
    KOREAN("한식", 2, List.of("김밥", "김치찌개", "쌈밥", "된장찌개", "비빔밥", "칼국수", "불고기", "떡볶이", "제육볶음")),
    CHINA("중식", 3, List.of("깐풍기", "볶음면", "동파육", "짜장면", "짬뽕", "마파두부", "탕수육", "토마토 달걀볶음", "고추잡채")),
    ASIAN("아시안", 4, List.of("팟타이", "카오 팟", "나시고렝", "파인애플 볶음밥", "쌀국수", "똠얌꿍", "반미", "월남쌈", "분짜")),
    WESTERN("양식", 5, List.of("라자냐", "그라탱", "뇨끼", "끼슈", "프렌치 토스트", "바게트", "스파게티", "피자", "파니니"));

    public static final String NOT_FOUND_CATEGORY = "[ERROR] 카테고리를 찾을 수 없습니다.";

    private final int key;
    private final String name;
    private final List<String> menus;

    Category(String name, int key, List<String> menus) {
        this.name = name;
        this.key = key;
        this.menus = menus;
    }

    public static Category find(int key) {
        return Arrays.stream(values())
                .filter(category -> category.key == key)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(NOT_FOUND_CATEGORY));
    }

    public String pickMenu() {
        return Randoms.shuffle(menus).get(0);
    }

    public String getName() {
        return name;
    }
}
