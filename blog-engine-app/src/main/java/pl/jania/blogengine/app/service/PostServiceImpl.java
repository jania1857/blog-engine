package pl.jania.blogengine.app.service;

import org.springframework.stereotype.Service;
import pl.jania.blogengine.api.dto.PostDto;
import pl.jania.blogengine.api.service.PostService;
import pl.jania.blogengine.app.repository.PostRepository;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public List<PostDto> findAll() {
        return postRepository.findAll().stream().map(post -> new PostDto(post.title())).toList();
    }
}
