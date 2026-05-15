package mekanism.common.inventory.container;

import mekanism.common.entity.EntityRobit;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ContainerRepair;

public class ContainerRobitRepair extends ContainerRepair
{
	public EntityRobit robit;
	
	public ContainerRobitRepair(InventoryPlayer inventory, EntityRobit entity)
	{
		super(inventory, entity.worldObj, 0, 0, 0, inventory.player);
		
		robit = entity;
	}

	@Override
	public boolean canInteractWith(EntityPlayer entityplayer)
	{
		//Validate robit is alive, player is within range, and player is the owner
		if(robit.isDead)
		{
			return false;
		}

		//Check distance to prevent remote inventory manipulation
		if(entityplayer.getDistanceSqToEntity(robit) > 64.0D)
		{
			return false;
		}

		//Verify owner to prevent unauthorized access
		String owner = robit.getOwnerName();
		if(owner != null && !owner.isEmpty() && !owner.equals(entityplayer.getCommandSenderName()))
		{
			return false;
		}

		return true;
	}
}
