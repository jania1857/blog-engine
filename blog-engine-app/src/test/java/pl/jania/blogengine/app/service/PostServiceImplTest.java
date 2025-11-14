package pl.jania.blogengine.app.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.jania.blogengine.api.dto.PostDto;
import pl.jania.blogengine.api.service.PostService;
import pl.jania.blogengine.app.domain.Post;
import pl.jania.blogengine.app.repository.PostRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PostServiceImplTest {

    @Mock
    private PostRepository postRepository;

    private PostService postService;

    @BeforeEach
    void setUp() {
        postService = new PostServiceImpl(postRepository);
    }

    @Test
    void shouldReturnEmptyListOfPostsWhenNoPostsExist() throws Exception {
        // Given
        when(postRepository.findAll()).thenReturn(List.of());

        // When
        List<PostDto> posts = postService.findAll();

        // Then
        assertThat(posts).isEmpty();
    }

    @Test
    void shouldReturnOnePostIfOnePostExist() throws Exception {
        // Given
        var postEntity = new Post("My First Post");
        when(postRepository.findAll()).thenReturn(List.of(postEntity));

        // When
        List<PostDto> posts = postService.findAll();

        // Then
        assertThat(posts).hasSize(1);
        assertThat(posts.getFirst().title()).isEqualTo("My First Post");
    }
}
