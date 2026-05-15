package mekanism.common.inventory.container;

import mekanism.common.entity.EntityRobit;
import mekanism.common.inventory.slot.SlotEnergy.SlotDischarge;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerRobitMain extends Container
{
	private EntityRobit robit;

	public ContainerRobitMain(InventoryPlayer inventory, EntityRobit entity)
	{
		robit = entity;
		addSlotToContainer(new SlotDischarge(entity, 27, 153, 17));

		robit.openInventory();

		int slotY;

		for(slotY = 0; slotY < 3; slotY++)
		{
			for(int slotX = 0; slotX < 9; slotX++)
			{
				addSlotToContainer(new Slot(inventory, slotX + slotY * 9 + 9, 8 + slotX * 18, 84 + slotY * 18));
			}
		}

		for(slotY = 0; slotY < 9; slotY++)
		{
			addSlotToContainer(new Slot(inventory, slotY, 8 + slotY * 18, 142));
		}
	}

	@Override
	public void onContainerClosed(EntityPlayer entityplayer)
	{
		super.onContainerClosed(entityplayer);
		robit.closeInventory();
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

	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int slotID)
	{
		return null;
	}
}
