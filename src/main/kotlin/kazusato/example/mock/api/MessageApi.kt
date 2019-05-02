package kazusato.example.mock.api

import kazusato.example.mock.dto.MessageDto
import kazusato.example.mock.service.MessageService
import javax.inject.Inject
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.PathParam

@Path("message")
class MessageApi {

    @Inject
    private lateinit var service : MessageService

    @GET
    @Path("{messageId}")
    fun getMessage(@PathParam("messageId") messageId: String): MessageDto {
        val message = service.getMessage(messageId)
        val dto = MessageDto(message.text)
        return dto
    }

}