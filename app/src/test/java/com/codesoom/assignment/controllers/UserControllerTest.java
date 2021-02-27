package com.codesoom.assignment.controllers;

import com.codesoom.assignment.application.UserService;
import com.codesoom.assignment.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
@DisplayName("UserController 클래스")
class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    private final String givenEmail = "juuni.ni.i@gmail.com";
    private final String givenName = "juunini";
    private final String givenPassword = "secret";
    private final String givenUserInfoJSON = String.format("{" +
                    "\"email\":\"%s\"," +
                    "\"name\":\"%s\"," +
                    "\"password\":\"%s\"" +
                    "}",
            givenEmail,
            givenName,
            givenPassword
    );

    @MockBean
    private UserService userService;

    @Nested
    @DisplayName("[POST] /users 요청은")
    class Describe_create {
        @Nested
        @DisplayName("올바른 데이터가 주어졌을 때")
        class Context_with_correct_given_data {
            @BeforeEach
            void setup() {
                Mockito.doNothing().when(userService).create(any(User.class));
            }

            @Test
            @DisplayName("생성된 유저 정보와 함께 created 를 응답한다.")
            void It_respond_created_user_info() throws Exception {
                mockMvc.perform(
                        post("/users")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(givenUserInfoJSON)
                )
                        .andExpect(status().isCreated())
                        .andExpect(content().string(containsString(givenEmail)));
            }
        }

        @Nested
        @DisplayName("필요한 정보가 오지 않았을 때")
        class Context_without_required_parameter {
            @Test
            @DisplayName("bad request 를 응답한다.")
            void It_respond_bad_request() {

            }
        }
    }

    @Nested
    @DisplayName("[PATCH] /users/{id} 요청은")
    class Describe_modify {
        @Nested
        @DisplayName("주어진 id의 유저가 존재할 때")
        class Context_when_exists_given_id_user {
            @Test
            @DisplayName("유저 정보가 변경되었음을 status ok 로 응답한다.")
            void It_respond_modify_user_info() {

            }
        }

        @Nested
        @DisplayName("주어진 id의 user 가 없을 때")
        class Context_when_not_exists_given_id_user {
            @Test
            @DisplayName("not found 를 응답한다.")
            void It_respond_not_found() {

            }
        }
    }

    @Nested
    @DisplayName("[DELETE] /users/{id} 요청은")
    class Describe_delete {
        @Nested
        @DisplayName("주어진 id의 user 가 존재할 때")
        class Context_when_exists_given_id_user {
            @Test
            @DisplayName("no content 를 응답한다.")
            void It_respond_no_content() {

            }
        }

        @Nested
        @DisplayName("주어진 id의 user 가 없을 때")
        class Context_when_not_exists_given_id_user {
            @Test
            @DisplayName("not found 를 응답한다.")
            void It_respond_not_found() {

            }
        }
    }
}
