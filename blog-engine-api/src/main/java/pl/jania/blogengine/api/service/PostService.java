package pl.jania.blogengine.api.service;

import pl.jania.blogengine.api.dto.PostDto;

import java.util.List;

public interface PostService {
    List<PostDto> findAll();
}
