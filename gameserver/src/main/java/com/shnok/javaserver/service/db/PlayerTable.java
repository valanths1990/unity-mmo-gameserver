package com.shnok.javaserver.service.db;

import com.shnok.javaserver.db.entity.DBCharTemplate;
import com.shnok.javaserver.db.entity.DBCharacter;
import com.shnok.javaserver.db.entity.DBLevelUpGain;
import com.shnok.javaserver.db.repository.CharTemplateRepository;
import com.shnok.javaserver.db.repository.CharacterRepository;
import com.shnok.javaserver.db.repository.LvlUpGainRepository;
import com.shnok.javaserver.enums.ClassId;
import lombok.extern.log4j.Log4j2;

import java.util.List;
import java.util.Random;

@Log4j2
public class PlayerTable {
    private final CharacterRepository characterRepository;
    private static PlayerTable instance;
    public static PlayerTable getInstance() {
        if (instance == null) {
            instance = new PlayerTable();
        }
        return instance;
    }

    public PlayerTable() {
        characterRepository = new CharacterRepository();
    }

    public DBCharacter getRandomCharacter() {
        return characterRepository.getRandomCharacter();
    }

    public DBCharacter getCharacterById(int id) {
        return characterRepository.getCharacterById(id);
    }

    public List<DBCharacter> getCharactersForAccount(String account) {
        return characterRepository.getCharactersForAccount(account);
    }

    public void saveOrUpdateCharacter(DBCharacter character) {
        characterRepository.saveOrUpdateCharacter(character);
    }

    public void createRandomCharForAccount(String account) {
        log.info("Creating random character for account {}.", account);
        Random random = new Random();
        byte[] classes = {
                0x1f,
                0x26,
                0x35
        };

        DBCharTemplate charTemplate = CharTemplateTable.getInstance().
                getTemplateByClassId(classes[random.nextInt(3)]);

        int face = random.nextInt(4);
        int hairStyle = random.nextInt(4);
        int hairColor = random.nextInt(4);

        float posX = 4724.32f;
        float posY = -68.00f;
        float posZ = -1731.24f;

        DBCharacter dbCharacter = new DBCharacter();
        dbCharacter.setCharName(account);
        dbCharacter.setAccountName(account);
        dbCharacter.setClassId(ClassId.getById((byte) charTemplate.getClassId()));
        dbCharacter.setRace(charTemplate.getRace());
        dbCharacter.setCritical(charTemplate.getCritical());
        dbCharacter.setEvasion(charTemplate.getEvasion());
        dbCharacter.setMAtk(charTemplate.getMAtk());
        dbCharacter.setMDef(charTemplate.getMDef());
        dbCharacter.setMSpd(charTemplate.getMAtkSpd());
        dbCharacter.setPAtk(charTemplate.getPAtk());
        dbCharacter.setRunSpd(charTemplate.getMoveSpd());
        dbCharacter.setStr((byte) charTemplate.getStr());
        dbCharacter.setCon((byte) charTemplate.getCon());
        dbCharacter.setDex((byte) charTemplate.getDex());
        dbCharacter.set_int((byte) charTemplate.get_int());
        dbCharacter.setMen((byte) charTemplate.getMen());
        dbCharacter.setColR(charTemplate.getCollisionHeightFemale());
        dbCharacter.setColH(charTemplate.getCollisionHeightMale());
        dbCharacter.setMen((byte) charTemplate.getMen());

        //HP MP CP

        dbCharacter.setPosX(posX);
        dbCharacter.setPosY(posY);
        dbCharacter.setPosZ(posZ);
        dbCharacter.setFace((byte) face);
        dbCharacter.setHairColor((byte) hairColor);
        dbCharacter.setHairStyle((byte) hairStyle);

        dbCharacter.setCharSlot((byte) 0);

        DBLevelUpGain levelUpGain = LvlUpGainTable.getInstance().
                getCharacterByClassId(charTemplate.getClassId());

        dbCharacter.setMaxHp((int) (levelUpGain.getDefaultHpBase() + levelUpGain.getDefaultHpAdd()));
        dbCharacter.setMaxMp((int) (levelUpGain.getDefaultMpBase() + levelUpGain.getDefaultMpAdd()));
        dbCharacter.setMaxCp((int) (levelUpGain.getDefaultCpAdd() + levelUpGain.getDefaultCpAdd()));

        saveOrUpdateCharacter(dbCharacter);
    }
}
