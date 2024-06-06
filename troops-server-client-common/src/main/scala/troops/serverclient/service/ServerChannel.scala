package troops.serverclient.service

import game.collections.ProducerConsumerIterator
import troops.serverclient.model.ClientRequest
import troops.serverclient.model.ServerResponse

trait ServerChannel:
  def createServerConnection(sessionId: String): (Iterator[ServerResponse], ProducerConsumerIterator[ClientRequest])
