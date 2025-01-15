package M6FGR.guts.world.item;

import java.util.UUID;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.ImmutableMultimap.Builder;
import com.google.common.collect.Multimap;
import net.minecraft.world.item.Tier;
import yesman.epicfight.world.item.WeaponItem;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.AttributeModifier.Operation;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;

public class DragonSlayerItem extends WeaponItem {
        protected static final UUID MOVEMENT_SPEED_MODIFIER = UUID.fromString("ef97f722-b324-42d5-b5d8-2489cc74a00d");
        protected static final UUID MAX_HEALTH_MODIFIER = UUID.fromString("ef97f722-b324-42d5-b5d8-2489cc74a00d");

        private final float attackDamage;
        private final float attackSpeed;

        @SuppressWarnings("deprecation")
        public DragonSlayerItem(Properties build, Tier tier) {
            super(tier, 0, 0.0F, build);
            this.attackDamage = 11.0F + tier.getAttackDamageBonus();
            this.attackSpeed = -2.85F - (0.05F * tier.getLevel());
        }

        @Override
        public int getEnchantmentValue() {
            return 5;
        }

        @Override
        public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot slot, ItemStack stack) {
            if (slot == EquipmentSlot.MAINHAND) {
                Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
                builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Weapon modifier", this.attackDamage, Operation.ADDITION));
                builder.put(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Weapon modifier", this.attackSpeed, Operation.ADDITION));
                builder.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(MOVEMENT_SPEED_MODIFIER, "Weapon modifier", -0.025D, Operation.ADDITION));
                builder.put(Attributes.MAX_HEALTH, new AttributeModifier(MAX_HEALTH_MODIFIER, "Weapon modifier", 4.0D, Operation.ADDITION));
                return builder.build();
            }

            return super.getAttributeModifiers(slot, stack);
        }
    }
