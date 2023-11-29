package pairmatching.view;

import pairmatching.domain.Feature;

import static camp.nextstep.edu.missionutils.Console.readLine;

public class Input {

    public Feature readFeature() {
        System.out.println("기능을 선택하세요.\n" +
                "1. 페어 매칭\n" +
                "2. 페어 조회\n" +
                "3. 페어 초기화\n" +
                "Q. 종료");
        return InputConvertor.convert(read());
    }

    private String read() {
        return readLine();
    }
}
