package com.github.hackertechmaster.hackertechrpg.interfaces;

public interface IPlayer extends IEntity {
    /**
     * @return 剩余货币
     */
    int getMoney();

    /**
     * 设置剩余货币
     * @param cnt
     */
    void setMoney(int cnt);

    /**
     * @return 可用体力
     */
    int getEnergyAvailable();

    /**
     * 设置剩余可用体力
     * @param cnt
     */
    void setEnergyAvailable(int cnt);

    /**
     * @return 体力上限
     */
    int getEnergyCapacity();
}
