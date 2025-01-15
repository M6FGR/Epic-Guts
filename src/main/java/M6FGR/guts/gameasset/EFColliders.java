package M6FGR.guts.gameasset;

import yesman.epicfight.api.collider.Collider;
import yesman.epicfight.api.collider.MultiOBBCollider;

public class EFColliders {
    public static final Collider GLADIUS = new MultiOBBCollider(3, 0.6, 0.6, 1.0, 0.0, 0.0, -1.0);
    public static final Collider KUTEN = new MultiOBBCollider(3, 1.6, 1.6, 1.6, 0.0, 1.5, -1.0);
    public static final Collider GLADIUS_AUTO2 = new MultiOBBCollider(3, 1.6, 1.6, 1.6, 0.0, 1.5, -1.0);
    public static final Collider GROUNDSLAM_KUTEN = new MultiOBBCollider(5, 2.6, 2.6, 2.6, 0.0, 1.9, 0.0);
    public EFColliders() {
    }
}