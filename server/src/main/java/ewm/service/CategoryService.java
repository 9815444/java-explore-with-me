package ewm.service;

import ewm.model.Category;
import ewm.model.NewCategoryDto;

import java.util.List;

public interface CategoryService {
    Category addCategory(NewCategoryDto body);

    Category updateCategory(Category body);

    void deleteCategory(Long catId);

    List<Category> getCategories(Integer from, Integer size);

    Category getCategory(Long catId);
}
