package nextstep.courses.infrastructure;

import static org.assertj.core.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import nextstep.courses.domain.enums.PaidType;
import nextstep.courses.domain.enums.Status;
import nextstep.courses.domain.session.Period;
import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.SessionRepository;
import nextstep.courses.domain.session.image.Image;
import nextstep.courses.domain.session.registration.SessionCapacity;
import nextstep.courses.domain.session.registration.SessionRegistration;
import nextstep.courses.domain.session.registration.Tuition;

@JdbcTest
public class SessionRepositoryTest {
	private final static Session session1 = new Session(
		1L, "자바 마스터리 30선",
		new Period(LocalDate.of(2023, 11, 1), LocalDate.of(2023, 12, 14)),
		new Image(0.9d, "PNG", 300, 200), Status.READY,
		new SessionRegistration(PaidType.PAID, new Tuition(50000), new SessionCapacity(100), null), null);
	private final static Session session2 = new Session(
		2L, "자바 마스터리 30선 ver 2.0",
		new Period(LocalDate.of(2023, 12, 15), LocalDate.of(2024, 1, 31)),
		new Image(0.9d, "PNG", 300, 200), Status.READY,
		new SessionRegistration(PaidType.PAID, new Tuition(100000), new SessionCapacity(100), null), null);
	@Autowired
	private JdbcTemplate jdbcTemplate;

	private SessionRepository sessionRepository;

	@BeforeEach
	void setUp() {
		sessionRepository = new JdbcSessionRepository(jdbcTemplate);
	}

	@Test
	public void validate_save() {
		int count = sessionRepository.save(session1);
		assertThat(count).isEqualTo(1);
	}

	@Test
	public void validate_findById() {
		sessionRepository.save(session1);
		sessionRepository.save(session2);
		assertThat(
			sessionRepository.findById(1L).orElse(null).getTitle()
		).isEqualTo("자바 마스터리 30선");
	}
}