package TheRealMcrafter.LunaCityUtils.main;

import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class LunaCityUtils extends JavaPlugin implements Listener{

	
	private static final Logger log = Logger.getLogger("Minecraft");
	public static final String lcuGeneral = ChatColor.AQUA + "[" + ChatColor.GREEN + "Luna City Utils" + ChatColor.AQUA + "] " + ChatColor.GOLD;
	public static final String lcuError = ChatColor.AQUA + "[" + ChatColor.GREEN + "Luna City Utils" + ChatColor.AQUA + "] " + ChatColor.RED;
	public final String version = "0.1";
	public final String mcVersion = "1.15.2";
	
	@Override
	public void onEnable(){
    	log.info(String.valueOf(lcuGeneral) + "Enabling LunaCityUtils version " + version + " for MC" + mcVersion + "!");

    	getServer().getPluginManager().registerEvents(this, this);
    	
    	log.info(String.valueOf(lcuGeneral) + "LunaCityUtils enabled!");

	}
	
	
	@Override
	public void onDisable(){
    	log.info(String.valueOf(lcuGeneral) + "Disabling LunaCityUtils version " + version + " for MC" + mcVersion + "!");
	}
	
	
	public boolean onCommand(final CommandSender sender, final Command command, final String commandLabel, final String[] args) {
		
		if (sender instanceof Player){
			Player p = (Player) sender;
			
			if (command.getLabel().equalsIgnoreCase("lunacityutils") || command.getLabel().equalsIgnoreCase("lcu")){
				// LunaCityUtils main command, process accordingly
				
				
				
			}
			
		
		}
		
		return false;
	}
	
	
}
