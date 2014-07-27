package emeraldCasino.network;

import emeraldCasino.network.packets.*;
import cpw.mods.fml.common.network.simpleimpl.*;

public class ECServerMessageHandler implements IMessageHandler<GameMessage, IMessage>{
	@Override
	public IMessage onMessage(GameMessage message, MessageContext ctx) {
		message.transferData();
		return message;
	}

}
