package M6FGR.guts.world.capabilities.item;


import yesman.epicfight.world.capabilities.item.WeaponCategory;

public enum EFCategories implements WeaponCategory {
    DRAGONSLAYER;

    final int id;

    private EFCategories() {
        this.id = WeaponCategory.ENUM_MANAGER.assign(this);
    }

    public int universalOrdinal() {
        return this.id;
    }
}
