package uz.pdp.springbootsecuritytestdemo.reposiroty;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.springbootsecuritytestdemo.entity.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {

}
