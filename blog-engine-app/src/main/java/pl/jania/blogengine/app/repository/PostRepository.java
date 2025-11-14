package pl.jania.blogengine.app.repository;

import pl.jania.blogengine.app.domain.Post;

import java.util.List;

public interface PostRepository {
    List<Post> findAll();
}
