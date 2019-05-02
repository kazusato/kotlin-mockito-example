package kazusato.example.mock.repository

import kazusato.example.mock.domain.Message
import javax.enterprise.context.RequestScoped

@RequestScoped
open class MessageRepository {

    open fun findById(messageId: String): Message {
        throw UnsupportedOperationException()
    }

}