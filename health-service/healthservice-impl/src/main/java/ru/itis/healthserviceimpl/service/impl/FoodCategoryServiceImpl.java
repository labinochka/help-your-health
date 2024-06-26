package ru.itis.healthserviceimpl.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import ru.itis.healthserviceapi.dto.request.FoodCategoryRequest;
import ru.itis.healthserviceapi.dto.response.FoodCategoryResponse;
import ru.itis.healthserviceimpl.exception.FoodCategoryNotFoundException;
import ru.itis.healthserviceimpl.exception.ServiceException;
import ru.itis.healthserviceimpl.mapper.FoodCategoryMapper;
import ru.itis.healthserviceimpl.model.FoodCategory;
import ru.itis.healthserviceimpl.repository.FoodCategoryRepository;
import ru.itis.healthserviceimpl.service.FoodCategoryService;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FoodCategoryServiceImpl implements FoodCategoryService {

    private final FoodCategoryRepository repository;

    private final FoodCategoryMapper mapper;

    @Override
    @Cacheable(value = "foodCategory")
    public UUID save(FoodCategoryRequest foodCategoryRequest) {
        try {
            return repository.save(mapper.toEntity(foodCategoryRequest)).getId();
        } catch (Exception e) {
            throw new ServiceException("Bad food-category-create exception", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    @Cacheable(value = "foodCategory", key = "#id")
    public FoodCategoryResponse getById(UUID id) {
        return mapper.toResponse(
                repository.findById(id)
                        .orElseThrow(() -> new FoodCategoryNotFoundException(id))
        );
    }

    @Override
    @Cacheable(value = "foodCategory")
    public Set<FoodCategoryResponse> getAll() {
        Set<FoodCategoryResponse> foodCategories = new HashSet<>();
        for (FoodCategory foodCategory : repository.findAll()) {
            foodCategories.add(mapper.toResponse(foodCategory));
        }
        return foodCategories;
    }

    @Override
    @CacheEvict(value = "foodCategory", key = "#id")
    public void deleteById(UUID id) {
        repository.deleteById(id);
    }

    @Override
    @CachePut(value = "foodCategory", key = "#id")
    public void putById(UUID id, FoodCategoryRequest foodCategoryRequest) {
        if (repository.findById(id).isPresent()) {
            FoodCategory category = mapper.toEntity(foodCategoryRequest);
            category.setId(id);
            repository.save(category);
        }
        else {
            throw new FoodCategoryNotFoundException(id);
        }
    }

}
