package com.github.hackertechmaster.hackertechrpg.interfaces;

public abstract class AbstractPlayerRegistry implements IPrintable {
    /**
     * 暂时不需要密码
     * @param name 人物名
     * @return 注册成功返回true，否则返回false
     */
    public abstract boolean register(String name);

    /**
     * 通过人物名切换当前角色
     * @param name 人物名
     * @return 角色存在则返回true，否则返回false
     */
    public abstract boolean loginWithName(String name);

    /**
     * @return 返回当前激活的玩家
     */
    public abstract AbstractPlayer getCurrentPlayer();

    /**
     * @param name 人物名
     * @return 用户
     */
    public abstract AbstractPlayer findPlayerByName(String name);
}
