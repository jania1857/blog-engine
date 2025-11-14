package pl.jania.blogengine.app.controller;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import pl.jania.blogengine.api.dto.PostDto;
import pl.jania.blogengine.api.service.PostService;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PostController.class)
class PostControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PostService postService;

    @TestConfiguration
    static class PostControllerTestConfiguration {
        @Bean
        public PostService postService() {
            return Mockito.mock(PostService.class);
        }
    }

    @Test
    void shouldReturnEmptyListOfPostsWhenNoPostsExist() throws Exception {
        // Given
        when(postService.findAll()).thenReturn(List.of());

        // When/Then
        mockMvc.perform(get("/api/posts"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("[]"));
    }

    @Test
    void shouldReturnOnePostWhenOnePostExists() throws Exception {
        // Given
        var post = new PostDto("My First Post");
        var expectedResponseJson = """
                [
                    { "title": "My First Post" }
                ]
                """;

        when(postService.findAll()).thenReturn(List.of(post));

        mockMvc.perform(get("/api/posts"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(expectedResponseJson));
    }
}
