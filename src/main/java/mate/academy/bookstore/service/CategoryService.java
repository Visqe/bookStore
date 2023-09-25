package mate.academy.bookstore.service;

import java.util.List;
import mate.academy.bookstore.dto.category.CategoryResponseDto;
import mate.academy.bookstore.dto.category.CreateCategoryRequestDto;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface CategoryService {
    CategoryResponseDto save(CreateCategoryRequestDto createCategoryRequestDto);

    List<CategoryResponseDto> findAll(Pageable pageable);

    CategoryResponseDto findById(Long id);

    void deleteById(Long id);

    CategoryResponseDto update(Long id, CreateCategoryRequestDto createCategoryRequestDto);
}
