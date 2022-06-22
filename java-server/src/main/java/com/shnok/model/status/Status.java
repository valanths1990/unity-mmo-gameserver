package com.shnok.model.status;

public abstract class Status {
    protected int _maxHp;
    protected int _hp;
    protected int _level;
    protected float _moveSpeed = 1f;

    public Status() {
        /*_maxHp = 100;
        _hp = _maxHp;
        _maxStamina = 100;
        _stamina = _maxStamina;
        _level = 1;
        _exp = 0;
        _maxExp = 100;*/
    }

    protected Status(int level, int maxHp) {
        this._maxHp = maxHp;
        this._hp = maxHp;
        this._level = level;
    }

    public void setCurrentHp(int value) {
        _hp = value;
    }

    public int getCurrentHp() {
        return _hp;
    }

    public int getMaxHp() {
        return _maxHp;
    }

    public void setLevel(int value) {
        _level = value;
    }

    public int getLevel() {
        return _level;
    }

    public float getMoveSpeed() {
        return _moveSpeed;
    }
}
