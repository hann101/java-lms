package nextstep.courses.domain.course.session;

import java.util.Optional;

public interface SessionRepository {
    Optional<Session> findById(Long id);
}