package progs.kos;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.*;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.*;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import progs.kos.nmsGetter.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.inventory.ItemStack;

import java.util.*;
import java.util.regex.Pattern;

public class Handler implements Listener {

    private static final Map<ItemStack, Integer> threadsId = new HashMap<>();
    private static final Map<String, Class> allEntity = new HashMap<String, Class>(){{
        put("ArmorStand", ArmorStand.class);
        put("Bat", Bat.class);
        put("Bee", Bee.class);
        put("Blaze", Blaze.class);
        put("Boat", Boat.class);
        put("Cat", Cat.class);
        put("CaveSpider", CaveSpider.class);
        put("Chicken", Chicken.class);
        put("Cod", Cod.class);
        put("Cow", Cow.class);
        put("Creeper", Creeper.class);
        put("Dolphin", Dolphin.class);
        put("Donkey", Donkey.class);
        put("Drowned", Drowned.class);
        put("ElderGuardian", ElderGuardian.class);
        put("EnderDragon", EnderDragon.class);
        put("Enderman", Enderman.class);
        put("Endermite", Endermite.class);
        put("Evoker", Endermite.class);
        put("EvokerFangs", EvokerFangs.class);
        put("ExperienceOrb", ExperienceOrb.class);
        put("Fireball", Fireball.class);
        put("Fox", Fox.class);
        put("Ghast", Ghast.class);
        put("Giant", Giant.class);
        put("Hoglin", Hoglin.class);
        put("Horse", Horse.class);
        put("Husk", Husk.class);
        put("Illager", Illager.class);
        put("Illusioner", Illusioner.class);
        put("IronGolem", IronGolem.class);
        put("ItemFrame", ItemFrame.class);
        put("LargeFireball", LargeFireball.class);
        put("Llama", Llama.class);
        put("LlamaSpit", LlamaSpit.class);
        put("MagmaCube", MagmaCube.class);
        put("Minecart", Minecart.class);
        put("Mule", Mule.class);
        put("MushroomCow", MushroomCow.class);
        put("Ocelot", Ocelot.class);
        put("Painting", Painting.class);
        put("Panda", Panda.class);
        put("Parrot", Parrot.class);
        put("Phantom", Phantom.class);
        put("Pig", Pig.class);
        put("Piglin", Piglin.class);
        put("PiglinBrute", PiglinBrute.class);
        put("PigZombie", PigZombie.class);
        put("Pillager", Pillager.class);
        put("PolarBear", PolarBear.class);
        put("PufferFish", PufferFish.class);
        put("Rabbit", Rabbit.class);
        put("Ravager", Ravager.class);
        put("Salmon", Salmon.class);
        put("Sheep", Sheep.class);
        put("Shulker", Shulker.class);
        put("ShulkerBullet", ShulkerBullet.class);
        put("Silverfish", Silverfish.class);
        put("SizedFireball", SizedFireball.class);
        put("Skeleton", Skeleton.class);
        put("SkeletonHorse", SkeletonHorse.class);
        put("Slime", Slime.class);
        put("SmallFireball", SmallFireball.class);
        put("Snowball", Snowball.class);
        put("Snowman", Snowman.class);
        put("Spider", Spider.class);
        put("Squid", Squid.class);
        put("Stray", Stray.class);
        put("Strider", Strider.class);
        put("TNTPrimed", TNTPrimed.class);
        put("TraderLlama", TraderLlama.class);
        put("TropicalFish", TropicalFish.class);
        put("Turtle", Turtle.class);
        put("Vex", Vex.class);
        put("Villager", Villager.class);
        put("Vindicator", Vindicator.class);
        put("WanderingTrader", WanderingTrader.class);
        put("Witch", Witch.class);
        put("WitherSkeleton", WitherSkeleton.class);
        put("WitherSkull", WitherSkull.class);
        put("Wolf", Wolf.class);
        put("Zoglin", Zoglin.class);
        put("Zombie", Zombie.class);
        put("ZombieHorse", ZombieHorse.class);
        put("ZombieVillager", ZombieVillager.class);
    }};
//    Bat, Bee, Blaze, Cat(Types), CaveSpider, Chicken, Cod, Cow, Creeper, Dolphin, Donkey, Drowned
    private static Main main;
    private static final Color[] COLORS = new Color[]{Color.WHITE, Color.SILVER, Color.GRAY, Color.BLACK, Color.RED, Color.MAROON, Color.YELLOW, Color.OLIVE, Color.LIME, Color.GREEN, Color.AQUA, Color.TEAL, Color.BLUE, Color.NAVY, Color.FUCHSIA, Color.PURPLE, Color.ORANGE};
    private static final FireworkEffect.Type[] TYPES = new FireworkEffect.Type[]{FireworkEffect.Type.BALL, FireworkEffect.Type.BALL_LARGE, FireworkEffect.Type.STAR ,FireworkEffect.Type.BURST, FireworkEffect.Type.CREEPER};

    public Handler(Main main) {
        Handler.main = main;
    }

    @EventHandler
    public void onClick(PlayerInteractEvent interactEvent){
        Player player = interactEvent.getPlayer();

        if(player.getInventory().getItemInMainHand().getType().equals(Material.SPLASH_POTION)) {
            if(player.getInventory().getItemInMainHand().getAmount() == 1) {
                onUnsetInMainHandItem(player, player.getInventory().getItemInMainHand());
                onUnsetInInventory(player, player.getInventory().getItemInMainHand());
            }
        }

        if(interactEvent.getAction() == Action.RIGHT_CLICK_BLOCK && !player.getInventory().getType().equals(InventoryType.CREATIVE))
            if((player.getInventory().getItemInMainHand().getType().isBlock() || Pattern.compile("("+ Material.ITEM_FRAME + "|" + Material.ARMOR_STAND + "|" + Material.REDSTONE + "|" + Material.PAINTING + "|" + "BOAT" + "|" + Material.STRING + "|" + "SPAWN_EGG" + "|" + Material.FIREWORK_ROCKET +")").matcher(player.getInventory().getItemInMainHand().getType().toString()).find() || (player.getInventory().getItemInMainHand().getType().toString().contains("MINECART") && (interactEvent.getClickedBlock() != null && interactEvent.getClickedBlock().getType().toString().contains("RAIL"))) || ((player.getInventory().getItemInMainHand().getType().equals(Material.WRITABLE_BOOK) || player.getInventory().getItemInMainHand().getType().equals(Material.WRITTEN_BOOK) && (interactEvent.getClickedBlock() != null && interactEvent.getClickedBlock().getType().equals(Material.LECTERN)))))){
                if(player.getInventory().getItemInMainHand().getAmount() == 1) {
                    onUnsetInMainHandItem(player, player.getInventory().getItemInMainHand());
                    onUnsetInInventory(player, player.getInventory().getItemInMainHand());
                }
            }

        if(interactEvent.getAction() == Action.RIGHT_CLICK_AIR || interactEvent.getAction() == Action.RIGHT_CLICK_BLOCK){
            if(Pattern.compile("(HELMET|CHESTPLATE|LEGGINGS|BOOTS)").matcher(player.getInventory().getItemInMainHand().getType().toString()).find()) {
                onSetArmor(player, player.getInventory().getItemInMainHand());
                return;
            }
        }

        if(interactEvent.getAction() == Action.RIGHT_CLICK_AIR) {
            ItemStack item = player.getInventory().getItemInMainHand();
            Map<String, ConstructorNBTData> NBTList = main.NMSManager.getNBTKeys(item);
            for (Map.Entry<String, ConstructorNBTData> dataHash : NBTList.entrySet()) {
                switch (dataHash.getKey()) {
                    case Main.LIGHTNING:
                        if (!getCooldown(NBTList, player, dataHash.getKey())) return;
                        player.getWorld().strikeLightning(player.getTargetBlock(null, 50).getLocation());
                        player.getInventory().setItemInMainHand(main.NMSManager.setData(Main.COOLDOWN, item, dataHash.getKey(), dataHash.getValue().dataInt + (int) (System.currentTimeMillis() / 1000)));
                        break;

                    case Main.ARROW:
                    case Main.SNOWBALL:
                    case Main.EGGS:
                        if (!getCooldown(NBTList, player, dataHash.getKey())) return;
                        player.launchProjectile(dataHash.getKey().equals(Main.ARROW) ? Arrow.class : (dataHash.getKey().equals(Main.SNOWBALL) ? Snowball.class : Egg.class));
                        player.getInventory().setItemInMainHand(main.NMSManager.setData(Main.COOLDOWN, item, dataHash.getKey(), dataHash.getValue().dataInt + (int) (System.currentTimeMillis() / 1000)));
                        break;
                    case Main.TELEPORT:
                        if (!getCooldown(NBTList, player, dataHash.getKey())) return;
                        Location teleport = player.getTargetBlock(null, dataHash.getValue().mapData.get("Distance").dataInt).getLocation();
                        teleport.setYaw(player.getLocation().getYaw());
                        teleport.setPitch(player.getLocation().getPitch());
                        player.teleport(teleport);
                        player.getInventory().setItemInMainHand(main.NMSManager.setData(Main.COOLDOWN, item, dataHash.getKey(), dataHash.getValue().mapData.get("CD").dataInt + (int) (System.currentTimeMillis() / 1000)));
                        break;
                    case Main.FIREWORK:
                        if (!getCooldown(NBTList, player, dataHash.getKey())) return;

                        Firework firework = player.getWorld().spawn(player.getLocation(), Firework.class);
                        FireworkMeta fireworkMeta = firework.getFireworkMeta();
                        FireworkEffect.Builder builder = FireworkEffect.builder();
                        if (dataHash.getValue().mapData.get("Meta").data != null) {
                            ArrayList<Color> temp = new ArrayList<>(Arrays.asList(COLORS));
                            for (int i = 0; i < random(temp.size()); i++) {
                                int index = random(temp.size() - 1);
                                builder.withColor(temp.get(index));
                                temp.remove(index);
                            }
                            temp = new ArrayList<>(Arrays.asList(COLORS));
                            for (int i = 0; i < random(temp.size()); i++) {
                                int index = random(temp.size() - 1);
                                builder.withFade(temp.get(index));
                                temp.remove(index);
                            }
                            fireworkMeta.addEffect(builder.with(TYPES[random(TYPES.length - 1)]).trail(random(1) == 1).build());
                            fireworkMeta.setPower(random(2) + 1);
                        } else {
                            for (int color : dataHash.getValue().mapData.get("Meta").mainColor) {
                                if (color >= COLORS.length)
                                    continue;
                                builder.withColor(COLORS[color]);
                            }

                            for (int color : dataHash.getValue().mapData.get("Meta").fadeColor) {
                                if (color >= COLORS.length)
                                    continue;
                                builder.withFade(COLORS[color]);
                            }
                            int type = dataHash.getValue().mapData.get("Meta").type;
                            fireworkMeta.addEffect(builder.with(TYPES[(type >= TYPES.length ? 0 : type)]).trail(dataHash.getValue().mapData.get("Meta").effect == 1).build());
                            int power = dataHash.getValue().mapData.get("Meta").power;
                            fireworkMeta.setPower(power > 3 ? 0 : (power == 0 ? 1 : power));
                        }
                        firework.setFireworkMeta(fireworkMeta);
                        player.getInventory().setItemInMainHand(main.NMSManager.setData(Main.COOLDOWN, item, dataHash.getKey(), dataHash.getValue().mapData.get("CD").dataInt + (int) (System.currentTimeMillis() / 1000)));
                        break;
                }
            }
        }else if(interactEvent.getAction() == Action.RIGHT_CLICK_BLOCK){
            ItemStack item = player.getInventory().getItemInMainHand();
            Map<String, ConstructorNBTData> NBTList = main.NMSManager.getNBTKeys(item);
            for (Map.Entry<String, ConstructorNBTData> dataHash : NBTList.entrySet()) {
                switch (dataHash.getKey()) {
                    case Main.SUPER_MOB:
                        Map<String, ConstructorNBTData> dataMap = dataHash.getValue().mapData;
                        interactEvent.setCancelled(true);
                        Location location = player.getTargetBlock(null, 6).getLocation();
                        location.setY(location.getBlockY() + 1);
                        LivingEntity entity = (LivingEntity) interactEvent.getPlayer().getWorld().spawn(location, allEntity.get(dataMap.get("Type").data));
                        if(dataMap.containsKey("MobType"))
                            switch (dataMap.get("Type").data){
                                case "Cat":
                                    Cat cat = (Cat) entity;
                                    cat.setCatType(Cat.Type.valueOf(dataMap.get("MobType").data.toUpperCase(Locale.ROOT)));
                                    break;
                                case "EnderDragon":
                                    EnderDragon enderDragon = (EnderDragon) entity;
                                    enderDragon.setPhase(EnderDragon.Phase.valueOf(dataMap.get("MobType").data.toUpperCase(Locale.ROOT)));
                                    break;
                                case "Fox":
                                    Fox fox = (Fox) entity;
                                    fox.setFoxType(Fox.Type.valueOf(dataMap.get("MobType").data.toUpperCase(Locale.ROOT)));
                                    break;
                                case "Horse":
                                    Horse horse = (Horse) entity;
                                    String[] data = dataMap.get("MobType").data.toUpperCase(Locale.ROOT).split("\\.");
                                    if(data.length >= 1)
                                        horse.setColor(Horse.Color.valueOf(data[0]));

                                    if(data.length >= 2)
                                        horse.setStyle(Horse.Style.valueOf(data[1]));
                                    break;
                                case "Llama":
                                    Llama llama = (Llama) entity;
                                    llama.setColor(Llama.Color.valueOf(dataMap.get("MobType").data.toUpperCase(Locale.ROOT)));
                                    break;
                                case "MushroomCow":
                                    MushroomCow mushroomCow = (MushroomCow) entity;
                                    mushroomCow.setVariant(MushroomCow.Variant.valueOf(dataMap.get("MobType").data.toUpperCase(Locale.ROOT)));
                                    break;
                                case "Panda":
                                    Panda panda = (Panda) entity;
                                    panda.setMainGene(Panda.Gene.valueOf(dataMap.get("MobType").data.toUpperCase(Locale.ROOT)));
                                    break;
                                case "Parrot":
                                    Parrot parrot = (Parrot) entity;
                                    parrot.setVariant(Parrot.Variant.valueOf(dataMap.get("MobType").data.toUpperCase(Locale.ROOT)));
                                    break;
                                case "Rabbit":
                                    Rabbit rabbit = (Rabbit) entity;
                                    rabbit.setRabbitType(Rabbit.Type.valueOf(dataMap.get("MobType").data.toUpperCase(Locale.ROOT)));
                                    break;
                                case "TropicalFish":
                                    TropicalFish tropicalFish = (TropicalFish) entity;
                                    tropicalFish.setPattern(TropicalFish.Pattern.valueOf(dataMap.get("MobType").data.toUpperCase(Locale.ROOT)));
                                    break;
                                case "Villager":
                                    Villager villager = (Villager) entity;
                                    String[] dataVillager = dataMap.get("MobType").data.toUpperCase(Locale.ROOT).split("\\.");
                                    if(dataVillager.length >= 1)
                                        villager.setProfession(Villager.Profession.valueOf(dataVillager[0]));

                                    if(dataVillager.length >= 2)
                                        villager.setVillagerType(Villager.Type.valueOf(dataVillager[1]));
                                    break;

                            }
                        setEffects(entity, (ArrayList<Map<String, ConstructorNBTData>>) dataMap.get("Effects").arrayData);
                        main.NMSManager.setNBTEntity(interactEvent.getItem(), entity);
                        interactEvent.getItem().setAmount(interactEvent.getItem().getAmount() - 1);
                        break;
                }
            }
        }

    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
        for (ItemStack armor: event.getPlayer().getInventory().getArmorContents()) {
            if(armor != null)
                onSetArmor(event.getPlayer(), armor);
        }
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event){
        for (ItemStack item: event.getEntity().getInventory().getArmorContents()) {
            if(item != null) {
                if (!item.getType().equals(Material.AIR))
                    onUnsetArmor(event.getEntity(), item);
            }
        }
    }

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent event){
        for (ItemStack item : event.getPlayer().getInventory().getContents()) {
            if(item != null) {
                if (!item.getType().equals(Material.AIR))
                    onSetInInventory(event.getPlayer(), item);
                if (item.equals(event.getPlayer().getInventory().getItemInOffHand()))
                    onSetInSecondHandItem(event.getPlayer(), item);
                else if (item.equals(event.getPlayer().getInventory().getItemInMainHand()))
                    onSetInMainHandItem(event.getPlayer(), item);
                else {
                    ArrayList<ItemStack> tempArmor = new ArrayList<>(Arrays.asList(event.getPlayer().getInventory().getArmorContents()));
                    if (tempArmor.contains(item))
                        onSetArmor(event.getPlayer(), item);
                }
            }

        }
    }

    @EventHandler
    public void onClickEntity(PlayerInteractEntityEvent event){
        if(event.getRightClicked().getType().equals(EntityType.ITEM_FRAME) || (Pattern.compile("("+ Material.NAME_TAG +"|"+ Material.LEAD +")").matcher(event.getPlayer().getInventory().getItemInMainHand().getType().toString()).find()))
            if(event.getPlayer().getInventory().getItemInMainHand().getAmount() == 1) {
                onUnsetInMainHandItem(event.getPlayer(), event.getPlayer().getInventory().getItemInMainHand());
                onUnsetInInventory(event.getPlayer(), event.getPlayer().getInventory().getItemInMainHand());
            }
    }

    @EventHandler
    public void onItemBreak(PlayerItemBreakEvent event){
        onUnsetInMainHandItem(event.getPlayer(), event.getBrokenItem());
        onUnsetInInventory(event.getPlayer(), event.getBrokenItem());
    }

    @EventHandler
    public void onPlayerShootEvent(EntityShootBowEvent event){
        if(event.getEntity() instanceof Player){
            Player player = (Player) event.getEntity();
            if(event.getConsumable().getAmount() == 1){
                onUnsetInMainHandItem(player, event.getConsumable());
                onUnsetInInventory(player, event.getConsumable());
                onUnsetInSecondHandItem(player, event.getConsumable());
            }
        }
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event){
        for (ItemStack armor: event.getPlayer().getInventory().getArmorContents()) {
            if(armor != null)
                onUnsetArmor(event.getPlayer(), armor);
        }
    }

    @EventHandler
    public void onPlayerChangeHand(PlayerSwapHandItemsEvent event){
        if(!event.getMainHandItem().getType().equals(Material.AIR)) {
            onUnsetInSecondHandItem(event.getPlayer(), event.getMainHandItem());
            onSetInMainHandItem(event.getPlayer(), event.getMainHandItem());
        }
        if(!event.getOffHandItem().getType().equals(Material.AIR)) {
            onSetInSecondHandItem(event.getPlayer(), event.getOffHandItem());
            onUnsetInMainHandItem(event.getPlayer(), event.getOffHandItem());
        }
    }

    @EventHandler
    public void onPlayerDropItem(PlayerDropItemEvent event){
        if(event.getPlayer().getInventory().getItemInMainHand().getType().equals(Material.AIR)) {
            onUnsetInMainHandItem(event.getPlayer(), event.getItemDrop().getItemStack());
            onUnsetInInventory(event.getPlayer(), event.getItemDrop().getItemStack());
        }
    }

    @EventHandler
    public void onPlayerBreakBlocks(BlockBreakEvent event){
        Player player = event.getPlayer();
        ItemStack item = event.getPlayer().getInventory().getItemInMainHand();
        Map<String, ConstructorNBTData> NBTList = main.NMSManager.getNBTKeys(item);
        onBreakBlock(event.getBlock(), item, NBTList, player, player.getWorld(), false);
        if(event.getBlock().getType().equals(Material.AIR)) {
            item.setDurability((short) (item.getDurability() + 1));
        }
        for (Map.Entry<String, ConstructorNBTData> dataHash : NBTList.entrySet()) {
            switch (dataHash.getKey()) {
                case Main.BREAK:
                    int[] offsets = dataHash.getValue().mapData.get("Size").mainColor;
                    int firstX = 0, firstY = 0, firstZ = 0;
                    Location location = event.getBlock().getLocation();
                    ArrayList<Block> blocks = new ArrayList<>();
                    World world = player.getWorld();
                    float pitch = player.getEyeLocation().getPitch();
                    if(pitch < -45f){
                        firstX = location.getBlockX() - (offsets[0] / 2);
                        firstY = location.getBlockY();
                        firstZ = location.getBlockZ() - (offsets[2] / 2);
                        for(int iX = 0; iX < offsets[0]; iX++){
                            for(int iY = 0; iY < offsets[1]; iY++){
                                for(int iZ = 0; iZ < offsets[2]; iZ++){
                                    blocks.add(world.getBlockAt(firstX + iX, firstY + iY, firstZ + iZ));
                                }
                            }
                        }
                    }else if(pitch > 45f){
                        firstX = location.getBlockX() - (offsets[0] / 2);
                        firstY = location.getBlockY();
                        firstZ = location.getBlockZ() - (offsets[2] / 2);
                        for(int iX = 0; iX < offsets[0]; iX++){
                            for(int iY = 0; iY < offsets[1]; iY++){
                                for(int iZ = 0; iZ < offsets[2]; iZ++){
                                    blocks.add(world.getBlockAt(firstX + iX, firstY - iY, firstZ + iZ));
                                }
                            }
                        }
                    }else
                    switch (player.getFacing().toString()){
                        case "NORTH":
                            firstX = location.getBlockX() - (offsets[0] / 2);
                            firstY = location.getBlockY() - (offsets[1] / 2);
                            firstZ = location.getBlockZ();
                            for(int iX = 0; iX < offsets[0]; iX++){
                                for(int iY = 0; iY < offsets[1]; iY++){
                                    for(int iZ = 0; iZ < offsets[2]; iZ++){
                                        blocks.add(world.getBlockAt(firstX + iX, firstY + iY, firstZ - iZ));
                                    }
                                }
                            }
                            break;
                        case "SOUTH":
                            firstX = location.getBlockX() - (offsets[0] / 2);
                            firstY = location.getBlockY() - (offsets[1] / 2);
                            firstZ = location.getBlockZ();
                            for(int iX = 0; iX < offsets[0]; iX++){
                                for(int iY = 0; iY < offsets[1]; iY++){
                                    for(int iZ = 0; iZ < offsets[2]; iZ++){
                                        blocks.add(world.getBlockAt(firstX + iX, firstY + iY, firstZ + iZ));
                                    }
                                }
                            }
                            break;
                        case "WEST":
                            firstX = location.getBlockX();
                            firstY = location.getBlockY() - (offsets[1] / 2);
                            firstZ = location.getBlockZ() + (offsets[2] / 2);
                            for(int iX = 0; iX < offsets[0]; iX++){
                                for(int iY = 0; iY < offsets[1]; iY++){
                                    for(int iZ = 0; iZ < offsets[2]; iZ++){
                                        blocks.add(world.getBlockAt(firstX - iX, firstY + iY, firstZ - iZ));
                                    }
                                }
                            }
                            break;
                        case "EAST":
                            firstX = location.getBlockX();
                            firstY = location.getBlockY() - (offsets[1] / 2);
                            firstZ = location.getBlockZ() + (offsets[2] / 2);
                            for(int iX = 0; iX < offsets[0]; iX++){
                                for(int iY = 0; iY < offsets[1]; iY++){
                                    for(int iZ = 0; iZ < offsets[2]; iZ++){
                                        blocks.add(world.getBlockAt(firstX + iX, firstY + iY, firstZ - iZ));
                                    }
                                }
                            }
                            break;
                    }
                    boolean isBreak = (dataHash.getValue().mapData.get("BreakItem").dataInt != 1);
                    int countBreak = 0;
                    int exp = 0;
                    for (Block block: blocks) {
                        if(!isBreak && (countBreak >= (item.getType().getMaxDurability() - item.getDurability()) * (item.getEnchantmentLevel(Enchantment.DURABILITY) + 1))){
                            item.setDurability(item.getType().getMaxDurability());
                            isBreak = true;
                            break;
                        }
                        if(!Pattern.compile("("+ Material.AIR + "|" + Material.BEDROCK+ "|" + Material.COMMAND_BLOCK + "|" + Material.BARRIER + "|" + Material.LAVA + "|" + Material.WATER +")").matcher(block.getType().toString()).find() && !block.getLocation().equals(location)) {
                            exp += onBreakBlock(block, item, NBTList, player, world, true);
                            if(!block.getType().equals(Material.AIR))
                                block.breakNaturally();
                            countBreak++;
                        }
                    }

                    if(exp != 0) {
                        ExperienceOrb experienceOrb = world.spawn(event.getBlock().getLocation(), ExperienceOrb.class);
                        experienceOrb.setExperience(exp);
                    }

                    if(!isBreak)
                        item.setDurability((short) (item.getDurability() +  (countBreak / (item.getEnchantmentLevel(Enchantment.DURABILITY) + 1))));

                    break;
            }
        }
    }

    public int getFortune(int value, int drop){
        if(value == 0) return drop;
        int newDrop = drop;
        int chance = value * 15;
        if(chance >= 100)
            newDrop = getFortune(value - 7, drop * 2);
        else
            if(chance >= random(100))
                newDrop *= 2;
        return newDrop;
    }

    @EventHandler
    public void onPickUpItem(EntityPickupItemEvent event){
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            ItemStack itemStack = event.getItem().getItemStack();
             if(player.getInventory().getItemInMainHand().getType().equals(Material.AIR))
                Bukkit.getScheduler().runTask(main, () -> {
                    if(player.getInventory().first(itemStack) == player.getInventory().getHeldItemSlot())
                        onSetInMainHandItem(player, itemStack);
                });
            onSetInInventory(player, itemStack);
        }
        //Для мобов есть отдельный тег
    }

    @EventHandler
    public void onPlayerChangeItemInMainHand(PlayerItemHeldEvent event){
        Player player = event.getPlayer();
        ItemStack item = player.getInventory().getItemInMainHand();
        if(!item.getType().equals(Material.AIR))
            onUnsetInMainHandItem(player, item);

        item = event.getPlayer().getInventory().getItem(event.getNewSlot());
        if(item != null)
            if(!item.getType().equals(Material.AIR))
                onSetInMainHandItem(player, item);
    }

    @EventHandler
    public void onPLayerReadBookInTakeLectern(PlayerTakeLecternBookEvent event){
        ItemStack book = event.getBook().clone();
        Bukkit.getScheduler().runTask(main, () -> {
            if(event.getPlayer().getInventory().contains(book))
                onSetInInventory(event.getPlayer(), book);
        });
    }

    @EventHandler
    public void onPlayerExpChange(PlayerExpChangeEvent event) {
        PlayerInventory inventory = event.getPlayer().getInventory();
        ArrayList<ItemStack> arrayList = new ArrayList<>(Arrays.asList(inventory.getArmorContents()));
        arrayList.add(inventory.getItemInMainHand());
        arrayList.add(inventory.getItemInOffHand());
        int lvl = 0, count = 0;
        for (ItemStack item : arrayList) {
            if(item == null)
                continue;
            if (item.getType().equals(Material.AIR))
                continue;
            Map<String, ConstructorNBTData> NBTList = main.NMSManager.getNBTKeys(item);
            if(!NBTList.containsKey(Main.EXPERIENCES_BOOST))
                continue;
            lvl += NBTList.get(Main.EXPERIENCES_BOOST).dataInt;
            count++;
        }
        if(count != 0) {
            event.getPlayer().giveExp(getFortune(lvl / count, event.getAmount()) - event.getAmount());
        }
    }
    @EventHandler
    public void onInventoryChanges(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();
        if(event.getSlot() >= 0 && event.getRawSlot() >= 0)
            if(player.getOpenInventory().getType().equals(InventoryType.CRAFTING) || player.getOpenInventory().getType().equals(InventoryType.CREATIVE)){
                if(player.getInventory().getHeldItemSlot() == event.getSlot()) {
                    if (event.getCursor().getType().equals(Material.AIR))
                        onUnsetInMainHandItem(player, event.getCurrentItem());
                    else
                        onSetInMainHandItem(player, event.getCursor());
                }else if(40 == event.getSlot()){
                    if (event.getCursor().getType().equals(Material.AIR))
                        onUnsetInSecondHandItem(player, event.getCurrentItem());
                    else
                        onSetInSecondHandItem(player, event.getCursor());
                }else if(Pattern.compile("("+ InventoryAction.MOVE_TO_OTHER_INVENTORY +"|"+ InventoryAction.HOTBAR_SWAP +")").matcher(event.getAction().toString()).find() && player.getInventory().getItemInMainHand().getType().equals(Material.AIR)){
                    ItemStack newItemStack = event.getCurrentItem().clone();
                    Bukkit.getScheduler().runTask(main, () -> {
                        if(player.getInventory().first(newItemStack) == player.getInventory().getHeldItemSlot())
                            onSetInMainHandItem(player, newItemStack);
                    });
                }
                if (event.getCursor().getType().equals(Material.AIR))
                    onUnsetInInventory(player, event.getCurrentItem());
                else
                    onSetInInventory(player, event.getCursor());
            }else{
                if(!player.getOpenInventory().getType().equals(InventoryType.CREATIVE)) {
                    if (!event.getCursor().getType().equals(Material.AIR)) {
                        if (event.getRawSlot() < (player.getOpenInventory().countSlots() - player.getInventory().getSize()))
                            onUnsetInInventory(player, event.getCursor());
                        else {
                            onSetInInventory(player, event.getCursor());
                            if(event.getSlot() != event.getRawSlot() && event.getSlot() < 9 && event.getSlot() == player.getInventory().getHeldItemSlot()){
                                onSetInMainHandItem(player, event.getCursor());
                            }
                        }
                    }else {
                        if (event.getRawSlot() < (player.getOpenInventory().countSlots() - player.getInventory().getSize()) && event.getAction().equals(InventoryAction.MOVE_TO_OTHER_INVENTORY)) {
                            onSetInInventory(player, event.getCurrentItem());
                            ItemStack tempItem = event.getCurrentItem().clone();
                            if(player.getInventory().getItemInMainHand().getType().equals(Material.AIR))
                                Bukkit.getScheduler().runTask(main, () -> {
                                    if(player.getInventory().first(tempItem) == player.getInventory().getHeldItemSlot())
                                        onSetInMainHandItem(player, tempItem);
                                });
                        }else {
                            onUnsetInInventory(player, event.getCurrentItem());
                            if(event.getSlot() == player.getInventory().getHeldItemSlot())
                                onUnsetInMainHandItem(player, event.getCurrentItem());
                        }
                    }
                }
            }


        if(!(event.getSlotType().equals(InventoryType.SlotType.ARMOR) || (event.getAction().equals(InventoryAction.MOVE_TO_OTHER_INVENTORY) && Pattern.compile("(HELMET|CHESTPLATE|LEGGINGS|BOOTS)").matcher(event.getCurrentItem().getType().toString()).find() && player.getOpenInventory().getType().equals(InventoryType.CRAFTING)))) return;

        ItemStack setArmor = null;
        ItemStack unsetArmor = null;
        boolean isArmorSet = true;
        boolean ifShift = (event.getAction().equals(InventoryAction.MOVE_TO_OTHER_INVENTORY) && Pattern.compile("(HELMET|CHESTPLATE|LEGGINGS|BOOTS)").matcher(event.getCurrentItem().getType().toString()).find() && player.getOpenInventory().getType().equals(InventoryType.CRAFTING));
        if(ifShift)
            for (ItemStack armor: player.getInventory().getArmorContents()) {
                if(armor != null) {
                    isArmorSet = !armor.getType().equals(event.getCurrentItem().getType());
                    if(!isArmorSet) break;

                }
            }

        if(event.getCursor().getType().equals(Material.AIR))
            if(isArmorSet && ifShift)
                setArmor = event.getCurrentItem();
            else
                unsetArmor = event.getCurrentItem();
        else
            setArmor = event.getCursor();

        if(event.getCurrentItem().getType().equals(Material.AIR)) {
            if(setArmor == null)
                setArmor = event.getCursor();
        }else {
            if(isArmorSet && ifShift) {
                if (setArmor == null)
                    setArmor = event.getCurrentItem();
            }else
                if(unsetArmor == null)
                    unsetArmor = event.getCurrentItem();
        }
        if(setArmor != null)
            onSetArmor(player, setArmor);
        if(unsetArmor != null)
            onUnsetArmor(player, unsetArmor);
    }

    public int onBreakBlock(Block block, ItemStack itemBreaked, Map<String, ConstructorNBTData> NBTList, Player player, World world, boolean autoBreak){
        String type = block.getType().toString();
        boolean isFortune = itemBreaked.getEnchantmentLevel(Enchantment.LUCK) != 0;
        boolean isSiltTouch = itemBreaked.getEnchantmentLevel(Enchantment.SILK_TOUCH) != 0;
        boolean isAutoMelting = NBTList.containsKey(Main.AUTO_MELTING);
        boolean isTELEKINESISNotFull = true;
        int fortune = itemBreaked.getEnchantmentLevel(Enchantment.LUCK);
        if(NBTList.containsKey(Main.TELEKINESIS)){
            HashMap<Integer, ItemStack> items = new HashMap<>();
            if(block.getType().equals(Material.DIAMOND_ORE)) {
                items = player.getInventory().addItem( isSiltTouch ? new ItemStack(Material.DIAMOND_ORE, 1) : new ItemStack(Material.DIAMOND, isFortune ? getFortune(fortune, 1) : 1));
            }else if(block.getType().equals(Material.COAL_ORE)){
                items = player.getInventory().addItem( isSiltTouch ? new ItemStack(Material.COAL_ORE, 1) : new ItemStack(Material.COAL, isFortune ? getFortune(fortune, 1) : 1));
            }else if(block.getType().equals(Material.LAPIS_ORE)){
                items = player.getInventory().addItem( isSiltTouch ? new ItemStack(Material.LAPIS_ORE, 1) : new ItemStack(Material.LAPIS_LAZULI, isFortune ? getFortune(fortune, 7) : 1));
            }else if(block.getType().equals(Material.EMERALD_ORE)){
                items = player.getInventory().addItem( isSiltTouch ? new ItemStack(Material.EMERALD_ORE, 1) : new ItemStack(Material.EMERALD, isFortune ? getFortune(fortune, 1) : 1));
            }else if(block.getType().equals(Material.REDSTONE_ORE)){
                items = player.getInventory().addItem( isSiltTouch ? new ItemStack(Material.REDSTONE_ORE, 1) : new ItemStack(Material.REDSTONE,isFortune ? getFortune(fortune, 5) : 1));
            }else if(block.getType().equals(Material.NETHER_QUARTZ_ORE)){
                items = player.getInventory().addItem( isSiltTouch ? new ItemStack(Material.NETHER_QUARTZ_ORE, 1) : new ItemStack(Material.QUARTZ, isFortune ? getFortune(fortune, 1) : 1));
            }else if(block.getType().equals(Material.NETHER_GOLD_ORE)) {
                items = player.getInventory().addItem( isSiltTouch ? new ItemStack(Material.NETHER_GOLD_ORE, 1) : new ItemStack(Material.GOLD_NUGGET, isFortune ? getFortune(fortune, 1) : 1));
            }else if(block.getType().equals(Material.GRAVEL)) {
                items = player.getInventory().addItem( isSiltTouch ? new ItemStack(Material.GRAVEL, 1) : isFortune ? (getFortune(fortune, 1) == 1 ? new ItemStack(Material.GRAVEL, 1) : new ItemStack(Material.FLINT, 1)) : new ItemStack(Material.GRAVEL, 1));
            }else if(block.getType().equals(Material.STONE)) {
                items = player.getInventory().addItem( isSiltTouch || isAutoMelting ? new ItemStack(Material.STONE, 1) : new ItemStack(Material.COBBLESTONE, 1));
            }else if(block.getType().equals(Material.ENDER_CHEST)) {
                items = player.getInventory().addItem( isSiltTouch ? new ItemStack(Material.ENDER_CHEST, 1) : new ItemStack(Material.OBSIDIAN, 8));
            }else {
                if (isAutoMelting) {
                    if (block.getType().equals(Material.IRON_ORE)) {
                        items = player.getInventory().addItem(isSiltTouch ? new ItemStack(Material.IRON_ORE, 1) : new ItemStack(Material.IRON_INGOT, isFortune ? getFortune(fortune, 1) : 1));
                    } else if (block.getType().equals(Material.GOLD_ORE)) {
                        items = player.getInventory().addItem(isSiltTouch ? new ItemStack(Material.GOLD_ORE, 1) : new ItemStack(Material.GOLD_INGOT, isFortune ? getFortune(fortune, 1) : 1));
                    } else if (block.getType().equals(Material.NETHERRACK)) {
                        items = player.getInventory().addItem(new ItemStack(Material.NETHER_BRICK, 1));
                    }
                }else
                    items = player.getInventory().addItem(new ItemStack(block.getType(), 1));
            }

            if(items.size() == 0) {
                block.setType(Material.AIR);
                isTELEKINESISNotFull = false;
            }
        }
        if(isTELEKINESISNotFull){
            if(isFortune){
                if(block.getType().equals(Material.DIAMOND_ORE)) {
                    world.dropItem(block.getLocation(), new ItemStack(Material.DIAMOND, getFortune(fortune, 1)));
                    block.setType(Material.AIR);
                }else if(block.getType().equals(Material.COAL_ORE)){
                    world.dropItem(block.getLocation(), new ItemStack(Material.COAL, getFortune(fortune, 1)));
                    block.setType(Material.AIR);
                }else if(block.getType().equals(Material.LAPIS_ORE)){
                    world.dropItem(block.getLocation(), new ItemStack(Material.LAPIS_LAZULI, getFortune(fortune, 7)));
                    block.setType(Material.AIR);
                }else if(block.getType().equals(Material.EMERALD_ORE)){
                    world.dropItem(block.getLocation(), new ItemStack(Material.EMERALD, getFortune(fortune, 1)));
                    block.setType(Material.AIR);
                }else if(block.getType().equals(Material.REDSTONE_ORE)){
                    world.dropItem(block.getLocation(), new ItemStack(Material.REDSTONE, getFortune(fortune, 5)));
                    block.setType(Material.AIR);
                }else if(block.getType().equals(Material.NETHER_QUARTZ_ORE)){
                    world.dropItem(block.getLocation(), new ItemStack(Material.QUARTZ, getFortune(fortune, 1)));
                    block.setType(Material.AIR);
                }else if(block.getType().equals(Material.NETHER_GOLD_ORE)) {
                    world.dropItem(block.getLocation(), new ItemStack(Material.GOLD_NUGGET, getFortune(fortune, 1)));
                    block.setType(Material.AIR);
                }else if(block.getType().equals(Material.GRAVEL)) {
                    world.dropItem(block.getLocation(), getFortune(fortune, 1) == 1 ? new ItemStack(Material.GRAVEL, 1) : new ItemStack(Material.FLINT, 1));
                    block.setType(Material.AIR);
                }
            }else if(itemBreaked.getEnchantmentLevel(Enchantment.SILK_TOUCH) != 0){
                if(block.getType().equals(Material.DIAMOND_ORE)) {
                    world.dropItem(block.getLocation(), new ItemStack(Material.DIAMOND_ORE, 1));
                    block.setType(Material.AIR);
                }else if(block.getType().equals(Material.COAL_ORE)){
                    world.dropItem(block.getLocation(), new ItemStack(Material.COAL_ORE, 1));
                    block.setType(Material.AIR);
                }else if(block.getType().equals(Material.LAPIS_ORE)){
                    world.dropItem(block.getLocation(), new ItemStack(Material.LAPIS_ORE, 1));
                    block.setType(Material.AIR);
                }else if(block.getType().equals(Material.EMERALD_ORE)){
                    world.dropItem(block.getLocation(), new ItemStack(Material.EMERALD_ORE, 1));
                    block.setType(Material.AIR);
                }else if(block.getType().equals(Material.REDSTONE_ORE)){
                    world.dropItem(block.getLocation(), new ItemStack(Material.REDSTONE_ORE, 1));
                    block.setType(Material.AIR);
                }else if(block.getType().equals(Material.NETHER_QUARTZ_ORE)){
                    world.dropItem(block.getLocation(), new ItemStack(Material.NETHER_QUARTZ_ORE, 1));
                    block.setType(Material.AIR);
                }else if(block.getType().equals(Material.NETHER_GOLD_ORE)) {
                    world.dropItem(block.getLocation(), new ItemStack(Material.NETHER_GOLD_ORE, 1));
                    block.setType(Material.AIR);
                }
            }

            if (isAutoMelting) {
                if (block.getType().equals(Material.IRON_ORE)) {
                    world.dropItem(block.getLocation(), isSiltTouch ? new ItemStack(Material.IRON_ORE, 1) : new ItemStack(Material.IRON_INGOT, isFortune ? getFortune(fortune, 1) : 1));
                    block.setType(Material.AIR);
                } else if (block.getType().equals(Material.GOLD_ORE)) {
                    world.dropItem(block.getLocation(), isSiltTouch ? new ItemStack(Material.GOLD_ORE, 1) : new ItemStack(Material.GOLD_INGOT, isFortune ? getFortune(fortune, 1) : 1));
                    block.setType(Material.AIR);
                } else if (block.getType().equals(Material.NETHERRACK)) {
                    world.dropItem(block.getLocation(), new ItemStack(Material.NETHER_BRICK, 1));
                    block.setType(Material.AIR);
                }else if(block.getType().equals(Material.STONE)) {
                    world.dropItem(block.getLocation(), new ItemStack(Material.STONE, 1));
                    block.setType(Material.AIR);
                }
            }
            //diamond 3 - 7
            //emerald 3 - 7
            //lapis 2 - 5
            //redstone 1 - 5
            //quarts 2 - 5

        }
        if(block.getType().equals(Material.AIR) || autoBreak){
            if(Pattern.compile("^("+ Material.DIAMOND_ORE +"|"+ Material.EMERALD_ORE +")$").matcher(type).find()) {
                return random(4) + 3;
            }else if(Pattern.compile("^("+ Material.LAPIS_ORE +"|"+ Material.REDSTONE_ORE +"|"+ Material.NETHER_QUARTZ_ORE +"|"+ Material.COAL_ORE +")$").matcher(type).find()) {
                return random(3) + 2;
            }
        }
        return 0;
    }

    public void onSetInMainHandItem(Player player, ItemStack itemStack){
//        player.sendMessage("Взят предмет: " + itemStack.getType());
        Map<String, ConstructorNBTData> NBTList = main.NMSManager.getNBTKeys(itemStack);
        for (Map.Entry<String, ConstructorNBTData> dataHash: NBTList.entrySet()) {
            switch (dataHash.getKey()) {
                case Main.EFFECT_IN_MAIN_HAND:
                    Bukkit.getScheduler().runTask(main, () -> setEffects(player, (ArrayList<Map<String, ConstructorNBTData>>) dataHash.getValue().arrayData));
                    break;

                case Main.PARTICLES_IN_MAIN_HAND:
                    player.getInventory().setItem(player.getInventory().first(itemStack), (main.NMSManager.setData(Main.ID_THREAD, itemStack, Main.PARTICLES_IN_MAIN_HAND, main.NMSManager.createParticles(player, dataHash.getValue().mapData, main))));
                    break;
            }
        }
    }

    public void onUnsetInMainHandItem(Player player, ItemStack itemStack){
//        player.sendMessage("Убран предмет: " + itemStack.getType());
        Map<String, ConstructorNBTData> NBTList = main.NMSManager.getNBTKeys(itemStack);
        for (Map.Entry<String, ConstructorNBTData> dataHash: NBTList.entrySet()) {
            switch (dataHash.getKey()) {
                case Main.EFFECT_IN_MAIN_HAND:
                    clearEffects(player, (ArrayList<Map<String, ConstructorNBTData>>) dataHash.getValue().arrayData);
                    break;
                case Main.ID_THREAD:
                    Map<String, ConstructorNBTData> threads = dataHash.getValue().mapData;
                    for (Map.Entry<String, ConstructorNBTData> dataThread: threads.entrySet()) {
                        Bukkit.getScheduler().cancelTask(dataThread.getValue().dataInt);
                    }
            }
        }
    }

    public void onSetInSecondHandItem(Player player, ItemStack itemStack){
//        player.sendMessage("Взят во вторую руку: " + itemStack.getType().toString());
        Map<String, ConstructorNBTData> NBTList = main.NMSManager.getNBTKeys(itemStack);
        for (Map.Entry<String, ConstructorNBTData> dataHash: NBTList.entrySet()) {
            switch (dataHash.getKey()) {
                case Main.EFFECT_IN_SECOND_HAND:
                    setEffects(player, (ArrayList<Map<String, ConstructorNBTData>>) dataHash.getValue().arrayData);
                    break;
            }
        }
    }

    public void onUnsetInSecondHandItem(Player player, ItemStack itemStack){
//        player.sendMessage("Убран из второй руки: " + itemStack.getType().toString());
        Map<String, ConstructorNBTData> NBTList = main.NMSManager.getNBTKeys(itemStack);
        for (Map.Entry<String, ConstructorNBTData> dataHash: NBTList.entrySet()) {
            switch (dataHash.getKey()) {
                case Main.EFFECT_IN_SECOND_HAND:
                    clearEffects(player, (ArrayList<Map<String, ConstructorNBTData>>) dataHash.getValue().arrayData);
                    break;
                case Main.ID_THREAD:
                    Map<String, ConstructorNBTData> threads = dataHash.getValue().mapData;
                    for (Map.Entry<String, ConstructorNBTData> dataThread: threads.entrySet()) {
                        Bukkit.getScheduler().cancelTask(dataThread.getValue().dataInt);
                    }
            }
        }
    }

    public void onSetInInventory(Player player, ItemStack itemStack){
//        player.sendMessage("Положил предмет в инветарь: " + itemStack.getType());
        Map<String, ConstructorNBTData> NBTList = main.NMSManager.getNBTKeys(itemStack);
        for (Map.Entry<String, ConstructorNBTData> dataHash: NBTList.entrySet()) {
            switch (dataHash.getKey()) {
                case Main.EFFECT_IN_INVENTORY:
                    Bukkit.getScheduler().runTask(main, new Runnable() {
                        @Override
                        public void run() {
                            setEffects(player, (ArrayList<Map<String, ConstructorNBTData>>) dataHash.getValue().arrayData);
                        }
                    });
                    break;
                case Main.ID_THREAD:
                    Map<String, ConstructorNBTData> threads = dataHash.getValue().mapData;
                    for (Map.Entry<String, ConstructorNBTData> dataThread: threads.entrySet()) {
                        Bukkit.getScheduler().cancelTask(dataThread.getValue().dataInt);
                    }
            }
        }
    }

    public void onUnsetInInventory(Player player, ItemStack itemStack){
//        player.sendMessage("Убрал предмет из инветаря: " + itemStack.getType());
        Map<String, ConstructorNBTData> NBTList = main.NMSManager.getNBTKeys(itemStack);
        for (Map.Entry<String, ConstructorNBTData> dataHash: NBTList.entrySet()) {
            switch (dataHash.getKey()) {
                case Main.EFFECT_IN_INVENTORY:
                    clearEffects(player, (ArrayList<Map<String, ConstructorNBTData>>) dataHash.getValue().arrayData);
                    break;
                case Main.ID_THREAD:
                    Map<String, ConstructorNBTData> threads = dataHash.getValue().mapData;
                    for (Map.Entry<String, ConstructorNBTData> dataThread: threads.entrySet()) {
                        Bukkit.getScheduler().cancelTask(dataThread.getValue().dataInt);
                    }
            }
        }
    }

    public void onSetArmor(Player player, ItemStack itemStack){
//        player.sendMessage("Надето: " + itemStack.getType().toString());
        Map<String, ConstructorNBTData> NBTList = main.NMSManager.getNBTKeys(itemStack);
        for (Map.Entry<String, ConstructorNBTData> dataHash: NBTList.entrySet()) {
            switch (dataHash.getKey()){
                case Main.ANIMATION_COLOR:
                    if(!Pattern.compile("LEATHER").matcher(itemStack.getType().toString()).find()) return;
                    Map<String, ConstructorNBTData> dataMap = dataHash.getValue().mapData;
                    Material type = itemStack.getType();
                    threadsId.put(itemStack.clone(), Bukkit.getScheduler().scheduleSyncRepeatingTask(main, new Runnable() {
                        ArrayList<String> colors = (ArrayList<String>) dataMap.get("Colors").arrayData;
                        int count = -1;
                        ItemStack currentItem = null;
                        @Override
                        public void run() {
                            if(currentItem == null) {
                                ItemStack[] armors = player.getInventory().getArmorContents();
                                for (int i = 0; i < armors.length; i++) {
                                    if (armors[i] != null) {
                                        if (armors[i].getType().equals(type)) {
                                            currentItem = main.NMSManager.setData(Main.ID_THREAD, armors[i], Main.ANIMATION_COLOR, threadsId.get(armors[i]));
                                            threadsId.remove(armors[i]);
                                            if (dataMap.get("Stage") != null) {
                                                colors = getAnimColor(colors, dataMap.get("Stage").dataInt);
                                            }
                                        }
                                    }
                                }
                            }
                            LeatherArmorMeta itemMeta = (LeatherArmorMeta) currentItem.getItemMeta();
                            count++;
                            if (count >= colors.size())
                                count = 0;
                            itemMeta.setColor(Color.fromRGB(Integer.parseInt(colors.get(count), 16)));
                            currentItem.setItemMeta(itemMeta);
                            if(type.toString().contains("HELMET"))
                                player.getInventory().setHelmet(currentItem);
                            else if(type.toString().contains("CHESTPLATE"))
                                player.getInventory().setChestplate(currentItem);
                            else if(type.toString().contains("LEGGINGS"))
                                player.getInventory().setLeggings(currentItem);
                            else
                                player.getInventory().setBoots(currentItem);
                        }
                    } ,0 , dataMap.get("Speed").dataInt));
                    break;

                case Main.EFFECT_IN_ARMOR:
                    setEffects(player, (ArrayList<Map<String, ConstructorNBTData>>) dataHash.getValue().arrayData);
                    break;
                case Main.PARTICLES_IN_ARMOR:
                    break;
            }
        }
    }

    public void setEffects(LivingEntity entity, ArrayList<Map<String, ConstructorNBTData>> dataEffects){
        for (Map<String, ConstructorNBTData> effect: dataEffects) {
            PotionEffectType potionEffectType = PotionEffectType.getByName(effect.get("Id").data);
            if(potionEffectType == null)
                potionEffectType = PotionEffectType.SPEED;
            if(effect.get("Particles").dataInt == -1)
                entity.addPotionEffect(new PotionEffect(potionEffectType, Integer.MAX_VALUE, effect.get("Lvl").dataInt, false, false, true));
            else if(effect.get("Particles").dataInt == 0)
                entity.addPotionEffect(new PotionEffect(potionEffectType, Integer.MAX_VALUE, effect.get("Lvl").dataInt, true, true, true));
            else
                entity.addPotionEffect(new PotionEffect(potionEffectType, Integer.MAX_VALUE, effect.get("Lvl").dataInt, false, true, true));
        }
    }

    public void clearEffects(Player player, ArrayList<Map<String, ConstructorNBTData>> dataEffects){
        for (Map<String, ConstructorNBTData> effect: dataEffects) {
            PotionEffectType potionEffectType = PotionEffectType.getByName(effect.get("Id").data);
            if(potionEffectType == null)
                potionEffectType = PotionEffectType.SPEED;
            player.removePotionEffect(potionEffectType);
        }
    }

    public static ArrayList<String> getAnimColor(ArrayList<String> colorsHex, int stage){
        ArrayList<String>  colorReturn = new ArrayList<>();
        for (int i = 0; i < colorsHex.size(); i++){
            ArrayList<String> colors = getColor(colorsHex.get(i), colorsHex.get(i == colorsHex.size() -1 ? 0 : i + 1), stage);
            colorReturn.add(colorsHex.get(i));
            colorReturn.addAll(colors);
        }
        return colorReturn;
    }

    public static ArrayList<String> getColor(String colorOne, String colorTwo, int stage){
        ArrayList<String> newColors = new ArrayList<>();
        newColors.add(colorOne);
        newColors.add(getColorValue(colorOne, colorTwo));
        newColors.add(colorTwo);
        stage = stage/2;
        if(stage > 0)
            for (int i = 0; i < 2; i++){
                ArrayList<String> getColors = getColor(newColors.get(i), newColors.get(i + 1), stage);
                for(int l = 0; l < getColors.size(); l++){
                    newColors.add(i + 1 + l, getColors.get(l));
                }
            }
        newColors.remove(newColors.size() - 1);
        newColors.remove(0);
        return newColors;
    }

    public static String getColorValue(String colour1, String colour2){
        StringBuilder result = new StringBuilder();
        char[] one = colour1.toCharArray();
        char[] two = colour2.toCharArray();
        for(int i = 0; i < one.length; i++){
            result.append(Long.toHexString((Long.parseLong(String.valueOf(one[i]), 16) + Long.parseLong(String.valueOf(two[i]), 16))/2));
        }
        return result.toString();
    }

    public void onUnsetArmor(Player player, ItemStack itemStack){
//        player.sendMessage("Снято: " + itemStack.getType().toString());
        Map<String, ConstructorNBTData> NBTList = main.NMSManager.getNBTKeys(itemStack);

        for (Map.Entry<String, ConstructorNBTData> dataHash: NBTList.entrySet()) {
            switch (dataHash.getKey()) {
                case Main.ID_THREAD:
                    Map<String, ConstructorNBTData> threads = dataHash.getValue().mapData;
                    for (Map.Entry<String, ConstructorNBTData> dataThread: threads.entrySet()) {
                        Bukkit.getScheduler().cancelTask(dataThread.getValue().dataInt);
                    }
                    break;
                case Main.EFFECT_IN_ARMOR:
                    clearEffects(player, (ArrayList<Map<String, ConstructorNBTData>>) dataHash.getValue().arrayData);
                    break;
            }
        }
    }

    public static int random(int max){
        return (int) (Math.random() * ++max);
    }

    public boolean getCooldown(Map<String, ConstructorNBTData> NBTList, Player player, String event){
        if(NBTList.get(Main.COOLDOWN) == null) return true;
        int cdValue = 0;
        Map<String, ConstructorNBTData> CDS = NBTList.get(Main.COOLDOWN).mapData;
        if(!CDS.containsKey(event))
            return true;
        cdValue = CDS.get(event).dataInt;

        int seconds = (int)(System.currentTimeMillis()/1000);
        if(cdValue > seconds){
            player.sendMessage(String.format(main.getConfig().getString("text.CD"), cdValue - seconds));
            return false;
        }
        return true;
    }
}
