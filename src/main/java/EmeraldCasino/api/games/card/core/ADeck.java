package emeraldCasino.api.games.card.core;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import net.minecraft.nbt.NBTTagCompound;
import emeraldCasino.EmeraldCasino;

public abstract class ADeck implements IDeck {
	protected List<ICard> cards = new LinkedList<ICard>();
	protected String textureLocation="emeraldcasino:textures/cardDecks/deck_blank.png";
	
	public void build(int numHouses, int deckSize)
	{
		if(numHouses<1||deckSize<1)
			throw new IllegalArgumentException("Both Deck Size and the number of card houses must be greater than 0. Values Received: \nnumHouses: "+numHouses+"\ndeckSize: "+deckSize);
		int valLimit=0;
		if((deckSize%numHouses)==0)
		{
			valLimit=(deckSize/numHouses);
		} else {
			throw new IllegalArgumentException("deckSize is not cleanly divisible by numHouses.\nPlease add Jokers or oddly sized houses using the addCustomHouse() function.");
		}
		int house=1, value=1;
		for(int i=0;i<deckSize;i++){
			cards.add(new CardStandard(house,value));
			if(value==valLimit){
				house++;
				value=1;
			}else{
			value++;
			}
		}
	}
	
	public void addCustomHouse(int houseID, int houseSize,boolean jokers)
	{
		if(houseID<1||houseSize<1)
		throw new IllegalArgumentException("Both House Size and the number of ID number for the house must be greater than 0. Values Received: \nhousID: "+houseID+"\nhouseize: "+houseSize);
		for(int i = 1;i<=houseSize;i++)
		{
			cards.add(new CardStandard(houseID,jokers?1:i));
		}
	}
	
	public void addCustomHouse(int houseID, int houseSize)
	{
		addCustomHouse(houseID, houseSize, false);
	}
	
	public void burn(ICard card)
	{
		try{
		cards.remove(card);
		} catch (Error e) {
			System.err.println("Call to Burn(Card) resulted in error: "+e.getMessage());
		}
	}
	
	@Override
	public void addCard(ICard card)
	{
		this.cards.add(card);
	}
	
	@Override
	public void addCards(java.util.List<ICard> cards)
	{
		this.cards.addAll(cards);
	}
	
	@Override
	public void burn()
	{
		burn(cards.get(0));
	}

	@Override
	public String getTexture() {
		return textureLocation;
	}

	@Override
	public ICard takeCard(){
		ICard result=cards.get(0);
		cards.remove(0);
		return result;
	}

	@Override
	public void shuffle(){
		Collections.shuffle(cards);
	}
	
	public NBTTagCompound toNBT(){
		NBTTagCompound result= new NBTTagCompound();
		int[] houses = new int[cards.size()];
		int[] values = new int[cards.size()];
		for(int j = 0;j<cards.size();j++){
			houses[j]=cards.get(j).getHouse();
			values[j]=cards.get(j).getValue();
		}
		result.setIntArray("houses", houses);
		result.setIntArray("values", values);
		return result;
	}
	
	public String toString(){
		String result="";
		for(int j = 0;j<cards.size();j++){
			result+=cards.get(j).toString()+"|";
		}
		return result;
	}
	
}
