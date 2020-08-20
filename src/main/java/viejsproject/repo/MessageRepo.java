package viejsproject.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import viejsproject.domain.Message;

public interface MessageRepo extends JpaRepository<Message, Long> {

}
