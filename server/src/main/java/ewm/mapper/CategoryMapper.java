package ewm.mapper;

import ewm.model.Category;
import ewm.model.NewCategoryDto;

public class CategoryMapper {

    public static Category NewCategoryDtoToCategoryDto(NewCategoryDto newCategoryDto) {
        Category category = new Category();
        category.setName(newCategoryDto.getName());
        return category;
    }
}
