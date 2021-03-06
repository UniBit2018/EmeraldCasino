package emeraldCasino.blocks;

import java.util.Random;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import emeraldCasino.EmeraldCasino;
import emeraldCasino.blocks.tileEntities.TileEntityCardBlock;
import emeraldCasino.games.cards.*;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class blockCardDeck extends BlockContainer{
	
	
	public blockCardDeck() {
		super(Material.carpet);
		setBlockName("cardDeck");
		setStepSound(Block.soundTypeSnow);
	}
	
	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z)
    {
		return AxisAlignedBB.getBoundingBox(x+0.2, y, z+0.2, x+0.8, y+0.3, z+0.8);
    }
	
	@Override
	protected boolean canSilkHarvest() {
		return false;
	};
	
	@Override
	public boolean canSilkHarvest(World world, EntityPlayer player, int x, int y, int z, int metadata) {
		return false;
	};
	
	@Override
	public Item getItemDropped(int p_149650_1_, Random p_149650_2_,
			int p_149650_3_) {
		return GameRegistry.findItem(EmeraldCasino.MODID, "cardDeck");
	}
	
	@Override
	public ItemStack getPickBlock(net.minecraft.util.MovingObjectPosition target, World world, int x, int y, int z) {
		ItemStack result = new ItemStack(GameRegistry.findItem(EmeraldCasino.MODID, "cardDeck"),1);
		return result;
	};
	
	@Override
	public boolean canBeReplacedByLeaves(IBlockAccess world, int x, int y, int z) {
		return false;
	}
	
	@Override
	public boolean isBlockSolid(IBlockAccess block, int x, int y, int z, int metaD) {
	return false;	
	}
	
	@Override
	public boolean removedByPlayer(World world, EntityPlayer player, int x, int y, int z) {
		return false;
	}
	
	@Override
	public boolean canDropFromExplosion(Explosion p_149659_1_) {
		return false;
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float blockX, float blockY, float blockZ) {
		((TileEntityCardBlock)world.getTileEntity(x, y, z)).activatedBy(player);
		return true;
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public boolean renderAsNormalBlock()
    {
                    return false;
    }
	
	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRendered(IBlockAccess blockAccess, int x, int y, int z, int side) {
		return false;
	};
	
	@SideOnly(Side.CLIENT)
	@Override
	public boolean isOpaqueCube()
    {
		return false;
    }
	
	@Override
    public int getRenderType() {
            return -1;
    }
	
	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister icon) {
        this.blockIcon = icon.registerIcon("emeraldcasino:CardDeckIcon");
}
	
	@SideOnly(Side.CLIENT)
	protected IIcon[] icons;

	@Override
	public int onBlockPlaced (World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int metaD)
	{
		
		/*switch (side)
        {
        case 0:
        case 1:
            metaD = 0;
            break;
        case 2:
        case 3:
            metaD = 8;
            break;
        case 4:
        case 5:
            metaD = 4;
            break;
        }*/
		world.setBlockMetadataWithNotify(x, y, z, metaD,3);
		return metaD;
	}
	
	

	@Override
	public TileEntity createNewTileEntity(World world, int metadata) {
		TileEntityCardBlock tecb =  new TileEntityCardBlock();
		
		NBTTagCompound nbt = new NBTTagCompound();
		nbt.setString("gameID", EmeraldCasino.MODID+".poker.5CardDraw");
		tecb.writeToNBT(nbt);
		return tecb;
	}
	
	
}
