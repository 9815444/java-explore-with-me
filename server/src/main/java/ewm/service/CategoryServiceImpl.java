package ewm.service;

import ewm.errors.BadRequestException;
import ewm.errors.NotFoundException;
import ewm.mapper.CategoryMapper;
import ewm.model.Category;
import ewm.model.NewCategoryDto;
import ewm.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public Category addCategory(NewCategoryDto newCategoryDto) {
        var newCat = categoryRepository.save(CategoryMapper.NewCategoryDtoToCategoryDto(newCategoryDto));
        return newCat;
    }

    @Override
    public Category updateCategory(Category category) {
        if (category.getName() == null) {
            throw new BadRequestException("Не заполнено имя категории");
        }
        var newCat = categoryRepository.save(category);
        return newCat;
    }

    @Override
    public void deleteCategory(Long catId) {
        categoryRepository.deleteById(catId);
    }

    @Override
    public List<Category> getCategories(Integer from, Integer size) {
        int fromPage = from.intValue() / size.intValue();
        Pageable pageable = PageRequest.of(fromPage, size.intValue());
        var categoryDtos = categoryRepository.findAll(pageable).getContent();
        return categoryDtos;
    }

    @Override
    public Category getCategory(Long catId) {
        var optionalCategoryDto = categoryRepository.findById(catId).
                orElseThrow(() -> new NotFoundException("Категория не найдена"));
        return optionalCategoryDto;
    }
}
