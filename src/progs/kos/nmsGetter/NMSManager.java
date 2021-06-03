package progs.kos.nmsGetter;

import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import progs.kos.Main;

import java.util.Map;
import java.util.UUID;

public interface NMSManager {
    Map<String, ConstructorNBTData> getNBTKeys(ItemStack itemStack);

    ItemStack setData(String key, ItemStack itemStack, String event, int nextTime);

//    public ItemStack setIdThread(ItemStack itemStack, int id);

    void setNBTEntity(ItemStack eggs, Entity entity);

    int createParticles(Player player, Map<String, ConstructorNBTData> mapParticles, Main main);
}
