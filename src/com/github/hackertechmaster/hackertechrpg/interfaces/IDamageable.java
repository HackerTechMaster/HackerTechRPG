package com.github.hackertechmaster.hackertechrpg.interfaces;

public interface IDamageable {
    /**
     * @return 血量上限
     */
    int getHealthLimit();
    void setHealthLimit(int healthLimit);

    /**
     * @return 剩余血量
     */
    int getHealth();
    void setHealth(int health);
}
