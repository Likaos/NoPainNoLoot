package fr.minekahest;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class NoPainNoLoot extends JavaPlugin {
		
	//CHARGEMENT DU PLUGIN
    @Override
    public void onEnable(){
    	//Enregistrement du listener
    	PluginManager pm = this.getServer().getPluginManager();
        pm.registerEvents(new NPNL_Listener(), this);
    }
 
    //ARRET DU PLUGIN
    @Override
    public void onDisable() {
    }
}
