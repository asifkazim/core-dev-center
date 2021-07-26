package az.core.model.dto;

import com.sun.istack.NotNull;

public class BlogCategoryDto {

    @NotNull
    private Long id;

    @NotNull
    private String name;

    public BlogCategoryDto() {
    }

    public BlogCategoryDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
