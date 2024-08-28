package com.TrungTinhFullStack.Blogger2.Repository;

import com.TrungTinhFullStack.Blogger2.Entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
}
