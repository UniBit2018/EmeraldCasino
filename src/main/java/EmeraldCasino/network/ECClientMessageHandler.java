package emeraldCasino.network;

import emeraldCasino.network.packets.*;
import cpw.mods.fml.common.network.simpleimpl.*;

public class ECClientMessageHandler implements IMessageHandler<PlayerMessage, IMessage>{
	@Override
	public IMessage onMessage(PlayerMessage message, MessageContext ctx) {
		message.transferData();
		return message;
	}

}
