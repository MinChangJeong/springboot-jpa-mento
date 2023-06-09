package tuk.mentor.support.docs;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
@Import(RestDocsConfig.class)
@AutoConfigureRestDocs
@WebMvcTest
public abstract class RestDocumentTest {

    @Autowired private ObjectMapper objectMapper;
    protected MockMvc mockMvc;

    @MockBean private JpaMetamodelMappingContext jpaMetamodelMappingContext;

    protected String toRequestBody(Object value) throws JsonProcessingException {
        return objectMapper.writeValueAsString(value);
    }

    @BeforeEach
    public void setupMockMvc(
            WebApplicationContext ctx,
            RestDocumentationContextProvider restDocumentationContextProvider) {
        mockMvc =
                MockMvcBuilders.webAppContextSetup(ctx)
                        .apply(
                                documentationConfiguration(restDocumentationContextProvider)
                                        .uris()
                                        .withScheme("http")
                                        .withHost("localhost")
                                        .withPort(30507))
                        .addFilter(new CharacterEncodingFilter("UTF-8", true))
                        .alwaysDo(print())
                        .alwaysDo(document("api/v1/"))
                        .build();
    }
}
