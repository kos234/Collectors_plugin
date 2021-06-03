package progs.kos;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import progs.kos.nmsGetter.NMSManager;
import progs.kos.nmsGetter.v1_16_R3;

import java.io.File;
import java.util.regex.Pattern;

public class Main extends JavaPlugin {
    public NMSManager NMSManager;
    public String nmsVersion;
    public static final String TELEPORT = "CPTeleport"; //Телепортирует игрока на указанную дистанцию с указанным куллдауном, если игрок кликнет ПКМ
    public static final String SUPER_MOB = "CPSuperMob"; //Телепортирует игрока на указанную дистанцию с указанным куллдауном, если игрок кликнет ПКМ

    public static final String EFFECT_IN_MAIN_HAND = "CPEffectInMainHand"; //Выдает указанные эффекты, если предмет в главной руке
    public static final String EFFECT_IN_SECOND_HAND = "CPEffectInSecondHand"; //Выдает указанные эффекты, если предмет во второй руке
    public static final String EFFECT_IN_ARMOR = "CPEffectInArmor"; //Выдает указанные эффекты, если предмет надет на игрока
    public static final String EFFECT_IN_INVENTORY = "CPEffectInInventory"; //Выдает указанные эффекты, если предмет находится в инвентаре игрока
    public static final String ANIMATION_COLOR = "CPAnimationColor"; //Изменяет цвет кожаных вещей с анимацией

    public static final String PARTICLES_IN_MAIN_HAND = "CPParticlesInMainHand"; //Выдает указанные эффекты, если предмет в главной руке
    public static final String PARTICLES_IN_SECOND_HAND = "CPParticlesInSecondHand"; //Выдает указанные эффекты, если предмет во второй руке
    public static final String PARTICLES_IN_ARMOR = "CPParticlesInArmor"; //Выдает указанные эффекты, если предмет надет на игрока
    public static final String PARTICLES_IN_INVENTORY = "CPParticlesInInventory"; //Выдает указанные эффекты, если предмет находится в инвентаре игрока

    public static final String PARTICLES = "CPParticles"; //Спавит частицы в указанной позиции, когда предмет в главной руке
    public static final String LIGHTNING = "CPLightning"; //Призывает молнию на точку взгляда игрока с указанным куллдауном, если игрок кликнет ПКМ
    public static final String FIREWORK = "CPFirework"; //Призывает фейерверк в координаты игрока с указанным куллдауном, если игрок кликнет ПКМ
    public static final String SNOWBALL = "CPSnowball"; //Кидает снежки с указанной задержкой, если игрок зажимает ПКМ
    public static final String EGGS = "CPEggs"; //Кидает яйца с указанной задержкой, если игрок зажимает ПКМ
    public static final String ARROW = "CPArrow"; //Кидает стрелы с указанной задержкой, если игрок зажимает ПКМ
    public static final String BREAK = "CPBreak"; //Кидает стрелы с указанной задержкой, если игрок зажимает ПКМ

    //Enchanted
    public static final String AUTO_MELTING = "CPAutoMelting"; //Кидает стрелы с указанной задержкой, если игрок зажимает ПКМ
    public static final String TELEKINESIS = "CPTelekinesis"; //Кидает стрелы с указанной задержкой, если игрок зажимает ПКМ
    public static final String EXPERIENCES_BOOST = "CPExperiencesBoost"; //Кидает стрелы с указанной задержкой, если игрок зажимает ПКМ

    public static final String COOLDOWN  = "CPCooldown"; //Задержка перед чем-то. НЕ ДОБАВЛЯТЬ В ПРЕДМЕТ!!! ГЕНЕРИРУЕТСЯ САМО!!!!!!
    public static final String ID_THREAD  = "CPIdThread"; //Айди потока. НЕ ДОБАВЛЯТЬ В ПРЕДМЕТ!!! ГЕНЕРИРУЕТСЯ САМО!!!!!!
    public static final Pattern CheckCustomTag = Pattern.compile("^("+ SNOWBALL+ "|"+ BREAK + "|"+ AUTO_MELTING + "|"+ PARTICLES_IN_MAIN_HAND + "|"+ PARTICLES_IN_SECOND_HAND + "|"+ PARTICLES_IN_ARMOR + "|"+ PARTICLES_IN_INVENTORY + "|"+ EXPERIENCES_BOOST + "|"+ TELEKINESIS + "|"+ ID_THREAD + "|" + ANIMATION_COLOR + "|"+ ARROW + "|" + EGGS + "|" +TELEPORT +"|"+ EFFECT_IN_MAIN_HAND +"|"+ EFFECT_IN_SECOND_HAND +"|"+ EFFECT_IN_ARMOR +"|"+ EFFECT_IN_INVENTORY +"|"+ PARTICLES +"|"+ LIGHTNING +"|"+ FIREWORK +"|"+ SUPER_MOB +"|"+ COOLDOWN +")$");
    /*tags
    CPTeleport{distance, CD}
    CPInMainHand[{id:,lvl:}, {}]
    CPInSecondHand[{id:,lvl:}, {}]
    {CPInSecondHand:{Particles:1, Data:[{Id:LEVITATION,Lvl:2}]}}
    {CPInArmor:{Effects:{Particles:-1 0 1, Data:[{Id:INCREASE_DAMAGE,Lvl:2}, {}]}}
    {CPParticlesInMainHand{CD:20, Sleep:10, Particles:{[Type:Name,OffsetX:0.23,OffsetY:4.3,OffsetZ:34]}}}
    {CPInInventory:{Particles:-1, Data:[{Id:LEVITATION,Lvl:1}]}}
    {CPEffectInArmor:{Particles:-1, Data:[{Id:LEVITATION,Lvl:1}]}}
    {CPEffectInInventory:{Particles:-1, Data:[{Id:LEVITATION,Lvl:1}]}}
    CPEffectInInventory[{id:,lvl:}, {}]
    CPParticles[] - wtf?
    CPLightning: delay
    CPFirework: delay
    CPInvertMove:1
    CPInvertMouse: 1
    {CPFirework:{Meta:Random,CD:0}
    {CPFirework:{Meta:{MainColor:[1,2],FadeColor:[3,4],Type:2,Power:1,Effect:1},CD:2}}
    COOLDOWN[{Event, time}]
    {CPAnimationColor:{Colors:[ffffff,000000],Speed:20}}
    {CPAnimationColor:{Colors:[ffffff,000000],Speed:20,Stage:3}}
    {CPAnimationColor:{Colors:[ff0000,ffa500,ffff00,008000,42aaff,0000ff,8b00ff],Speed:20,Stage:3}}
    {CPSuperMob:{Type:Snowman,Effects:{Particles:1, Data:[{Id:FIRE_RESISTANCE,Lvl:2}]}}}
    {CPBreak:{Size:3x3x3,BreakItem:1},CPExperiencesBoost:20,CPTelekinesis:1}

    {CPSuperMob:{Type:IronGolem,MobType:d,Effects:{Particles:1, Data:[{Id:INCREASE_DAMAGE,Lvl:2}]}}}
    {CPParticlesInMainHand:{CD:20,Particles:[{Type:Random,OffsetX:0,OffsetY:4.3,OffsetZ:0}]}}
 */

    @Override
    public void onEnable() {
        File texts = new File(getDataFolder() + File.separator + "config.yml");
        if(!texts.exists()){
            getConfig().options().copyDefaults(true);
            saveDefaultConfig();
        }

        nmsVersion = getServer().getClass().getPackage().getName();
        nmsVersion = nmsVersion.substring(nmsVersion.lastIndexOf(".") + 1);
        switch (nmsVersion){
            case "v1_16_R3":
                NMSManager = new v1_16_R3();
                break;
        }
        Bukkit.getPluginManager().registerEvents(new Handler(this), this);
    }
}
