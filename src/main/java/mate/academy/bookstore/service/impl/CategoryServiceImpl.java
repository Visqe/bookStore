package mate.academy.bookstore.service.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.bookstore.dto.category.CategoryResponseDto;
import mate.academy.bookstore.dto.category.CreateCategoryRequestDto;
import mate.academy.bookstore.mapper.CategoryMapper;
import mate.academy.bookstore.model.Category;
import mate.academy.bookstore.repository.CategoryRepository;
import mate.academy.bookstore.service.CategoryService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public CategoryResponseDto save(CreateCategoryRequestDto createCategoryRequestDto) {
        Category category = categoryMapper.toModel(createCategoryRequestDto);
        Category savedBook = categoryRepository.save(category);
        return categoryMapper.toDto(savedBook);
    }

    @Override
    public List<CategoryResponseDto> findAll(Pageable pageable) {
        return categoryRepository.findAll(pageable).stream()
                .map(categoryMapper::toDto)
                .toList();
    }

    @Override
    public CategoryResponseDto findById(Long id) {
        return categoryRepository.findById(id)
                .map(categoryMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Cant find Category by id = " + id));
    }

    @Override
    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public CategoryResponseDto update(Long id, CreateCategoryRequestDto createCategoryRequestDto) {
        Category category = categoryMapper.toModel(createCategoryRequestDto);
        category.setId(id);
        Category savedCategory = categoryRepository.save(category);
        return categoryMapper.toDto(savedCategory);
    }
}
