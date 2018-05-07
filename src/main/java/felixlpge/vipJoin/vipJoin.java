package felixlpge.vipJoin;

import net.minecraft.server.MinecraftServer;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

@Mod(modid = vipJoin.MODID, name = vipJoin.NAME, version = vipJoin.VERSION, serverSideOnly = true, acceptableRemoteVersions = "*")
public class vipJoin {
    public static final String MODID = "vipjoin";
    public static final String NAME = "Vip join";
    public static final String VERSION = "1.0";

    private static Logger logger;
    private static configLoader configLoader;
    private static int normalPlayer = 0;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        try {
            configLoader = new configLoader();
        } catch (IOException e) {
            e.printStackTrace();
        }
        logger = event.getModLog();
    }

    @Mod.EventBusSubscriber(modid = vipJoin.MODID)
    public static class EventHandler {
        @SubscribeEvent
        public static void joinEvent(PlayerEvent.PlayerLoggedInEvent event) {
            logger.info("UUID of Player trying to login: " + event.player.getUniqueID());
            logger.info("Player is Vip: " + configLoader.containsUser(event.player.getUniqueID() + ""));
            if (!configLoader.containsUser(event.player.getUniqueID() + "") && !(normalPlayer >= Integer.parseInt(configLoader.getConfig().get("players")))) normalPlayer++;
            else if(!configLoader.containsUser(event.player.getUniqueID() + "") && normalPlayer >= Integer.parseInt(configLoader.getConfig().get("players"))){
                MinecraftServer server = FMLCommonHandler.instance().getMinecraftServerInstance();
                server.getCommandManager().executeCommand(server, "kick " + event.player.getName() + " Server is full!");
            }
        }

        @SubscribeEvent
        public static void leaveEvent(PlayerEvent.PlayerLoggedOutEvent event){
            if (!configLoader.containsUser(event.player.getUniqueID() + "")) normalPlayer = normalPlayer - 1;
        }

    }
}
