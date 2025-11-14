package pl.jania.blogengine;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import pl.jania.blogengine.dto.PostDto;
import pl.jania.blogengine.service.PostService;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class PostApiTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PostService postService;

    @TestConfiguration
    static class ControllerTestConfig {
        @Bean PostService postService() {
            return Mockito.mock(PostService.class);
        }
    }


    @Test
    void shouldReturnEmptyListOfPostsWhenNoPostsExist() throws Exception {
        when(postService.findAll()).thenReturn(List.of());

        mockMvc.perform(get("/api/posts"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("[]"));
    }

    @Test
    void shouldReturnOnePostWhenOnePostExists() throws Exception {
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
