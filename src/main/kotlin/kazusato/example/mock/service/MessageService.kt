package kazusato.example.mock.service

import kazusato.example.mock.domain.Message
import kazusato.example.mock.repository.MessageRepository
import javax.enterprise.context.RequestScoped
import javax.inject.Inject

@RequestScoped
open class MessageService {

    @Inject
    private lateinit var repository : MessageRepository

    open fun getMessage(messageId: String): Message {
        return repository.findById(messageId)
    }

}