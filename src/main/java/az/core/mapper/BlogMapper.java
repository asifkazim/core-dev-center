package az.core.mapper;

import az.core.model.dto.BlogDto;
import az.core.model.dto.MultiLang;
import az.core.model.entity.Blog;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface BlogMapper {

    default List<BlogDto> entitiesToDto(List<Blog> blogs) {
        List<BlogDto> dtos = new ArrayList<>();
        blogs.forEach(blog -> dtos.add(entityToDto(blog)));
        return dtos;
    }

////////////////////////////////////////////////
    @Mapping(target = "id", source = "id")
    @Mapping(target = "publicationDate", source = "publicationDate")
    @Mapping(target = "blogCategory", source = "blogCategory")
    @Mapping(target = "status", source = "status")
    @Mapping(target = "image", source = "image")
    default BlogDto entityToDto(Blog blog) {
        BlogDto dto = new BlogDto();
        dto.setDescription(mapDescription(blog));
        dto.setTittle(mapTittle(blog));
        return dto;
    }

    @Mapping(target = "az", source = "descriptionAz")
    @Mapping(target = "en", source = "descriptionEn")
    MultiLang mapDescription(Blog blog);

    @Mapping(target = "az", source = "tittleAz")
    @Mapping(target = "en", source = "tittleEn")
    MultiLang mapTittle(Blog blog);

    /////////////////////////////////////////////////

//    @Mapping(target = "id", source = "id")
//    @Mapping(target = "publicationDate", source = "publicationDate")
    @Mapping(target = "blogCategory", source = "blogCategory")
//    @Mapping(target = "status", source = "status")
    @Mapping(target = "image", source = "image")
    default Blog dtoToEntity(BlogDto blogDto) {
        System.out.println(blogDto);
        Blog blog1 = mapMultiDescToBlog(blogDto.getDescription());
        Blog blog2 = mapMultiTittleToBlog(blogDto.getTittle());
        blog2.setDescriptionAz(blog1.getDescriptionAz());
        blog2.setDescriptionEn(blog1.getDescriptionEn());
        blog2.setPublicationDate(blogDto.getPublicationDate());
        blog2.setStatus(blogDto.getStatus());
        blog2.setId(blogDto.getId());
        System.out.println(blog2);
        return blog2;
    }

    @Mapping(target = "descriptionAz", source = "az")
    @Mapping(target = "descriptionEn", source = "en")
    Blog mapMultiDescToBlog(MultiLang multiLang);

    @Mapping(target = "tittleAz", source = "az")
    @Mapping(target = "tittleEn", source = "en")
    Blog mapMultiTittleToBlog(MultiLang multiLang);
}
