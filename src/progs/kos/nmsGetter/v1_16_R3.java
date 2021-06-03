package progs.kos.nmsGetter;

import net.minecraft.server.v1_16_R3.*;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftEntity;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_16_R3.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import progs.kos.Handler;
import progs.kos.Main;

import java.util.*;

public class v1_16_R3 implements NMSManager {

    private static final Map<String, ParticleType> allParticlesData = new HashMap<String, ParticleType>(){{
        put("AMBIENT_ENTITY_EFFECT", Particles.AMBIENT_ENTITY_EFFECT);
        put("ANGRY_VILLAGER", Particles.ANGRY_VILLAGER);
        put("BARRIER", Particles.BARRIER);
        put("CLOUD", Particles.CLOUD);
        put("CRIT", Particles.CRIT);
        put("DAMAGE_INDICATOR", Particles.DAMAGE_INDICATOR);
        put("DRAGON_BREATH", Particles.DRAGON_BREATH);
        put("DRIPPING_LAVA", Particles.DRIPPING_LAVA);
        put("FALLING_LAVA", Particles.FALLING_LAVA);
        put("DRIPPING_WATER", Particles.DRIPPING_WATER);
        put("FALLING_WATER", Particles.FALLING_WATER);
        put("EFFECT", Particles.EFFECT);
        put("ELDER_GUARDIAN", Particles.ELDER_GUARDIAN);
        put("ENCHANTED_HIT", Particles.ENCHANTED_HIT);
        put("ENCHANT", Particles.ENCHANT);
        put("END_ROD", Particles.END_ROD);
        put("ENTITY_EFFECT", Particles.ENTITY_EFFECT);
        put("EXPLOSION_EMITTER", Particles.EXPLOSION_EMITTER);
        put("EXPLOSION", Particles.EXPLOSION);
        put("FIREWORK", Particles.FIREWORK);
        put("FISHING", Particles.FISHING);
        put("FLAME", Particles.FLAME);
        put("SOUL_FIRE_FLAME", Particles.SOUL_FIRE_FLAME);
        put("SOUL", Particles.SOUL);
        put("FLASH", Particles.FLASH);
        put("HAPPY_VILLAGER", Particles.HAPPY_VILLAGER);
        put("COMPOSTER", Particles.COMPOSTER);
        put("HEART", Particles.HEART);
        put("INSTANT_EFFECT", Particles.INSTANT_EFFECT);
        put("ITEM_SLIME", Particles.ITEM_SLIME);
        put("ITEM_SNOWBALL", Particles.ITEM_SNOWBALL);
        put("LARGE_SMOKE", Particles.LARGE_SMOKE);
        put("LAVA", Particles.LAVA);
        put("MYCELIUM", Particles.MYCELIUM);
        put("NOTE", Particles.NOTE);
        put("POOF", Particles.POOF);
        put("PORTAL", Particles.PORTAL);
        put("RAIN", Particles.RAIN);
        put("SMOKE", Particles.SMOKE);
        put("SNEEZE", Particles.SNEEZE);
        put("SPIT", Particles.SPIT);
        put("SQUID_INK", Particles.SQUID_INK);
        put("SWEEP_ATTACK", Particles.SWEEP_ATTACK);
        put("TOTEM_OF_UNDYING", Particles.TOTEM_OF_UNDYING);
        put("UNDERWATER", Particles.UNDERWATER);
        put("SPLASH", Particles.SPLASH);
        put("WITCH", Particles.WITCH);
        put("BUBBLE_POP", Particles.BUBBLE_POP);
        put("CURRENT_DOWN", Particles.CURRENT_DOWN);
        put("BUBBLE_COLUMN_UP", Particles.BUBBLE_COLUMN_UP);
        put("NAUTILUS", Particles.NAUTILUS);
        put("DOLPHIN", Particles.DOLPHIN);
        put("CAMPFIRE_COSY_SMOKE", Particles.CAMPFIRE_COSY_SMOKE);
        put("CAMPFIRE_SIGNAL_SMOKE", Particles.CAMPFIRE_SIGNAL_SMOKE);
        put("DRIPPING_HONEY", Particles.DRIPPING_HONEY);
        put("FALLING_HONEY", Particles.FALLING_HONEY);
        put("LANDING_HONEY", Particles.LANDING_HONEY);
        put("FALLING_NECTAR", Particles.FALLING_NECTAR);
        put("ASH", Particles.ASH);
        put("CRIMSON_SPORE", Particles.CRIMSON_SPORE);
        put("WARPED_SPORE", Particles.WARPED_SPORE);
        put("DRIPPING_OBSIDIAN_TEAR", Particles.DRIPPING_OBSIDIAN_TEAR);
        put("FALLING_OBSIDIAN_TEAR", Particles.FALLING_OBSIDIAN_TEAR);
        put("LANDING_OBSIDIAN_TEAR", Particles.LANDING_OBSIDIAN_TEAR);
        put("REVERSE_PORTAL", Particles.REVERSE_PORTAL);
        put("WHITE_ASH", Particles.WHITE_ASH);
    }};
    private static final ArrayList<String> allKeys = new ArrayList<String>(allParticlesData.keySet());

    @Override
    public Map<String, ConstructorNBTData>  getNBTKeys(ItemStack itemStack) {
        Map<String, ConstructorNBTData>  nbtData = new HashMap<>();
        NBTTagCompound tag = CraftItemStack.asNMSCopy(itemStack).getTag();
        if(tag == null) return nbtData;

        for (String key: tag.getKeys()) {
            if (Main.CheckCustomTag.matcher(key).find())
                switch (key){
                    case Main.ID_THREAD:
                    case Main.COOLDOWN:
                        Map<String, ConstructorNBTData> constructor = new HashMap<>();
                        NBTTagList cooldowns = (NBTTagList) tag.get(key);
                        for (int i = 0; i < cooldowns.size(); i++) {
                            NBTTagCompound cd = cooldowns.getCompound(i);
                            constructor.put(cd.getString("Event"), new ConstructorNBTData(cd.getInt(Main.COOLDOWN.equals(key) ? "CD" : "ID")));
                        }
                        nbtData.put(key, new ConstructorNBTData(constructor));
                        break;

                    case Main.LIGHTNING:
                    case Main.ARROW:
                    case Main.SNOWBALL:
                    case Main.EGGS:
                    case Main.AUTO_MELTING:
                    case Main.EXPERIENCES_BOOST:
                    case Main.TELEKINESIS:
                        nbtData.put(key, new ConstructorNBTData(tag.getInt(key)));
                        break;

                    case Main.TELEPORT:
                        Map<String, ConstructorNBTData> constructorNBTDataMap = new HashMap<>();
                        NBTTagCompound tagCompound = (NBTTagCompound) tag.get(key);
                        constructorNBTDataMap.put("Distance", new ConstructorNBTData(tagCompound.getInt("Distance")));
                        constructorNBTDataMap.put("CD", new ConstructorNBTData(tagCompound.getInt("CD")));
                        nbtData.put(key, new ConstructorNBTData(constructorNBTDataMap));
                        break;

                    case Main.FIREWORK:
                        NBTTagCompound nbtTagFirework = tag.getCompound(key), fireworkMeta;
                        Map<String, ConstructorNBTData> firework = new HashMap<>();
                        firework.put("CD", new ConstructorNBTData(nbtTagFirework.getInt("CD")));
                        if(tag.getCompound(key).get("Meta").asString().equals("Random"))
                            firework.put("Meta", new ConstructorNBTData(nbtTagFirework.getString("Meta")));
                        else {
                            fireworkMeta = nbtTagFirework.getCompound("Meta");
                            firework.put("Meta", new ConstructorNBTData(stringToIntArray(fireworkMeta.get("MainColor").asString()),
                                stringToIntArray(fireworkMeta.get("FadeColor").asString()), fireworkMeta.getInt("Type"),
                                fireworkMeta.getInt("Power"), fireworkMeta.getInt("Effect")));
                        }
                        nbtData.put(key, new ConstructorNBTData(firework));

                        break;

                    case Main.ANIMATION_COLOR:
                        NBTTagCompound nbtTagCompound = tag.getCompound(key);
                        String string = nbtTagCompound.get("Colors").asString().replace("\"", "").replace("'", "");
                        Map<String, ConstructorNBTData> stringConstructorNBTDataMap = new HashMap<>();
                        ArrayList<String> strings = new ArrayList<>(Arrays.asList(string.substring(1, string.length()-1).split(",")));
                        stringConstructorNBTDataMap.put("Colors", new ConstructorNBTData(strings));
                        stringConstructorNBTDataMap.put("Speed", new ConstructorNBTData(nbtTagCompound.getInt("Speed")));
                        if(nbtTagCompound.getInt("Stage") != 0)
                            stringConstructorNBTDataMap.put("Stage", new ConstructorNBTData(nbtTagCompound.getInt("Stage")));
                        nbtData.put(key, new ConstructorNBTData(stringConstructorNBTDataMap));
                        break;

                    case Main.EFFECT_IN_ARMOR:
                    case Main.EFFECT_IN_INVENTORY:
                    case Main.EFFECT_IN_MAIN_HAND:
                    case Main.EFFECT_IN_SECOND_HAND:
                        NBTTagCompound dataEffect = tag.getCompound(key);
                        int particles = dataEffect.getInt("Particles");
                        NBTTagList tags = (NBTTagList) dataEffect.get("Data");
                        ArrayList<Map<String, ConstructorNBTData>> constructorEffect = new ArrayList<>();
                        for (int i = 0; i < tags.size(); i++) {
                            NBTTagCompound effect = tags.getCompound(i);
                            Map<String, ConstructorNBTData> data = new HashMap<>();
                            data.put("Id", new ConstructorNBTData(effect.getString("Id")));
                            data.put("Lvl", new ConstructorNBTData(effect.getInt("Lvl")));
                            data.put("Particles", new ConstructorNBTData(particles));
                            constructorEffect.add(data);
                        }
                        nbtData.put(key, new ConstructorNBTData(constructorEffect));
                        break;

                    case Main.PARTICLES_IN_ARMOR:
                    case Main.PARTICLES_IN_INVENTORY:
                    case Main.PARTICLES_IN_MAIN_HAND:
                    case Main.PARTICLES_IN_SECOND_HAND:
                        NBTTagCompound dataParticles = tag.getCompound(key);
                        Map<String, ConstructorNBTData> mapParticles = new HashMap<>();
                        mapParticles.put("CD", new ConstructorNBTData(dataParticles.getInt("CD")));
                        ArrayList<Map<String, ConstructorNBTData>> allParticles = new ArrayList<>();
                        NBTTagList tagsParticles = (NBTTagList) dataParticles.get("Particles");
                        for (int i = 0; i < tagsParticles.size(); i++) {
                            NBTTagCompound effect = tagsParticles.getCompound(i);
                            Map<String, ConstructorNBTData> data = new HashMap<>();
                            data.put("Type", new ConstructorNBTData(effect.getString("Type")));
                            data.put("OffsetX", new ConstructorNBTData(effect.getFloat("OffsetX")));
                            data.put("OffsetY", new ConstructorNBTData(effect.getFloat("OffsetY")));
                            data.put("OffsetZ", new ConstructorNBTData(effect.getFloat("OffsetZ")));
                            allParticles.add(data);
                        }
                        mapParticles.put("Particles", new ConstructorNBTData(allParticles));
                        nbtData.put(key, new ConstructorNBTData(mapParticles));
                        break;

                    case Main.SUPER_MOB:
                        NBTTagCompound dataEntity = tag.getCompound(key);
                        Map<String, ConstructorNBTData> constructorEntity = new HashMap<>();
                        constructorEntity.put("Type", new ConstructorNBTData(dataEntity.getString("Type")));
                        if(dataEntity.getString("MobType") != null){
                            constructorEntity.put("MobType", new ConstructorNBTData(dataEntity.getString("MobType")));
                        }

                        NBTTagCompound entityEffects = dataEntity.getCompound("Effects");
                        int particlesEffect = entityEffects.getInt("Particles");
                        NBTTagList tagsEffects = (NBTTagList) entityEffects.get("Data");
                        ArrayList<Map<String, ConstructorNBTData>> effectList = new ArrayList<>();
                        for (int i = 0; i < tagsEffects.size(); i++) {
                            NBTTagCompound effect = tagsEffects.getCompound(i);
                            Map<String, ConstructorNBTData> data = new HashMap<>();
                            data.put("Id", new ConstructorNBTData(effect.getString("Id")));
                            data.put("Lvl", new ConstructorNBTData(effect.getInt("Lvl")));
                            data.put("Particles", new ConstructorNBTData(particlesEffect));
                            effectList.add(data);
                        }
                        constructorEntity.put("Effects", new ConstructorNBTData(effectList));
                        nbtData.put(key, new ConstructorNBTData(constructorEntity));
                        break;
                    case Main.BREAK:
                        NBTTagCompound dataBreak = tag.getCompound(key);
                        Map<String, ConstructorNBTData> constructorBreak = new HashMap<>();
                        String[] coords = dataBreak.getString("Size").toLowerCase(Locale.ROOT).split("x");
                        int[] offsets = new int[coords.length];
                        for (int i = 0; i < coords.length; i++) {
                            offsets[i] = Integer.parseInt(coords[i]);
                            if(offsets[i] % 2 == 0)
                                offsets[i] ++;
                        }
                        constructorBreak.put("Size", new ConstructorNBTData(offsets));
                        constructorBreak.put("BreakItem", new ConstructorNBTData(dataBreak.getInt("BreakItem")));
                        nbtData.put(key, new ConstructorNBTData(constructorBreak));
                        break;
                    default:
                        nbtData.put(key, new ConstructorNBTData(tag.get(key).asString()));
                        break;
                }
        }
        return nbtData;
    }

    public int[] stringToIntArray(String s){ //Может я идиот, но NBTTagCompound.getIntArray() return []
        String[] intString = s.substring(1, s.length()-1).split(",");
        int[] ints = new int[intString.length];
        for (int i = 0; i < intString.length; i++) {
            ints[i] = Integer.parseInt(intString[i]);
        }
        return ints;
    }

    @Override
    public ItemStack setData(String key, ItemStack itemStack, String event, int nextTime) {
        net.minecraft.server.v1_16_R3.ItemStack item = CraftItemStack.asNMSCopy(itemStack);
        NBTTagCompound tag = item.getTag();
        NBTTagList cooldowns = (NBTTagList) tag.get(key);
        boolean isUpdate = false;
        if(cooldowns != null) {
            for (int i = 0; i < cooldowns.size(); i++) {
                NBTTagCompound cd = cooldowns.getCompound(i);
                if (cd.getString("Event").equals(event)) {
                    cd.setInt(key.equals(Main.COOLDOWN) ? "CD" : "ID", nextTime);
                    isUpdate = true;
                }
            }
        }else{
            cooldowns = new NBTTagList();
            tag.set(key, cooldowns);
        }
        if(!isUpdate){
            NBTTagCompound cd = new NBTTagCompound();
            cd.setString("Event", event);
            cd.setInt(key.equals(Main.COOLDOWN) ? "CD" : "ID", nextTime);
            cooldowns.add(cd);
        }
        item.setTag(tag);
        return CraftItemStack.asBukkitCopy(item);
    }

    @Override
    public void setNBTEntity(ItemStack eggs, org.bukkit.entity.Entity entity) {
        net.minecraft.server.v1_16_R3.ItemStack item = CraftItemStack.asNMSCopy(eggs);
        Entity newEntity = ((CraftEntity) entity).getHandle();
        NBTTagCompound tag = newEntity.save(new NBTTagCompound());
        NBTTagCompound test = item.getTag().getCompound("EntityTag");
        for (String key: test.getKeys()) {
            tag.set(key, test.get(key));
        }
        newEntity.load(tag);
    }

    @Override
    public int createParticles(Player player, Map<String, ConstructorNBTData> mapParticles, Main main) {
        Location location = player.getLocation();
        PacketPlayOutWorldParticles packetPlayOutWorldParticles = new PacketPlayOutWorldParticles(Particles.NOTE, true, location.getX(), location.getY(), location.getZ(), 0, 0, 0, 0, 0);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packetPlayOutWorldParticles);

        return Bukkit.getScheduler().scheduleSyncRepeatingTask(main, new Runnable() {
            boolean isRandom = false;
            @Override
            public void run() {
                float red = 0;
                float green = 0;
                float blue = 0;
                Location location = player.getLocation();
                ArrayList<Map<String, ConstructorNBTData>> mapArrayList = (ArrayList<Map<String, ConstructorNBTData>>) mapParticles.get("Particles").arrayData;
                for (Map<String, ConstructorNBTData> dataParticles: mapArrayList) {
                    isRandom = dataParticles.get("Type").data.equals("Random");
                    if(allParticlesData.containsKey(dataParticles.get("Type").data))
                        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(new PacketPlayOutWorldParticles(allParticlesData.get(dataParticles.get("Type").data), true, location.getX() + dataParticles.get("OffsetX").floatValue, location.getY() + dataParticles.get("OffsetY").floatValue, location.getZ() + dataParticles.get("OffsetZ").floatValue,red, green, blue, 0, 100));
                    else {
                        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(new PacketPlayOutWorldParticles(allParticlesData.get(allKeys.get(Handler.random(allKeys.size() - 1))), true, location.getX() + dataParticles.get("OffsetX").floatValue, location.getY() + dataParticles.get("OffsetY").floatValue, location.getZ() + dataParticles.get("OffsetZ").floatValue, 0, 0, 0, 0, 0));
                    }
                }
            }
        }, 0, mapParticles.get("CD").dataInt);
//        return 0;
    }


}
