package fr.minekahest;

import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.ThrownPotion;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;

public class EntityListener implements Listener {
	
	@EventHandler
	//Cet event permet de gérer la mort d'une entité, via filtre il est facile de retrouver
	//Qui a fait quoi, ici on s'intéresse à toutes les morts qui ne concernent que les non joueurs
	//Et on s'assure que ça soit bien un joueur qui ai achevé la créature.
	public void onEntityDeath (EntityDeathEvent event) {
	    Entity entity = event.getEntity();
	    //On vérifie si l'entité morte ne soit pas un joueur
	    if ( ! (entity instanceof Player)) {
		    //On vérifie que la mort soit bien causée par une entité
	    	if (entity.getLastDamageCause() instanceof EntityDamageByEntityEvent) {
	    		Entity killer = ((EntityDamageByEntityEvent)event.getEntity().getLastDamageCause()).getDamager();
	    		//Si cette les dégâts ne proviennent pas d'un joueur
	    		//Ni d'une flèche, ni d'une potion lancée
	    			if ( ! (killer instanceof Player) && ! (killer instanceof Arrow) && ! (killer instanceof ThrownPotion)) {
	    				//Ici il est posible de vérifier si le projectile vient d'un joueur
	    				//Mais je trouve le friendlyfire plus amusant, c'est une technique de combat
	    				//Et cela mérite un loot ;).
	    				
	    				//Suppresion des loots et de l'xp.
	    		    	event.getDrops().clear();
	    		    	event.setDroppedExp(0);
	    			}
	    	//Si c'est un joueur on peut eventuellement coder ici les actions à réaliser (autre plugin à venir).
		    }
	    }
	}
}
