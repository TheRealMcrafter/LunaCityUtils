package TheRealMcrafter.LunaCityUtils.main;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;
import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_15_R1.CraftWorld;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.plugin.java.JavaPlugin;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;

import TheRealMcrafter.LunaCityUtils.utils.DataIO;
import net.minecraft.server.v1_15_R1.BlockPosition;
import net.minecraft.server.v1_15_R1.TileEntitySkull;

public class LunaCityUtils extends JavaPlugin implements Listener{

	
	private static final Logger log = Logger.getLogger("Minecraft");
	public static final String lcuGeneral = ChatColor.AQUA + "[" + ChatColor.GREEN + "Luna City Utils" + ChatColor.AQUA + "] " + ChatColor.GOLD;
	public static final String lcuError = ChatColor.AQUA + "[" + ChatColor.GREEN + "Luna City Utils" + ChatColor.AQUA + "] " + ChatColor.RED;
	public final String version = "0.1";
	public final String mcVersion = "1.15.2";
	
	DataIO io = new DataIO();
	
	public static ArrayList<Player> addingRedLights = new ArrayList<Player>();
	public static ArrayList<Player> addingYellowLights = new ArrayList<Player>();
	public static ArrayList<Player> addingGreenLights = new ArrayList<Player>();
	public static ArrayList<Player> addingTurnLights = new ArrayList<Player>();
	public static ArrayList<Player> addingWalkLights = new ArrayList<Player>();
	public static ArrayList<Player> addingDontWalkLights = new ArrayList<Player>();
	
	public static ArrayList<Block> redLightsInstance1 = new ArrayList<Block>();
	public static ArrayList<Block> redLightsInstance2 = new ArrayList<Block>();

	public static ArrayList<Block> yellowLightsInstance1 = new ArrayList<Block>();
	public static ArrayList<Block> yellowLightsInstance2 = new ArrayList<Block>();

	public static ArrayList<Block> greenLightsInstance1 = new ArrayList<Block>();
	public static ArrayList<Block> greenLightsInstance2 = new ArrayList<Block>();

	public static ArrayList<Block> walkLightsInstance1 = new ArrayList<Block>();
	public static ArrayList<Block> walkLightsInstance2 = new ArrayList<Block>();

	public final static String red1 = "redLightsInstance1.db";
	public final static String red2 = "redLightsInstance2.db";
	public final static String yellow1 = "yellowLightsInstance1.db";
	public final static String yellow2 = "yellowLightsInstance2.db";
	public final static String green1 = "greenLightsInstance1.db";
	public final static String green2 = "greenLightsInstance2.db";
	public final static String walk1 = "walkLightsInstance1.db";
	public final static String walk2 = "walkLightsInstance2.db";
	
	private static final Random random = new Random();
	private static final String chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";

	
	@Override
	public void onEnable(){
    	log.info(String.valueOf(lcuGeneral) + "Enabling LunaCityUtils version " + version + " for MC" + mcVersion + "!");

    	getServer().getPluginManager().registerEvents(this, this);
    	
    	File dir = new File("plugins/LunaCityUtils");
		if(!dir.exists()){
		    dir.mkdir();}

		
    	File rLI1 = new File("plugins/LunaCityUtils/redLightsInstance1.db");
    	if (!rLI1.exists()) {
    		io.createDatabase(red1);
    	}
    	
    	File rLI2 = new File("plugins/LunaCityUtils/redLightsInstance2.db");
    	if (!rLI2.exists()) {
    		io.createDatabase(red2);
    	}
    	
    	
    	File yLI1 = new File("plugins/LunaCityUtils/yellowLightsInstance1.db");
    	if (!yLI1.exists()) {
    		io.createDatabase(yellow1);
    	}
    	
    	File yLI2 = new File("plugins/LunaCityUtils/yellowLightsInstance2.db");
    	if (!yLI2.exists()) {
    		io.createDatabase(yellow2);
    	}
    	
    	
    	File gLI1 = new File("plugins/LunaCityUtils/greenLightsInstance1.db");
    	if (!gLI1.exists()) {
    		io.createDatabase(green1);
    	}
    	
    	File gLI2 = new File("plugins/LunaCityUtils/greenLightsInstance2.db");
    	if (!gLI2.exists()) {
    		io.createDatabase(green2);
    	}
    	
    	
    	File wLI1 = new File("plugins/LunaCityUtils/walkLightsInstance1.db");
    	if (!wLI1.exists()) {
    		io.createDatabase(walk1);
    	}
    	
    	
    	File wLI2 = new File("plugins/LunaCityUtils/walkLightsInstance2.db");
    	if (!wLI2.exists()) {
    		io.createDatabase(walk2);
    	}
    	
    	
    	io.loadLightDatabaseFromFile("redLightsInstance1.db");
    	io.loadLightDatabaseFromFile("redLightsInstance2.db");
    	io.loadLightDatabaseFromFile("yellowLightsInstance1.db");
    	io.loadLightDatabaseFromFile("yellowLightsInstance2.db");
    	io.loadLightDatabaseFromFile("greenLightsInstance1.db");
    	io.loadLightDatabaseFromFile("greenLightsInstance2.db");
    	io.loadLightDatabaseFromFile("walkLightsInstance1.db");
    	io.loadLightDatabaseFromFile("walkLightsInstance2.db");
    	
    	
    	log.info(String.valueOf(lcuGeneral) + "LunaCityUtils enabled!");

	}
	
	
	@Override
	public void onDisable(){
    	log.info(String.valueOf(lcuGeneral) + "Disabling LunaCityUtils version " + version + " for MC" + mcVersion + "!");
	}
	
	
	
	
	@EventHandler
    public void onPlayerInteract(final PlayerInteractEvent event) {
        Player p = event.getPlayer();
        
        if (event.getClickedBlock() != null){
        if (event.getHand() != null){
        if (event.getHand().equals(EquipmentSlot.HAND)) {
        	if (p.hasPermission("lunacityutils.admin")){
        		if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK)){
        			if (p.getInventory().getItemInMainHand().getType().equals(Material.STICK)){
        				if (event.getClickedBlock().getType().equals(Material.PLAYER_WALL_HEAD)){
        					// Add to instance 1
        					if (addingRedLights.contains(p)){
        						// Add this block as a red light
        						if (!redLightsInstance1.contains(event.getClickedBlock())){
        							io.addToLightDatabase(event.getClickedBlock(), red1);
        							p.sendMessage(lcuGeneral + "Added light!");
        						} else {
       								p.sendMessage(lcuError + "Already added!");
        						}
       						} else if (addingYellowLights.contains(p)){
       							// Add this block as a yellow light
       							if (!yellowLightsInstance1.contains(event.getClickedBlock())){
       								io.addToLightDatabase(event.getClickedBlock(), yellow1);
       								p.sendMessage(lcuGeneral + "Added light!");
       							} else {
       								p.sendMessage(lcuError + "Already added!");
       							}
        					} else if (addingGreenLights.contains(p)){
        						// Add this block as a green light
        						if (!greenLightsInstance1.contains(event.getClickedBlock())){
        							io.addToLightDatabase(event.getClickedBlock(), green1);
        							p.sendMessage(lcuGeneral + "Added light!");
        						} else {
       								p.sendMessage(lcuError + "Already added!");
        						}
        					} else if (addingWalkLights.contains(p)){
       							// Add this block as a walk/dont walk light
        						if (!walkLightsInstance1.contains(event.getClickedBlock())){
        							io.addToLightDatabase(event.getClickedBlock(), walk1);
        							p.sendMessage(lcuGeneral + "Added light!");
        						} else {
       								p.sendMessage(lcuError + "Already added!");
        						}
       						}
       					} else {
       		        		p.sendMessage(lcuError + "Unable to set this as a light! Must be a player wall head!");
       					}
        					
        			} else if (p.getInventory().getItemInMainHand().getType().equals(Material.BLAZE_ROD)) {
       					if (event.getClickedBlock().getType().equals(Material.PLAYER_WALL_HEAD)){
       						// Add to instance 2
       					
       						if (addingRedLights.contains(p)){
       							// Add this block as a red light
       							if (!redLightsInstance2.contains(event.getClickedBlock())){
       								io.addToLightDatabase(event.getClickedBlock(), red2);
       								p.sendMessage(lcuGeneral + "Added light!");
       							} else {
       								p.sendMessage(lcuError + "Already added!");
       							}
        					} else if (addingYellowLights.contains(p)){
        						// Add this block as a yellow light
        						if (!yellowLightsInstance2.contains(event.getClickedBlock())){
        							io.addToLightDatabase(event.getClickedBlock(), yellow2);
        							p.sendMessage(lcuGeneral + "Added light!");
        						} else {
       								p.sendMessage(lcuError + "Already added!");
        						}
        					} else if (addingGreenLights.contains(p)){
       							// Add this block as a green light
        						if (!greenLightsInstance2.contains(event.getClickedBlock())){
        							io.addToLightDatabase(event.getClickedBlock(), green2);
        							p.sendMessage(lcuGeneral + "Added light!");
        						} else {
       								p.sendMessage(lcuError + "Already added!");
        						}
       						} else if (addingWalkLights.contains(p)){
       							// Add this block as a walk/dont walk light
       							if (!walkLightsInstance2.contains(event.getClickedBlock())){
       								io.addToLightDatabase(event.getClickedBlock(), walk2);
       								p.sendMessage(lcuGeneral + "Added light!");
       							} else {
       								p.sendMessage(lcuError + "Already added!");
       							}
       						}
        				} else {
        	        		p.sendMessage(lcuError + "Unable to set this as a light! Must be a player wall head!");
        				}
       				}
       			}
       		}
       	}
        }
        }
	}
	
	
	
	
	public boolean onCommand(final CommandSender sender, final Command command, final String commandLabel, final String[] args) {
		
		if (sender instanceof Player){
			Player p = (Player) sender;
			
			if (command.getLabel().equalsIgnoreCase("lunacityutils") || command.getLabel().equalsIgnoreCase("lcu")){
				// LunaCityUtils main command, process accordingly (They typed /lunacityutils or /lcu)
				
				if (args.length > 0){
					// They have an argument: (Ex: /lcu toot)
					
					if (args[0].equalsIgnoreCase("editRedLights")){
						// Proper usage, /lcu addRedLights
							
						if (addingRedLights.contains(p)){
							addingRedLights.remove(p);
							p.sendMessage(lcuGeneral + "No longer adding red lights");
						} else {
							addingRedLights.remove(p);
							addingYellowLights.remove(p);
							addingGreenLights.remove(p);
							addingTurnLights.remove(p);
							addingWalkLights.remove(p);
							addingDontWalkLights.remove(p);
							
							addingRedLights.add(p);
							p.sendMessage(lcuGeneral + "Now adding red lights! Right click the red lights with a stick to add them to instance #1, or a blaze rod to add them to instance #2!");
						}
					} else if (args[0].equalsIgnoreCase("editYellowLights")){
						// Proper usage, /lcu addRedLights
							
						if (addingYellowLights.contains(p)){
							addingYellowLights.remove(p);
							p.sendMessage(lcuGeneral + "No longer adding yellow lights");
						} else {
							addingRedLights.remove(p);
							addingYellowLights.remove(p);
							addingGreenLights.remove(p);
							addingTurnLights.remove(p);
							addingWalkLights.remove(p);
							addingDontWalkLights.remove(p);
							
							addingYellowLights.add(p);
							p.sendMessage(lcuGeneral + "Now adding yellow lights! Right click the yellow lights with a stick to add them to instance #1, or a blaze rod to add them to instance #2!");
						}
						
					} else if (args[0].equalsIgnoreCase("editGreenLights")){
						// Proper usage, /lcu addRedLights
							
						if (addingGreenLights.contains(p)){
							addingGreenLights.remove(p);
							p.sendMessage(lcuGeneral + "No longer adding green lights");
						} else {
							addingRedLights.remove(p);
							addingYellowLights.remove(p);
							addingGreenLights.remove(p);
							addingTurnLights.remove(p);
							addingWalkLights.remove(p);
							addingDontWalkLights.remove(p);
							
							addingGreenLights.add(p);
							p.sendMessage(lcuGeneral + "Now adding green lights! Right click the green lights with a stick to add them to instance #1, or a blaze rod to add them to instance #2!");
						}
						
					} else if (args[0].equalsIgnoreCase("editWalkLights")){
						// Proper usage, /lcu addRedLights
							
						if (addingWalkLights.contains(p)){
							addingWalkLights.remove(p);
							p.sendMessage(lcuGeneral + "No longer adding walk lights");
						} else {
							addingRedLights.remove(p);
							addingYellowLights.remove(p);
							addingGreenLights.remove(p);
							addingTurnLights.remove(p);
							addingWalkLights.remove(p);
							addingDontWalkLights.remove(p);
							
							addingWalkLights.add(p);
							p.sendMessage(lcuGeneral + "Now adding walk lights! Right click the walk lights with a stick to add them to instance #1, or a blaze rod to add them to instance #2!");
						}
					} else if (args[0].equalsIgnoreCase("redInstance1")){
						this.turnRedLightsOn(1);
						this.turnYellowLightsOff(1);
						this.turnGreenLightsOff(1);
					} else if (args[0].equalsIgnoreCase("yellowInstance1")){
						this.turnRedLightsOff(1);
						this.turnYellowLightsOn(1);
						this.turnGreenLightsOff(1);
					} else if (args[0].equalsIgnoreCase("greenInstance1")){
						this.turnRedLightsOff(1);
						this.turnYellowLightsOff(1);
						this.turnGreenLightsOn(1);
					} else if (args[0].equalsIgnoreCase("redInstance2")){
						this.turnRedLightsOn(2);
						this.turnYellowLightsOff(2);
						this.turnGreenLightsOff(2);
					} else if (args[0].equalsIgnoreCase("yellowInstance2")){
						this.turnRedLightsOff(2);
						this.turnYellowLightsOn(2);
						this.turnGreenLightsOff(2);
					} else if (args[0].equalsIgnoreCase("greenInstance2")){
						this.turnRedLightsOff(2);
						this.turnYellowLightsOff(2);
						this.turnGreenLightsOn(2);
					} else if (args[0].equalsIgnoreCase("walkInstance1")){
						this.turnWalkLightsOn(1);
						this.turnDontWalkLightsOn(2);
					} else if (args[0].equalsIgnoreCase("walkInstance2")){
						this.turnWalkLightsOn(2);
						this.turnDontWalkLightsOn(1);
					}
				} else {
					p.sendMessage(lcuError + "Incorrect usage! Do /lcu help for more info!");
					
				}
			}
		} else {
			
			if (command.getLabel().equalsIgnoreCase("lunacityutils") || command.getLabel().equalsIgnoreCase("lcu")){
				if (args.length == 1){
					if (args[0].equalsIgnoreCase("redInstance1")){
						this.turnRedLightsOn(1);
						this.turnYellowLightsOff(1);
						this.turnGreenLightsOff(1);
					}
					
					if (args[0].equalsIgnoreCase("yellowInstance1")){
						this.turnRedLightsOff(1);
						this.turnYellowLightsOn(1);
						this.turnGreenLightsOff(1);
					}
					
					if (args[0].equalsIgnoreCase("greenInstance1")){
						this.turnRedLightsOff(1);
						this.turnYellowLightsOff(1);
						this.turnGreenLightsOn(1);
					}
					
					
					
					
					if (args[0].equalsIgnoreCase("redInstance2")){
						this.turnRedLightsOn(2);
						this.turnYellowLightsOff(2);
						this.turnGreenLightsOff(2);
					}
					
					if (args[0].equalsIgnoreCase("yellowInstance2")){
						this.turnRedLightsOff(2);
						this.turnYellowLightsOn(2);
						this.turnGreenLightsOff(2);
					}
					
					if (args[0].equalsIgnoreCase("greenInstance2")){
						this.turnRedLightsOff(2);
						this.turnYellowLightsOff(2);
						this.turnGreenLightsOn(2);
					}
					
					if (args[0].equalsIgnoreCase("walkInstance1")){
						this.turnWalkLightsOn(1);
						this.turnDontWalkLightsOn(2);
					}
					
					
					if (args[0].equalsIgnoreCase("walkInstance2")){
						this.turnWalkLightsOn(2);
						this.turnDontWalkLightsOn(1);
					}
					
				}
			}
			
		}
		
		return false;
	}
	
	
	public static String getRandomString(int length) {
		StringBuilder b = new StringBuilder(length);
		for(int j = 0; j < length; j++)
		b.append(chars.charAt(random.nextInt(chars.length())));
		return b.toString();
		}
	
	public static GameProfile getNonPlayerProfile(String skinURL, boolean randomName) {
		GameProfile newSkinProfile = new GameProfile(UUID.randomUUID(), randomName ? getRandomString(16) : null);
		newSkinProfile.getProperties().put("textures", new Property("textures", Base64Coder.encodeString("{textures:{SKIN:{url:\"" + skinURL + "\"}}}")));
		return newSkinProfile;
		}
	
	
	@SuppressWarnings("deprecation")
	public static void setSkullWithNonPlayerProfile(String skinURL, boolean randomName, Block skull) {
		if(skull.getType() != Material.PLAYER_WALL_HEAD)
		throw new IllegalArgumentException("Block must be a skull.");
		TileEntitySkull skullTile = (TileEntitySkull)((CraftWorld)skull.getWorld()).getHandle().getTileEntity(new BlockPosition(skull.getX(), skull.getY(), skull.getZ()));
		skullTile.setGameProfile(getNonPlayerProfile(skinURL, randomName));
		skull.getWorld().refreshChunk(skull.getChunk().getX(), skull.getChunk().getZ());
		}
	
	
	public void turnRedLightsOn(int instance){
		if (instance == 1){
			for (int i = 0; i < redLightsInstance1.size(); i++){
				Block block = redLightsInstance1.get(i);
				
				if (block.getType().equals(Material.PLAYER_WALL_HEAD)){	
					setSkullWithNonPlayerProfile("http://textures.minecraft.net/texture/ad5fcd31287d63e7826ea760a7ed154f685dfdc7f3465732a96e619b2e1347", true, block);
				}
			}
		} else if (instance == 2){
			for (int i = 0; i < redLightsInstance2.size(); i++){
				Block block = redLightsInstance2.get(i);
				
				if (block.getType().equals(Material.PLAYER_WALL_HEAD)){	
					setSkullWithNonPlayerProfile("http://textures.minecraft.net/texture/ad5fcd31287d63e7826ea760a7ed154f685dfdc7f3465732a96e619b2e1347", true, block);
				}
			
			}
		}
	}
	
	public void turnWalkLightsOn(int instance){
		if (instance == 1){
			for (int i = 0; i < walkLightsInstance1.size(); i++){
				Block block = walkLightsInstance1.get(i);
				
				if (block.getType().equals(Material.PLAYER_WALL_HEAD)){	
					setSkullWithNonPlayerProfile("http://textures.minecraft.net/texture/b048cc0dee095be5839fdf2f7d382556c00c686c7eb4b3c690be4df02f212aad", true, block);
				}
			}
		} else if (instance == 2){
			for (int i = 0; i < walkLightsInstance2.size(); i++){
				Block block = walkLightsInstance2.get(i);
				
				if (block.getType().equals(Material.PLAYER_WALL_HEAD)){	
					setSkullWithNonPlayerProfile("http://textures.minecraft.net/texture/b048cc0dee095be5839fdf2f7d382556c00c686c7eb4b3c690be4df02f212aad", true, block);
				}
			
			}
		}
	}
	
	public void turnDontWalkLightsOn(int instance){
		if (instance == 1){
			for (int i = 0; i < walkLightsInstance1.size(); i++){
				Block block = walkLightsInstance1.get(i);
				
				if (block.getType().equals(Material.PLAYER_WALL_HEAD)){	
					setSkullWithNonPlayerProfile("http://textures.minecraft.net/texture/dc4c33fc1f1ba5f7fd8772fe65d23fd549506b8991cdaddd47cc779944aa1b1b", true, block);
				}
			}
		} else if (instance == 2){
			for (int i = 0; i < walkLightsInstance2.size(); i++){
				Block block = walkLightsInstance2.get(i);
				
				if (block.getType().equals(Material.PLAYER_WALL_HEAD)){	
					setSkullWithNonPlayerProfile("http://textures.minecraft.net/texture/dc4c33fc1f1ba5f7fd8772fe65d23fd549506b8991cdaddd47cc779944aa1b1b", true, block);
				}
			
			}
		}
	}
	
	public void turnRedLightsOff(int instance){
		if (instance == 1){
			for (int i = 0; i < redLightsInstance1.size(); i++){
				Block block = redLightsInstance1.get(i);
				
				if (block.getType().equals(Material.PLAYER_WALL_HEAD)){	
					setSkullWithNonPlayerProfile("http://textures.minecraft.net/texture/f986b3f5ebf415a3c418781489bf55be5d5d275a5bafd1deaec2da63b0f0", true, block);
				}
			}
		} else if (instance == 2){
			for (int i = 0; i < redLightsInstance2.size(); i++){
				Block block = redLightsInstance2.get(i);
				
				if (block.getType().equals(Material.PLAYER_WALL_HEAD)){	
					setSkullWithNonPlayerProfile("http://textures.minecraft.net/texture/f986b3f5ebf415a3c418781489bf55be5d5d275a5bafd1deaec2da63b0f0", true, block);
				}
			}
		}
	}
	
	
	
	
	
	public void turnYellowLightsOn(int instance){
		if (instance == 1){
			for (int i = 0; i < yellowLightsInstance1.size(); i++){
				Block block = yellowLightsInstance1.get(i);
				
				if (block.getType().equals(Material.PLAYER_WALL_HEAD)){	
					setSkullWithNonPlayerProfile("http://textures.minecraft.net/texture/7440123e50d4b3d8a2a8b3581ef483fe3335ff57cfac15a21cbced2ac2cfe4", true, block);
				}
			}
		} else if (instance == 2){
			for (int i = 0; i < yellowLightsInstance2.size(); i++){
				Block block = yellowLightsInstance2.get(i);
				
				if (block.getType().equals(Material.PLAYER_WALL_HEAD)){	
					setSkullWithNonPlayerProfile("http://textures.minecraft.net/texture/7440123e50d4b3d8a2a8b3581ef483fe3335ff57cfac15a21cbced2ac2cfe4", true, block);
				}
			}
		}
	}
	
	public void turnYellowLightsOff(int instance){
		if (instance == 1){
			for (int i = 0; i < yellowLightsInstance1.size(); i++){
				Block block = yellowLightsInstance1.get(i);
				
				if (block.getType().equals(Material.PLAYER_WALL_HEAD)){	
					setSkullWithNonPlayerProfile("http://textures.minecraft.net/texture/f986b3f5ebf415a3c418781489bf55be5d5d275a5bafd1deaec2da63b0f0", true, block);
				}
			}
		} else if (instance == 2){
			for (int i = 0; i < yellowLightsInstance2.size(); i++){
				Block block = yellowLightsInstance2.get(i);
				
				if (block.getType().equals(Material.PLAYER_WALL_HEAD)){	
					setSkullWithNonPlayerProfile("http://textures.minecraft.net/texture/f986b3f5ebf415a3c418781489bf55be5d5d275a5bafd1deaec2da63b0f0", true, block);
				}
			}
		}
	}
	
	public void turnGreenLightsOn(int instance){
		if (instance == 1){
			for (int i = 0; i < greenLightsInstance1.size(); i++){
				Block block = greenLightsInstance1.get(i);
				
				if (block.getType().equals(Material.PLAYER_WALL_HEAD)){	
					setSkullWithNonPlayerProfile("http://textures.minecraft.net/texture/f45c9acea8da71b4f252cd4deb5943f49e7dbc0764274b25a6a6f5875baea3", true, block);
				}
			}
		} else if (instance == 2){
			for (int i = 0; i < greenLightsInstance2.size(); i++){
				Block block = greenLightsInstance2.get(i);
				
				if (block.getType().equals(Material.PLAYER_WALL_HEAD)){	
					setSkullWithNonPlayerProfile("http://textures.minecraft.net/texture/f45c9acea8da71b4f252cd4deb5943f49e7dbc0764274b25a6a6f5875baea3", true, block);
				}
			}
		}
	}
	
	public void turnGreenLightsOff(int instance){
		if (instance == 1){
			for (int i = 0; i < greenLightsInstance1.size(); i++){
				Block block = greenLightsInstance1.get(i);
				
				if (block.getType().equals(Material.PLAYER_WALL_HEAD)){	
					setSkullWithNonPlayerProfile("http://textures.minecraft.net/texture/f986b3f5ebf415a3c418781489bf55be5d5d275a5bafd1deaec2da63b0f0", true, block);
				}
			}
		} else if (instance == 2){
			for (int i = 0; i < greenLightsInstance2.size(); i++){
				Block block = greenLightsInstance2.get(i);
				
				if (block.getType().equals(Material.PLAYER_WALL_HEAD)){	
					setSkullWithNonPlayerProfile("http://textures.minecraft.net/texture/f986b3f5ebf415a3c418781489bf55be5d5d275a5bafd1deaec2da63b0f0", true, block);
				}
			}
		}
	}
	
}
