package nextstep.session.domain;

import java.time.LocalDateTime;

public class EndAt {

    private final LocalDateTime value;

    public EndAt(LocalDateTime value) {
        if (value == null) {
            throw new IllegalArgumentException("생성 데이터는 비어있을 수 없습니다.");
        }

        if (value.isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("종료일은 현재보다 이전일 수 없습니다.");
        }
        this.value = value;
    }
}
