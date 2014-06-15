package EmeraldCasino.blocks.tileEntities;

import java.util.HashMap;
import java.util.List;

import EmeraldCasino.games.cards.ICardGame;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TileEntityCardBlock extends TileEntity {
	private int gameID;
	private ICardGame game;
	private List<String> players;
	private HashMap<String, Integer> playerBalances;
	private HashMap<String, Integer[][]> playerHands;


	public TileEntityCardBlock() {
		super();
		System.out.println("Tile Entity Created!");
	}

	@Override
	public Packet getDescriptionPacket() {
		NBTTagCompound nbtTag = new NBTTagCompound();
		this.writeToNBT(nbtTag);

		return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 1, nbtTag);
	}
	
	@Override
	public void writeToNBT(NBTTagCompound nbtTag)
	{
		super.writeToNBT(nbtTag);
		nbtTag.setInteger("gameID", this.gameID);
		nbtTag.setString("playerList", condensePlayers());
	}

	@Override
	public void readFromNBT(NBTTagCompound nbtTag)
	{
		super.readFromNBT(nbtTag);
		this.gameID = nbtTag.getInteger("gameID");
	}
	
	private String condensePlayers() {
		String playerList="";
		/*int count=1;
		for (String playerName : players) {
			playerList+=playerName;
			count++;
			if(count<players.size())
				playerList+=",";
		}*/
		return playerList;
	}
	
	private boolean expandPlayers(String playerList) {
		String[] playerArray = playerList.split(",");
		players.clear();
		boolean successful= true;
		for (String playerName : playerArray) {
			successful = (successful && players.add(playerName));
		}
		return successful;
	}
	

}
