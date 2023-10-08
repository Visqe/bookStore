package mate.academy.bookstore.repository;

import java.util.Optional;
import mate.academy.bookstore.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("FROM User users INNER JOIN FETCH users.roles WHERE users.email = :email")
    Optional<User> findByEmail(String email);
}
