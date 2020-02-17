package TheRealMcrafter.LunaCityUtils.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import org.bukkit.Bukkit;
import org.bukkit.block.Block;

import TheRealMcrafter.LunaCityUtils.main.LunaCityUtils;

public class DataIO {
	

	public void loadLightDatabaseFromFile(String filename){
		try {
			FileInputStream fstream = new FileInputStream("plugins/LunaCityUtils/" + filename);
			BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
			String strLine;
			
			
			switch (filename) {
			case "redLightsInstance1.db": 
				LunaCityUtils.redLightsInstance1.clear();;
				break;
			case "redLightsInstance2.db":
				LunaCityUtils.redLightsInstance2.clear();
				break;
			case "yellowLightsInstance1.db":
				LunaCityUtils.yellowLightsInstance1.clear();
				break;
			case "yellowLightsInstance2.db":
				LunaCityUtils.yellowLightsInstance2.clear();
				break;
			case "greenLightsInstance1.db":
				LunaCityUtils.greenLightsInstance1.clear();
				break;
			case "greenLightsInstance2.db":
				LunaCityUtils.greenLightsInstance2.clear();
				break;
			case "walkLightsInstance1.db":
				LunaCityUtils.walkLightsInstance1.clear();
				break;
			case "walkLightsInstance2.db":
				LunaCityUtils.walkLightsInstance2.clear();
				break;
			}

			
			while ((strLine = br.readLine()) != null){

				int x = Integer.valueOf(strLine.substring(0, strLine.indexOf(":")));
				strLine = strLine.substring(strLine.indexOf(":") + 1);
				int y = Integer.valueOf(strLine.substring(0, strLine.indexOf(":")));
				strLine = strLine.substring(strLine.indexOf(":") + 1);
				int z = Integer.valueOf(strLine.substring(0));
				
				Block block = Bukkit.getWorld("world").getBlockAt(x, y, z);
				
				
				switch (filename) {
				case "redLightsInstance1.db": 
					LunaCityUtils.redLightsInstance1.add(block);
					break;
				case "redLightsInstance2.db":
					LunaCityUtils.redLightsInstance2.add(block);
					break;
				case "yellowLightsInstance1.db":
					LunaCityUtils.yellowLightsInstance1.add(block);
					break;
				case "yellowLightsInstance2.db":
					LunaCityUtils.yellowLightsInstance2.add(block);
					break;
				case "greenLightsInstance1.db":
					LunaCityUtils.greenLightsInstance1.add(block);
					break;
				case "greenLightsInstance2.db":
					LunaCityUtils.greenLightsInstance2.add(block);
					break;
				case "walkLightsInstance1.db":
					LunaCityUtils.walkLightsInstance1.add(block);
					break;
				case "walkLightsInstance2.db":
					LunaCityUtils.walkLightsInstance2.add(block);
					break;
				}
			}
			
			br.close();
		} catch (IOException e) {
		}
	}
	

	public void addToLightDatabase(Block block, String databaseName){
		try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("plugins/LunaCityUtils/" + databaseName, true)))) {
			
			switch (databaseName) {
				case "redLightsInstance1.db": 
					LunaCityUtils.redLightsInstance1.add(block);
					break;
				case "redLightsInstance2.db":
					LunaCityUtils.redLightsInstance2.add(block);
					break;
				case "yellowLightsInstance1.db":
					LunaCityUtils.yellowLightsInstance1.add(block);
					break;
				case "yellowLightsInstance2.db":
					LunaCityUtils.yellowLightsInstance2.add(block);
					break;
				case "greenLightsInstance1.db":
					LunaCityUtils.greenLightsInstance1.add(block);
					break;
				case "greenLightsInstance2.db":
					LunaCityUtils.greenLightsInstance2.add(block);
					break;
				case "walkLightsInstance1.db":
					LunaCityUtils.walkLightsInstance1.add(block);
					break;
				case "walkLightsInstance2.db":
					LunaCityUtils.walkLightsInstance2.add(block);
					break;
			}
			
			out.println(block.getX() + ":" + block.getY() + ":" + block.getZ());
			
			
		} catch (IOException e) {
		}
	}
	
	
	public void removeFromLightDatabase(Block block, String databaseName){
		try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("plugins/LunaCityUtils/" + databaseName, true)))) {
			
			File file = new File("plugins/LunaCityUtils/" + databaseName);
			file.delete();
			
			switch (databaseName) {
			case "redLightsInstance1.db": 
				LunaCityUtils.redLightsInstance1.remove(block);
				
				for (int i = 0; i < LunaCityUtils.redLightsInstance1.size(); i++){
					out.println(LunaCityUtils.redLightsInstance1.get(i).getX() + ":" + LunaCityUtils.redLightsInstance1.get(i).getY() + ":" + LunaCityUtils.redLightsInstance1.get(i).getZ());
				}
				
				break;
			case "redLightsInstance2.db":
				LunaCityUtils.redLightsInstance2.remove(block);
				
				for (int i = 0; i < LunaCityUtils.redLightsInstance2.size(); i++){
					out.println(LunaCityUtils.redLightsInstance2.get(i).getX() + ":" + LunaCityUtils.redLightsInstance2.get(i).getY() + ":" + LunaCityUtils.redLightsInstance2.get(i).getZ());
				}
				
				break;
			case "yellowLightsInstance1.db":
				LunaCityUtils.yellowLightsInstance1.remove(block);
				
				for (int i = 0; i < LunaCityUtils.yellowLightsInstance1.size(); i++){
					out.println(LunaCityUtils.yellowLightsInstance1.get(i).getX() + ":" + LunaCityUtils.yellowLightsInstance1.get(i).getY() + ":" + LunaCityUtils.yellowLightsInstance1.get(i).getZ());
				}
				
				break;
			case "yellowLightsInstance2.db":
				LunaCityUtils.yellowLightsInstance2.remove(block);
				
				for (int i = 0; i < LunaCityUtils.yellowLightsInstance2.size(); i++){
					out.println(LunaCityUtils.yellowLightsInstance2.get(i).getX() + ":" + LunaCityUtils.yellowLightsInstance2.get(i).getY() + ":" + LunaCityUtils.yellowLightsInstance2.get(i).getZ());
				}
				
				break;
			case "greenLightsInstance1.db":
				LunaCityUtils.greenLightsInstance1.remove(block);
				
				for (int i = 0; i < LunaCityUtils.greenLightsInstance1.size(); i++){
					out.println(LunaCityUtils.greenLightsInstance1.get(i).getX() + ":" + LunaCityUtils.greenLightsInstance1.get(i).getY() + ":" + LunaCityUtils.greenLightsInstance1.get(i).getZ());
				}
				
				break;
			case "greenLightsInstance2.db":
				LunaCityUtils.greenLightsInstance2.remove(block);
				
				for (int i = 0; i < LunaCityUtils.greenLightsInstance2.size(); i++){
					out.println(LunaCityUtils.greenLightsInstance2.get(i).getX() + ":" + LunaCityUtils.greenLightsInstance2.get(i).getY() + ":" + LunaCityUtils.greenLightsInstance2.get(i).getZ());
				}
				
				break;
			case "walkLightsInstance1.db":
				LunaCityUtils.walkLightsInstance1.remove(block);
				
				for (int i = 0; i < LunaCityUtils.walkLightsInstance1.size(); i++){
					out.println(LunaCityUtils.walkLightsInstance1.get(i).getX() + ":" + LunaCityUtils.walkLightsInstance1.get(i).getY() + ":" + LunaCityUtils.walkLightsInstance1.get(i).getZ());
				}
				
				break;
			case "walkLightsInstance2.db":
				LunaCityUtils.walkLightsInstance2.remove(block);
				
				for (int i = 0; i < LunaCityUtils.walkLightsInstance2.size(); i++){
					out.println(LunaCityUtils.walkLightsInstance2.get(i).getX() + ":" + LunaCityUtils.walkLightsInstance2.get(i).getY() + ":" + LunaCityUtils.walkLightsInstance2.get(i).getZ());
				}
				
				break;
			}
			
			
			
			
			
			
			
			
		} catch (IOException e) {
		}
	}
	
	public void createDatabase(String filename){
		try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("plugins/LunaCityUtils/" + filename, true)))) {
		} catch (IOException e) {
			
		}
	}
	

}
