package M6FGR.guts.gameasset;

import M6FGR.guts.animation.HeavyAttackAnimation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import yesman.epicfight.api.animation.property.AnimationEvent;
import yesman.epicfight.api.animation.property.AnimationProperty;
import yesman.epicfight.api.animation.types.*;
import yesman.epicfight.api.animation.types.AttackAnimation;
import yesman.epicfight.api.forgeevent.AnimationRegistryEvent;
import yesman.epicfight.api.utils.LevelUtil;
import yesman.epicfight.api.utils.math.ValueModifier;
import yesman.epicfight.gameasset.Animations;
import yesman.epicfight.gameasset.Armatures;
import yesman.epicfight.gameasset.EpicFightSounds;
import yesman.epicfight.model.armature.HumanoidArmature;
import yesman.epicfight.world.damagesource.StunType;
import static yesman.epicfight.api.animation.property.AnimationProperty.AttackAnimationProperty.BASIS_ATTACK_SPEED;



public class EFAnimations {


    public static StaticAnimation DRAGONSLAYER_AUTO_1;
    public static StaticAnimation DRAGONSLAYER_AUTO_2;
    public static StaticAnimation DRAGONSLAYER_AUTO_3;
    public static StaticAnimation DRAGONSLAYER_AUTO_4;
    public static StaticAnimation DRAGONSLAYER_DASH;
    public static StaticAnimation DRAGONSLAYER_AIRSLASH;
    public static StaticAnimation DRAGONSLAYER_IDLE;
    public static StaticAnimation DRAGONSLAYER_WALK;
    public static StaticAnimation DRAGONSLAYER_RUN;
    public static StaticAnimation DRAGONSLAYER_GUARD;



    @SubscribeEvent
    public static void registerAnimations(AnimationRegistryEvent event) {
        event.getRegistryMap().put("guts", EFAnimations::build);
    }

    private static void build() {
        HumanoidArmature biped = Armatures.BIPED;



        DRAGONSLAYER_AUTO_1 = new HeavyAttackAnimation(0.1F, 0.5F, 0.9F, 1.4F, EFColliders.GROUNDSLAM_KUTEN, biped.rootJoint, "biped/combat/kuten_auto1", biped)
                .addProperty(AnimationProperty.AttackPhaseProperty.SWING_SOUND, EpicFightSounds.WHOOSH_BIG.get())
                .addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.LONG)
                .addProperty(BASIS_ATTACK_SPEED, 1.0F)
                .addEvents(new AnimationEvent.TimeStampedEvent[]{AnimationEvent.TimeStampedEvent.create(0.6F, EFReusableEvents.GROUNDSLAM_SMALL, AnimationEvent.Side.CLIENT)});

        DRAGONSLAYER_AUTO_2 = new HeavyAttackAnimation(0.1F, 0.5F, 0.9F, 1.4F, EFColliders.GROUNDSLAM_KUTEN, biped.rootJoint, "biped/combat/kuten_auto2", biped)
                .addProperty(AnimationProperty.AttackPhaseProperty.SWING_SOUND, EpicFightSounds.WHOOSH_BIG.get())
                .addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.LONG)
                .addProperty(BASIS_ATTACK_SPEED, 1.0F);

        DRAGONSLAYER_AUTO_3 =new HeavyAttackAnimation(0.05F, "biped/combat/kuten_auto3", biped,
                new AttackAnimation.Phase(0.0F, 0.2F, 0.2F, 0.5F, 1.0F, 0.8F, biped.toolL, null)
                        .addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.7F))
                        .addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.LONG)
                , new AttackAnimation.Phase(0.3F, 0.5F, 0.7F, 1.4F, 0.9F, biped.rootJoint, null))
                .addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.7F))
                .addProperty(BASIS_ATTACK_SPEED, 1.0F)
                .addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.LONG);


        DRAGONSLAYER_AUTO_4 = new BasicAttackAnimation(0.1F, 0.5F, 0.9F, 1.4F, EFColliders.KUTEN, biped.toolR, "biped/combat/kuten_auto4", biped)
                .addProperty(AnimationProperty.AttackPhaseProperty.SWING_SOUND, EpicFightSounds.WHOOSH_BIG.get())
                .addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.HOLD)
                .addProperty(BASIS_ATTACK_SPEED, 1.0F);

    DRAGONSLAYER_AIRSLASH = new AirSlashAnimation(0.1F, 0.4F, 0.9F, 0.9F, null, biped.toolR, "biped/combat/gladius_airslash", biped);

        DRAGONSLAYER_DASH = new DashAttackAnimation(0.15F, "biped/combat/gladius_dash", biped, new AttackAnimation.Phase(0.0F, 0.3F, 0.8F, 1.0F, 1.2F, biped.legL, null))
                .addProperty(BASIS_ATTACK_SPEED, 1.4F);



        DRAGONSLAYER_IDLE = new StaticAnimation(true, "biped/living/kuten_idle", biped);


        DRAGONSLAYER_WALK = new MovementAnimation(true, "biped/living/kuten_walk", biped);


        DRAGONSLAYER_RUN = new MovementAnimation(true, "biped/living/kuten_run", biped);


    }
}