package M6FGR.guts.world.item;

import M6FGR.guts.main.EFAddon;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Tiers;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;



public class EFItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, EFAddon.MOD_ID);

    public static final RegistryObject<Item> DRAGONSLAYER = ITEMS.register("dragon_slayer", () -> {
        return new DragonSlayerItem(new Item.Properties().rarity(Rarity.EPIC).fireResistant(), Tiers.NETHERITE);
    });
}

