package kazusato.example.mock.api

import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import kazusato.example.mock.domain.Message
import kazusato.example.mock.repository.MessageRepository
import kazusato.example.mock.service.MessageService
import org.assertj.core.api.Assertions.assertThat
import org.jboss.weld.junit5.auto.ActivateScopes
import org.jboss.weld.junit5.auto.AddBeanClasses
import org.jboss.weld.junit5.auto.EnableAutoWeld
import org.jboss.weld.junit5.auto.ExcludeBean
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mockito
import javax.enterprise.context.RequestScoped
import javax.enterprise.inject.Produces
import javax.inject.Inject

@EnableAutoWeld
@AddBeanClasses(MessageApi::class, MessageService::class, MessageRepository::class)
@ActivateScopes(RequestScoped::class)
class MessageApiTest {

    @Inject
    private lateinit var api : MessageApi

    @Produces
    @ExcludeBean
    fun getMockRepository(): MessageRepository {
        return mock(defaultAnswer = Mockito.RETURNS_DEEP_STUBS) {
            on { findById(ArgumentMatchers.anyString()) } doReturn Message("msg1", "hello world")
        }
    }

    @Test
    fun testGetMessage() {
        val messageDto = api.getMessage("msg1")
        assertThat(messageDto.text).isEqualTo("hello world")
    }

}