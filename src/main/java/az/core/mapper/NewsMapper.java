package az.core.mapper;

import az.core.model.dto.request.NewsRequestDto;
import az.core.model.dto.response.NewsResponseDto;
import az.core.model.entity.News;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface NewsMapper {
    default List<NewsResponseDto> entitiesToDto(List<News> news) {
        List<NewsResponseDto> dtos = new ArrayList<>();
        news.forEach(n -> dtos.add(entityToDto(n)));
        return dtos;
    }

    NewsResponseDto entityToDto(News news);

    News dtoToEntity(NewsRequestDto newsRequestDto);
}
