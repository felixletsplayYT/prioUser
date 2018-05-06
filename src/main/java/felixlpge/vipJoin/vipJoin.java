package felixlpge.vipJoin;

import net.minecraft.init.Blocks;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.io.IOException;

@Mod(modid = vipJoin.MODID, name = vipJoin.NAME, version = vipJoin.VERSION, serverSideOnly = true, acceptableRemoteVersions = "*")
public class vipJoin {
    public static final String MODID = "vipjoin";
    public static final String NAME = "Vip join";
    public static final String VERSION = "1.0";

    private static Logger logger;
    private static configLoader configLoader;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        /*try {
            configLoader = new configLoader();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        logger = event.getModLog();
    }

    @EventHandler
    public void joinEvent(PlayerEvent.PlayerLoggedInEvent event) {
        System.out.println(event.player.getUniqueID());
        JOptionPane.showMessageDialog(null, "User Login UUID: " + event.player.getUniqueID().toString());
    }

}
