package M6FGR.guts.world.capabilities.item;

import java.util.Map;
import java.util.function.Function;
import com.google.common.collect.Maps;
import M6FGR.guts.main.EFAddon;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import M6FGR.guts.gameasset.EFAnimations;
import net.minecraftforge.fml.common.Mod;
import yesman.epicfight.api.forgeevent.WeaponCapabilityPresetRegistryEvent;
import yesman.epicfight.api.animation.LivingMotions;
import yesman.epicfight.gameasset.ColliderPreset;
import yesman.epicfight.gameasset.EpicFightSkills;
import yesman.epicfight.gameasset.Animations;
import yesman.epicfight.gameasset.EpicFightSounds;
import yesman.epicfight.world.capabilities.entitypatch.LivingEntityPatch;
import yesman.epicfight.world.capabilities.item.CapabilityItem;
import yesman.epicfight.world.capabilities.item.CapabilityItem.Styles;
import yesman.epicfight.world.capabilities.item.WeaponCapability;



@Mod.EventBusSubscriber(
        modid = "guts",
        bus = Mod.EventBusSubscriber.Bus.MOD
)
@SuppressWarnings("")
public class WeaponCapabilityPresets {

        public static final Function<Item, CapabilityItem.Builder> DRAGONSLAYER = (item) -> {
            WeaponCapability.Builder builder = WeaponCapability.builder()
                    .category(EFCategories.DRAGONSLAYER)
                    .styleProvider((playerpatch) -> Styles.TWO_HAND)
                    .collider(ColliderPreset.GREATSWORD)
                    .swingSound(EpicFightSounds.WHOOSH_BIG.get())
                    .hitSound(EpicFightSounds.BLADE_HIT.get())
                    .canBePlacedOffhand(false)
                    .newStyleCombo(Styles.TWO_HAND, EFAnimations.DRAGONSLAYER_AUTO_1, EFAnimations.DRAGONSLAYER_AUTO_2, EFAnimations.DRAGONSLAYER_AUTO_3, EFAnimations.DRAGONSLAYER_AUTO_4, EFAnimations.DRAGONSLAYER_DASH, EFAnimations.DRAGONSLAYER_AIRSLASH)
                    .innateSkill(Styles.TWO_HAND, (itemstack) -> {
                        return EpicFightSkills.STEEL_WHIRLWIND;})
                    .livingMotionModifier(Styles.TWO_HAND, LivingMotions.IDLE, EFAnimations.DRAGONSLAYER_IDLE)
                    .livingMotionModifier(Styles.TWO_HAND, LivingMotions.WALK, EFAnimations.DRAGONSLAYER_WALK)
                    .livingMotionModifier(Styles.TWO_HAND, LivingMotions.CHASE, EFAnimations.DRAGONSLAYER_WALK)
                    .livingMotionModifier(Styles.TWO_HAND, LivingMotions.RUN, EFAnimations.DRAGONSLAYER_RUN)
                    .livingMotionModifier(Styles.TWO_HAND, LivingMotions.JUMP, Animations.BIPED_JUMP)
                    .livingMotionModifier(Styles.TWO_HAND, LivingMotions.KNEEL, Animations.BIPED_KNEEL)
                    .livingMotionModifier(Styles.TWO_HAND, LivingMotions.SNEAK, Animations.BIPED_SNEAK)
                    .livingMotionModifier(Styles.TWO_HAND, LivingMotions.SWIM, Animations.BIPED_SWIM)
                    .livingMotionModifier(Styles.TWO_HAND, LivingMotions.BLOCK, EFAnimations.DRAGONSLAYER_GUARD);
            return builder;
    };

    public WeaponCapabilityPresets() {
    }

    @SubscribeEvent
    public static void RegisterMovesets(WeaponCapabilityPresetRegistryEvent event) {
        event.getTypeEntry().put(new ResourceLocation("guts", "dragon_slayer"), DRAGONSLAYER);
    }
}