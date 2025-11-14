package pl.jania.blogengine.service;

import pl.jania.blogengine.dto.PostDto;

import java.util.List;

public interface PostService {
    List<PostDto> findAll();
}
