package M6FGR.guts.animation;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.phys.Vec3;
import yesman.epicfight.api.animation.Joint;
import yesman.epicfight.api.animation.property.AnimationProperty;
import yesman.epicfight.api.animation.types.AttackAnimation;
import yesman.epicfight.api.animation.types.DynamicAnimation;
import yesman.epicfight.api.animation.types.EntityState;
import yesman.epicfight.api.client.animation.Layer;
import yesman.epicfight.api.client.animation.property.JointMaskEntry;
import yesman.epicfight.api.collider.Collider;
import yesman.epicfight.api.model.Armature;
import yesman.epicfight.api.utils.datastruct.TypeFlexibleHashMap;
import yesman.epicfight.client.world.capabilites.entitypatch.player.LocalPlayerPatch;
import yesman.epicfight.config.EpicFightOptions;
import yesman.epicfight.gameasset.Animations;
import yesman.epicfight.world.capabilities.entitypatch.LivingEntityPatch;
import yesman.epicfight.world.gamerule.EpicFightGamerules;

import javax.annotation.Nullable;
import java.util.Locale;
import java.util.Optional;

public class HeavyAttackAnimation extends AttackAnimation {
    public HeavyAttackAnimation(float convertTime, float Start, float End, float recovery, @Nullable Collider collider, Joint colliderJoint, String path, Armature armature) {
        this(convertTime, Start, Start, End, recovery, collider, colliderJoint, path, armature);
        this.newTimePair(0.0F, Float.MAX_VALUE);
    }

    public HeavyAttackAnimation(float ConvertTime, float StopMovement, float Start, float End, float Recovery, @Nullable Collider collider, Joint colliderJoint, String path, Armature armature) {
        this(ConvertTime, path, armature, new HeavyAttackAnimation.Phase( StopMovement, Start, End, Recovery, Float.MAX_VALUE, colliderJoint, collider));
        this.addProperty(AnimationProperty.ActionAnimationProperty.CANCELABLE_MOVE, false);
        this.newTimePair(0.0F, Float.MAX_VALUE);
        this.addProperty(AnimationProperty.StaticAnimationProperty.POSE_MODIFIER, Animations.ReusableSources.COMBO_ATTACK_DIRECTION_MODIFIER);
    }

    public HeavyAttackAnimation(float ConvertTime, float StopMovement, float Start, float End, float Recovery, InteractionHand hand, @Nullable Collider collider, Joint colliderJoint, String path, Armature armature) {
        this(ConvertTime, path, armature, new HeavyAttackAnimation.Phase(0.0F, StopMovement, Start, End, Recovery, Float.MAX_VALUE, colliderJoint, collider));
        this.addProperty(AnimationProperty.ActionAnimationProperty.CANCELABLE_MOVE, false);
        this.newTimePair(0.0F, Float.MAX_VALUE);
        this.addProperty(AnimationProperty.StaticAnimationProperty.POSE_MODIFIER, Animations.ReusableSources.COMBO_ATTACK_DIRECTION_MODIFIER);
    }

    public HeavyAttackAnimation(float convertTime, String path, Armature armature, Phase... phase) {
        super(convertTime, path, armature, phase);
        this.newTimePair(0.0F, Float.MAX_VALUE);
        this.addProperty(AnimationProperty.ActionAnimationProperty.CANCELABLE_MOVE, false);
        this.addProperty(AnimationProperty.StaticAnimationProperty.POSE_MODIFIER, Animations.ReusableSources.COMBO_ATTACK_DIRECTION_MODIFIER);


    }


    @Override
    public void postInit() {
        super.postInit();

        if (!this.properties.containsKey(AnimationProperty.AttackAnimationProperty.BASIS_ATTACK_SPEED)) {
            float basisSpeed = Float.parseFloat(String.format(Locale.US, "%.2f", (1.0F / this.getTotalTime())));
            this.addProperty(AnimationProperty.AttackAnimationProperty.BASIS_ATTACK_SPEED, basisSpeed);
        }
    }

    @Override
    public void end(LivingEntityPatch<?> entitypatch, DynamicAnimation nextAnimation, boolean isEnd) {
        super.end(entitypatch, nextAnimation, isEnd);

        boolean stiffAttack = entitypatch.getOriginal().level().getGameRules().getRule(EpicFightGamerules.STIFF_COMBO_ATTACKS).get();

        if (!isEnd && !nextAnimation.isMainFrameAnimation() && entitypatch.isLogicalClient() && !stiffAttack) {
            float playbackSpeed = EpicFightOptions.A_TICK * this.getPlaySpeed(entitypatch, this);
            entitypatch.getClientAnimator().baseLayer.copyLayerTo(entitypatch.getClientAnimator().baseLayer.getLayer(Layer.Priority.MIDDLE), playbackSpeed);
        }
    }

    @Override
    public TypeFlexibleHashMap<EntityState.StateFactor<?>> getStatesMap(LivingEntityPatch<?> entitypatch, float time) {
        TypeFlexibleHashMap<EntityState.StateFactor<?>> stateMap = super.getStatesMap(entitypatch, time);

        if (!entitypatch.getOriginal().level().getGameRules().getRule(EpicFightGamerules.STIFF_COMBO_ATTACKS).get()) {
            stateMap.put(EntityState.MOVEMENT_LOCKED, (Object) false);
            stateMap.put(EntityState.UPDATE_LIVING_MOTION, (Object) true);
        }

        return stateMap;
    }

    @Override
    protected Vec3 getCoordVector(LivingEntityPatch<?> entitypatch, DynamicAnimation dynamicAnimation) {
        Vec3 vec3 = super.getCoordVector(entitypatch, dynamicAnimation);

        if (entitypatch.shouldBlockMoving() && this.getProperty(AnimationProperty.ActionAnimationProperty.CANCELABLE_MOVE).orElse(false)) {
            vec3 = vec3.scale(0.0F);
        }

        return vec3;
    }

    @Override
    public Optional<JointMaskEntry> getJointMaskEntry(LivingEntityPatch<?> entitypatch, boolean useCurrentMotion) {
        if (entitypatch.isLogicalClient()) {
            if (entitypatch.getClientAnimator().getPriorityFor(this) == Layer.Priority.MIDDLE) {
                return Optional.of(JointMaskEntry.BASIC_ATTACK_MASK);
            }
        }

        return super.getJointMaskEntry(entitypatch, useCurrentMotion);
    }

    @Override
    public boolean isBasicAttackAnimation() {
        return false;
    }

    @Override
    public boolean shouldPlayerMove(LocalPlayerPatch playerpatch) {
        if (playerpatch.isLogicalClient()) {
            if (!playerpatch.getOriginal().level().getGameRules().getRule(EpicFightGamerules.STIFF_COMBO_ATTACKS).get()) {
                return playerpatch.getOriginal().input.forwardImpulse == 0.0F && playerpatch.getOriginal().input.leftImpulse == 0.0F;
            }
        }

        return true;
    }

}