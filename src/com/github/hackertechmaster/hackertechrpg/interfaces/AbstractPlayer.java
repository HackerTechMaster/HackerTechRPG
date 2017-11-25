package com.github.hackertechmaster.hackertechrpg.interfaces;

import com.github.hackertechmaster.hackertechrpg.util.Area;

public abstract class AbstractPlayer extends AbstractEntity {
    public AbstractPlayer(Area area) {
        super(area);
    }

    /**
     * @return 剩余货币
     */
    public abstract int getMoney();

    /**
     * 设置剩余货币
     * @param cnt
     */
    public abstract void setMoney(int cnt);

    /**
     * @return 可用体力
     */
    public abstract int getEnergyAvailable();

    /**
     * 设置剩余可用体力
     * @param cnt
     */
    public abstract void setEnergyAvailable(int cnt);

    /**
     * @return 体力上限
     */
    public abstract int getEnergyCapacity();
}
