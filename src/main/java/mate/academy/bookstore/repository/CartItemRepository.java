package mate.academy.bookstore.repository;

import java.util.List;
import mate.academy.bookstore.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    List<CartItem> findAllByCartIdAndBookId(Long cartId, Long bookId);
}
