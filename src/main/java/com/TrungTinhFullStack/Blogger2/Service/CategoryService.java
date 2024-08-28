package com.TrungTinhFullStack.Blogger2.Service;

import com.TrungTinhFullStack.Blogger2.Entity.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getAllCategory();
    Category getCategoryById(Long id);
    Category createCategory(Category category);
    Category updateCategory(Long id,Category category);
    Category deleteCategory(Long id);
}
